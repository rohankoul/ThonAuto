package autothon.autothon.nonUI;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.StringWriter;
//import java.net.HttpURLConnection;
//import java.net.InetSocketAddress;
//import java.net.Proxy;
//import java.net.URI;
//import java.net.URL;
import java.util.ArrayList;
//import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
//import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//import javax.servlet.ServletRequest;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

//import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;


//import com.google.api.services.youtube.YouTube;


@SuppressWarnings("deprecation")
@CrossOrigin("*")
@RestController

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class youtube_api {
		
	String path_json = "C:\\Users\\I340909\\Desktop\\result.json";
	String apiKey = "AIzaSyB3VZKsn0XgSngv-u2b8lcOJjJwVSC584g";
			
	private static YouTube youtube;

	@RequestMapping("/getMovieName")
	public String getMovieName()
	{
		String responseBody = null;
		HttpResponse response = null;
		int counter = 0;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://54.169.34.162:5252/video");
		while(counter < 3)
		{
			try 
			{
				response = client.execute(request);
				int statusCode = response.getStatusLine().getStatusCode();
			    System.out.println(statusCode);
				if (statusCode != 200){
					System.out.println("In if");
					response.getEntity().consumeContent();
					counter+=1;
					continue;
				}
				else 
				{
					responseBody = EntityUtils.toString(response.getEntity());
					System.out.println("Movie name from APi is : "+ responseBody);
					break;
				}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
			
				e.printStackTrace();
			}
		}
			//}
			return responseBody;
			
			
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
@RequestMapping("/getMovieID")
public String getMovieID(String movieName) throws IOException
{
	String rId ="";
	youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
	            public void initialize(HttpRequest request) throws IOException {
	            }
	        }).setApplicationName("youtube-cmdline-search-sample").build();

	YouTube.Search.List search = youtube.search().list("id,snippet");
	
	//String movie = getMovieName();
	
	//String apiKey = "AIzaSyDk7_07DXXrZyqOocsw58DZO2Sn42TDl9U";
	search.setKey(apiKey);
	search.setQ(movieName);
	search.setType("video");
	        // To increase efficiency, only retrieve the fields that the
	        // application uses.
	
	search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
	search.setMaxResults((long) 1);
	        //System.out.println("Moved on");
	        // Call the API and print results.
	SearchListResponse searchResponse = search.execute();
	List<SearchResult> searchResultList = searchResponse.getItems();
	if (searchResultList != null) 
	{
	    rId = prettyPrint1(searchResultList.iterator(), movieName);
	}
	        //System.out.println(rId);
			return (rId) ;
	}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@RequestMapping("/getMovieList")
