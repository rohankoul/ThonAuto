package autothon.autothon.helper;

public class jsonResponseObject {
	int step;
	String actionType;
	String label;
	String value;
	String status;
	String logForApi;
	String tech;
	
	public jsonResponseObject(int step, String actionType, String label, String value, String status, String logForApi,
			String tech) {
		super();
		this.step = step;
		this.actionType = actionType;
		this.label = label;
		this.value = value;
		this.status = status;
		this.logForApi = logForApi;
		this.tech = tech;
	}
	
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLogForApi() {
		return logForApi;
	}
	public void setLogForApi(String logForApi) {
		this.logForApi = logForApi;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
}
