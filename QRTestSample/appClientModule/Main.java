
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
 

public class Main {
	public static void main(String[] args) throws IOException {
        String content = "http://google.com.tw";
        String charsetName =  "UTF-8";
        String qrCodeImagePath = "D:/qrCode.jpg";
   
        int version = 2; 
        char errorCorrect = 'M';
        char encodeMode ='B';
   
        Color backgroundColor = Color.WHITE;
        Color paintColor = Color.BLACK;
        int matrixWidth = 3;
        int borderWidth = 2;
        String imageFormat = "png";
   
        int matrix = 17 + version * 4;
        int imageWidth = (matrix)*matrixWidth+borderWidth*2;
   
        Qrcode qrcodeHandler = new Qrcode();
        qrcodeHandler.setQrcodeVersion(version);
        qrcodeHandler.setQrcodeErrorCorrect(errorCorrect);
        qrcodeHandler.setQrcodeEncodeMode(encodeMode); 

        byte[] contentBytes = content.getBytes(charsetName); 
       
        BufferedImage bufImg = new BufferedImage(imageWidth, imageWidth, BufferedImage.TYPE_INT_RGB); 
        Graphics2D gs = bufImg.createGraphics();   
        gs.setBackground(backgroundColor); 
        gs.clearRect(0, 0, imageWidth, imageWidth); 
        gs.setColor(paintColor);

        if (contentBytes.length > 0) { 
            boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes); 
            for (int i = 0; i < codeOut.length; i++) { 
                for (int j = 0; j < codeOut.length; j++) { 
                    if (codeOut[j][i]) { 
                        gs.fillRect(j * matrixWidth + borderWidth, i * matrixWidth + borderWidth, matrixWidth, matrixWidth); 
                    } 
                } 
            } 
        } 
       
        bufImg.flush(); 
   
        File imgFile = new File(qrCodeImagePath); 
        ImageIO.write(bufImg, imageFormat, imgFile); 
		// TODO Auto-generated method stub
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}