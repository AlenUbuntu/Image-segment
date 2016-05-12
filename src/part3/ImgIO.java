package part3;

import java.awt.image.BufferedImage;  
import java.io.File;
import javax.imageio.ImageIO;

public class ImgIO{

	private static BufferedImage img;
	
	public static void readImg(String path){
		try{
			File f = new File(path);
			img = ImageIO.read(f);
			System.out.println("read completed");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void writeImg(String path){
		try{
			File f = new File(path);
			ImageIO.write(img, "JPEG", f);
			System.out.println("write completed");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getImg(){
		return img;
	}
	
	public static void setImg(BufferedImage img2){
		img = img2;
	}
}