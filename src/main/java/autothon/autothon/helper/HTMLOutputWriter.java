package autothon.autothon.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HTMLOutputWriter {

		FileWriter fw;

		public HTMLOutputWriter(String location, String title) {
		//	LocalDateTime now = LocalDateTime.now();
		//	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"); 
		//	String formattedDate = now.format(format);			
			try {
				fw = new FileWriter(location);
				fw.write("<!DOCTYPE html>");
				fw.write("<html>");
				fw.write("<head>");
				
				fw.write("<title>"+title+"</title>");
				fw.write(" <style>"
						+ "body{"
						+ "		//background-color:#c2d4e7;"
						+ "		background-color:#fafafa;"
						+ "}"
						+ "table{"
						+ "		padding-right:0px;"
						+ "		padding-left:0px;"
						+ "		margin-left:150px;"
						+ "		margin-right:40px;"
						+ "		//border:5px inset #345543;"
						+ "}"
						+ "td,th{"
						+ "		height:30px;"
						+ "		width:290px;"
						+ "		text-align:left;"
						+ "		padding-left:20px;"
						+ "		padding-bottom:18px;"
						+ "		font-family:Arial;"
						+ "		//border-bottom:1.2px solid #A8A8A8;"
						+ "		border-bottom:1.2px solid #427cac;"
						+ "}"
						+ "tr{"
						+ "		padding:20px;"
						+ "}"
						+ "tr:nth-child(odd){"
						+ "		background-color:#FFFFFF;"
						+ "}"
						+ ".success{"
						+ "		border:4px solid #007785;"
						+ "		height:18px;"
						+ "		width:18px;"
						+ "		border-radius:5px;"
						+ "}"
						+ ".fail{"
						+ "		border:4px solid #d14747;"
						+ "		border-radius:15px;"
						+ "		height:18px;"
						+ "		width:18px;"
						+ "		color:#d14747"
						+ "}"
						+ "tr:nth-child(even){"
						+ "		background-color:#F0F0F0;"
						+ "		//background-color:#eff4f9;"	
						+ "}"
						+ ""
						+ "</style>"
						+ "<script>"
						+ "		function toggleTable(id,id1)"
						+ "		{"
						+ "			var x = document.getElementById(id);"
						+ "			var y = document.getElementById(id1);"
						+ "			if(x.style.display==\"none\")"
						+ "			{"
						+ "				x.style.display = \"block\";"
						+ "				y.value = \"-\";"
						+ "			}"
						+ "			else"
						+ "			{"
						+ "				x.style.display = \"none\";"
						+ "				y.value = \"+\";"
						+ "			}	"
						+ "		}"
						+ "</script>"
						+ "");
				fw.write("</head>");
				fw.write("<body>"
						+ "<div align=\"\">");
				fw.write("<div align=\"center\">"
						+ "		<h2>"+title+"</h2>"
						+ "		<h4>Test run :  </h4>"
						+ "</div>"
						+ "<table width=\"1200\">"
						+ "		<tr><th>Step Action</th><th>Input data</th><th>Exported Data</th><th><div align='center'>Status</div></th><th>Screenshot</th></tr>"
						+ ""
						+ "</table><br>");
				
							
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void createTable(String name)
		{
			try {
				String m[] = name.split(" ");
				String p = "";
				String q = "";
				for(int i=0;i<m.length;i++)
				{
					p+=m[i];
					q+=p+"34";
				}
				this.fw.write("<table width=\"1200\">");
				this.fw.write("<tr><th colspan=4>"
						+ "<input type=\"button\" value=\"+\" id=\""+q+"\" style=\"background-color:#ffffff; font-size:22px;color:#2557a3;border:none;\" onclick=\"toggleTable('"+p+"','"+q+"')\" />"
						+ ""+name+"</th></tr><table id="+p+" width='1200' style='display:none'>");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		/**
		 * Takes input Action, input data, transaction data, status
		 */
		public void insertRow(String action, String test, String generated,String screenshot, int status)
		{
			try {
				this.fw.write("<tr><td>"+action+"</td><td>"+test+"</td>");
				if(status==1)
					this.fw.write("<td>"+generated+"</td><td><div align=\"center\"><div class=\"success\"></div></div></td><td><a target='blank' href='"+screenshot+"'>screenshot</a></td></tr>");
				else
					this.fw.write("<td>"+generated+"</td><td><div align=\"center\"><div align=\"center\" class=\"fail\">x</div></div></td><td><a target='blank' href='"+screenshot+"'>screenshot</a></td></tr>");

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void closeTable(int status)
		{
			try {
				
				this.fw.write("</table>");
				if(status==1)
					this.fw.write("<table width='1200'><tr><th colspan='2'>Overall Status</th><div align=\"center\"><th></th><th><div align=\"center\" style=\"color:#007785;\">Passed</div></th></div></tr></table><br><br>");
				else
					this.fw.write("<table width='1200'><tr><th colspan='2'>Overall Status</th><div align=\"center\"><th></th><th><div align=\"center\" style=\"color:#d14747\">Failed</div></th></div></tr></table><br><br>");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void closeHTML()
		{
			try {
				this.fw.write("</div></body>"
					+ "</html>");
				this.fw.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void cleanup() {
			try {
				this.fw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
