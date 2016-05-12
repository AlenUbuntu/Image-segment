package part3;

public class Pixel {

	private int x;
	private int y;
	private int color;
	private int[] rgb = new int[3];
	
	public Pixel(int x, int y, int color){
		this.x = x;
		this.y = y;
		this.color = color;
		decode(color);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getR(){
		return this.rgb[0];
	}
	
	public int getG(){
		return this.rgb[1];
	}
	
	public int getB(){
		return this.rgb[2];
	}
	
	public int getRGB(){
		return this.color;
	}
	
	public void setRGB(int color){
		decode(color);
	}
	
	private void decode(int color){
		this.rgb[0] =(color>>16)&0xff;
		this.rgb[1] =(color>>8)&0xff;
		this.rgb[2] =(color)&0xff;	
	}
}
