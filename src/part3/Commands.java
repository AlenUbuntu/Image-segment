package part3;

public class Commands {

	private int kNum;
	private String inUrl;
	private String outUrl;
	
	public Commands(int k, String in, String out)
	{
		this.kNum = k;
		this.inUrl = in;
		this.outUrl = out;
	}
	
	public int getK(){
		return this.kNum;
	}
	
	public String getInUrl()
	{
		return this.inUrl;
	}
	
	public String getOutUrl()
	{
		return this.outUrl;
	}
}
