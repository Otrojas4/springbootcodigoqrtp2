package pe.edu.upc.serviceImpl;

import java.io.ByteArrayOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class ZHingHelper {
	public static byte [] getQRCodeImage (String id, int width, int height)
	{
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(id, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream ();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream );
				
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			System.err.print(e.getMessage());
			return 	 null;
		}
		
		
		
	}
	

	
	
	
}
