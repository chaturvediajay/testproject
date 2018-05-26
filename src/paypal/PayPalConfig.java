package paypal;

import javax.servlet.http.HttpServletRequest;

public class PayPalConfig {

	private String authToken;
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getPostrrl() {
		return postrrl;
	}
	public void setPostrrl(String postrrl) {
		this.postrrl = postrrl;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getReturnurl() {
		return returnurl;
	}
	public PayPalConfig getConfig(HttpServletRequest request)
	{
		PayPalConfig pc=new PayPalConfig();
		try
		{
			
			pc.authToken=request.getServletContext().getInitParameter("authToken");
			pc.postrrl=request.getServletContext().getInitParameter("postrrl");
			pc.returnurl=request.getServletContext().getInitParameter("returnurl");
			pc.cancelurl=request.getServletContext().getInitParameter("cancelurl");
		}
		catch(Exception ex)
		{
			pc=null;
		}
		return pc;
	}
	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}
	public String getCancelurl() {
		return cancelurl;
	}
	public void setCancelurl(String cancelurl) {
		this.cancelurl = cancelurl;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	private String postrrl;
	private String business;
	private String returnurl;
	private String cancelurl;
	private String cmd;
	public String toString()
	{
		return authToken+" 1 "+postrrl+" 2 "+business+" 3 "+returnurl+" 5 "+cancelurl+" 6 "+cmd;
	}

}
