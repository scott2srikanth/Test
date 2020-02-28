package com.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class QRCodeGenerator {
	
	public static ByteArrayOutputStream generateQR() throws FileNotFoundException, DocumentException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, bout);
		
        document.open();


        BarcodeQRCode barcodeQRCode = new BarcodeQRCode("Hackathon", 1000, 1000, null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(100, 100);
        
        
        document.add(codeQrImage);

        document.close();
        return bout; 
	}

}
