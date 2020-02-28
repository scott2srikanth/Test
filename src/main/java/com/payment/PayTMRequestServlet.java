package com.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paytm.pg.merchant.CheckSumServiceHelper;


/**
 * Servlet implementation class PayTMRequestServlet
 */
public class PayTMRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int order_id = 123;//unique id 
		TreeMap< String, String> parameters = new TreeMap<String, String>();
		parameters.put("ORDER_ID", String.valueOf(order_id));
		parameters.put("CUST_ID", "CUST001");
		parameters.put("INDUSTRY_TYPE_ID", "Retail");
		parameters.put("CHANNEL_ID", "WEB");
		//parameters.put("TXN_AMOUNT", String.valueOf(rst_appnt.getDouble(3)));
		parameters.put("TXN_AMOUNT", "1");
		parameters.put("MID", "WorldP64425807474247");
		parameters.put("CHANNEL_ID", "WEB");
		parameters.put("INDUSTRY_TYPE_ID", "Retail");
		parameters.put("WEBSITE", "worldpressplg");
		parameters.put("MOBILE_NO", "9550930350");
		parameters.put("EMAIL", "test@gmail.com");
		parameters.put("CALLBACK_URL", "http://localhost:8080/pgResponse.jsp");
		String checkSum = null;
		try {
			checkSum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("xxxMKxx", parameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuilder outputHtml = new StringBuilder();
		outputHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
		outputHtml.append("<html>");
		outputHtml.append("<head>");
		outputHtml.append("<title>Merchant Check Out Page</title>");
		outputHtml.append("</head>");
		outputHtml.append("<body>");
		outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");
		outputHtml.append("<form method='post' action='https://pguat.paytm.com/oltp-web/processTransaction' name='f1'>");
		outputHtml.append("<table border='1'>");
		outputHtml.append("<tbody>");

		for (Map.Entry<String, String> entry : parameters.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    outputHtml.append("<input type='hidden' name='" + key + "' value='" + value + "'>");
		}

		outputHtml.append("<input type='hidden' name='CHECKSUMHASH' value='" + checkSum + "'>");

		outputHtml.append("</tbody>");
		outputHtml.append("</table>");
		outputHtml.append("<script type='text/javascript'>");
		outputHtml.append("document.onload=document.f1.submit();");
		outputHtml.append("</script>");
		outputHtml.append("</form>");

		out.print(outputHtml);
	}


}
