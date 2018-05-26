package paypal;

import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

import javax.servlet.http.HttpServletRequest;



public class PayPalSucess {

	public PayPalResult getPayPal(HttpServletRequest request)
	{
		PayPalResult pr=new PayPalResult();
		System.out.println(pr);
		PayPalConfig pc=new PayPalConfig();
		System.out.println(pc);
		pc=pc.getConfig(request);
		String[] tmp=null;
		String res="";
		try
		{
			
			String transId=request.getParameter("tx");
			System.out.println(transId);
			String authtoken=pc.getAuthToken();
			System.out.println(transId+"  "+authtoken);
			String query="cmd=_notify-synch&tx="+transId+"&at="+authtoken;
			String url=pc.getPostrrl();
			URL u=new URL(url);
			HttpURLConnection req=(HttpURLConnection)u.openConnection();
			req.setRequestMethod("POST");
			req.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			req.setDoOutput(true);
			req.setDoInput(true);
			req.setFixedLengthStreamingMode(query.length());
			OutputStream outputStream=req.getOutputStream();
			outputStream.write(query.getBytes());
			outputStream.close();
			
			BufferedReader in=new BufferedReader(new InputStreamReader(req.getInputStream()));
			res=in.readLine();
			if(res.equals("sucess"))
			{
				while((res=in.readLine())!=null)
				{
					tmp=res.split("=");
					if(tmp.length==1)
						continue;
						tmp[1]=URLDecoder.decode(tmp[1],"UTF-8");
					
						if(tmp[0].equals("mc_gross")){
							pr.setMc_gross(tmp[1]);;
						}
						if(tmp[0].equals("protection_eligibility")){
							pr.setProtection_eligibility(tmp[1]);;
						}
						if(tmp[0].equals("address_street")){
							pr.setAddress_street(tmp[1]);;
						}
						if(tmp[0].equals("tax")){
							pr.setTax(tmp[1]);;
						}
						if(tmp[0].equals("payer_id")){
							pr.setPayer_id(tmp[1]);;
						}
						if(tmp[0].equals("address_status")){
							pr.setAddress_status(tmp[1]);;
						}
						if(tmp[0].equals("payment_status")){
							pr.setPayment_status(tmp[1]);;
						}
						if(tmp[0].equals("payer_id")){
							pr.setPayer_id(tmp[1]);;
						}
						if(tmp[0].equals("payer_id")){
							pr.setPayer_id(tmp[1]);;
						}
						if(tmp[0].equals("payment_date")){
							pr.setPayment_date(tmp[1]);;
						}
						
					
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			pr=null;
		}
		return pr;
	}

}