public String getRelatedMovies(String MovieId,String movieName) throws IOException
{
	youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
	            public void initialize(HttpRequest request) throws IOException {
	            }
	        }).setApplicationName("youtube-cmdline-search-sample").build();

	YouTube.Search.List search = youtube.search().list("id,snippet");

	//String apiKey = "AIzaSyDk7_07DXXrZyqOocsw58DZO2Sn42TDl9U";
	search.setKey(apiKey);
			//search.setQ("Jonathan Rende, VP Product Marketing for Software at HP at STeP-IN SUMMIT 2008");
	search.setType("video");
	//String MovieId = getMovieID();
	System.out.println(MovieId);
	search.setRelatedToVideoId(MovieId);

	        // To increase efficiency, only retrieve the fields that the
	        // application uses.
	search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
	search.setMaxResults((long) 10);

	        // Call the API and print results.
	SearchListResponse searchResponse = search.execute();
	List<SearchResult> searchResultList = searchResponse.getItems();
	if (searchResultList != null) {
	        	//System.out.println("In if");
	    prettyPrint(searchResultList.iterator(), "Jonathan Rende, VP Product Marketing for Software at HP at STeP-IN SUMMIT 2008");
	    createJSON(searchResultList.iterator(),movieName);
	   }
			return "";
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private static String prettyPrint1(Iterator<SearchResult> iteratorSearchResults, String query) {
			String id = "";

	        /*System.out.println("\n=============================================================");
	        System.out.println(
	                "   First " + 30 + " videos for search on \"" + query + "\".");
	        System.out.println("=============================================================\n");*/

	        if (!iteratorSearchResults.hasNext()) {
	            System.out.println(" There aren't any results for your query.");
	        }

	        while (iteratorSearchResults.hasNext()) {

	            SearchResult singleVideo = iteratorSearchResults.next();
	            ResourceId rId = singleVideo.getId();
	            id = rId.getVideoId();

	            // Confirm that the result represents a video. Otherwise, the
	            // item will not contain a video ID.
	            if (rId.getKind().equals("youtube#video")) 
	            {
	                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
	            }
	        }
	        return id;
	    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
		private static void prettyPrint (Iterator<SearchResult> iteratorSearchResults, String query) {

	        if (!iteratorSearchResults.hasNext()) {
	            System.out.println(" There aren't any results for your query.");
	        }

	        while (iteratorSearchResults.hasNext()) {

	            SearchResult singleVideo = iteratorSearchResults.next();
	            ResourceId rId = singleVideo.getId();
	            //id = rId.getVideoId();

	            // Confirm that the result represents a video. Otherwise, the
	            // item will not contain a video ID.
	            if (rId.getKind().equals("youtube#video")) {
	                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

	               System.out.println(" Video Id" + rId.getVideoId());
	                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
	                System.out.println(" Thumbnail: " + thumbnail.getUrl());
	                System.out.println("\n-------------------------------------------------------------\n");
	            }
	        }
	       // return id;
	    }
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	public void createJSON(Iterator<SearchResult> iteratorSearchResults, String movieName)
	{
	    List<String> movieNames = new ArrayList<String>();
	    JSONObject videoDetails = new JSONObject();
	    
	    videoDetails.put("team", "SAPLabsIndia1");
	    videoDetails.put("video",movieName );
	    JSONArray jsonArray = new JSONArray();

	    int s = 1;
	    while (iteratorSearchResults.hasNext()) 
	    {
	       SearchResult singleVideo = iteratorSearchResults.next();
	       jsonArray.add(singleVideo.getSnippet().getTitle());
	       movieNames.add(singleVideo.getSnippet().getTitle());
	             
	    }
	        
	    videoDetails.put("upcoming-videos", movieNames);
	    JSONObject videoObject = new JSONObject();
	        //videoObject.put("videos", videoDetails);
	    try (FileWriter file = new FileWriter(path_json)) {
	          JSONArray videoList = new JSONArray();
	          videoList.add(videoObject);
	          file.write(videoDetails.toJSONString());
	          file.flush();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	                    
	       }
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

		public String postData() throws ClientProtocolException, IOException
	       {
	       ProcessBuilder process = new ProcessBuilder("curl", "-X", "POST", "http://54.169.34.162:5252/upload", "-H", "\'content-type:multipart/form-data\'", "-F", "\"file=@"+path_json+"\""); 
	        Process p = process.start();
	        InputStream is = p.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        StringBuilder responseStrBuilder = new StringBuilder();

	        String line = new String();

	        while ((line = br.readLine()) != null) {
	            System.out.println("read line from curl command: " + line);
	            return line;
	            
	        }
	        return "";
	       }
	        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	        
	       
	       @RequestMapping("/validate")
	       public void Validate() throws ClientProtocolException, IOException
	       {
	             String id = postData();
	             String url = "http://54.169.34.162:5252/result/"+id;
	             ProcessBuilder process = new ProcessBuilder("curl", "-iX", "GET",url ); 
	        Process p = process.start();
	        InputStream is = p.getInputStream();
	        System.out.println(url);
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        StringBuilder responseStrBuilder = new StringBuilder();

	        String line = new String();

	        while ((line = br.readLine()) != null) {
	            System.out.println("Read line from curl command: " + line);
	            responseStrBuilder.append(line);
	        }
	        
	       
	       }

		
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


