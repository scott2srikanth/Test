package com.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.qrcode.QRCodeGenerator;

/**
 * Servlet implementation class PDFServlet
 */
public class PDFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf;charset=UTF-8");

        response.addHeader("Content-Disposition", "inline; filename=" + "QRCode.pdf");
        ServletOutputStream out = response.getOutputStream();

        ByteArrayOutputStream baos = null;
		try {
			baos = QRCodeGenerator.generateQR();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        baos.writeTo(out);
		
	}

	

}
