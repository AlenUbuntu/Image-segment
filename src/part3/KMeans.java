package part3;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import part3.Pixel;

public class KMeans {

	private BufferedImage img;
	private int width;
	private int height;
	private int minWid;
	private int minHei;
	private Pixel[] cent;
	private HashMap<Integer,ArrayList<Pixel>> groups;
	private Pixel[] dataset;
	private int k;
	
	public KMeans(int k,BufferedImage img){
		this.img = img;
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.minWid = img.getMinX();
		this.minHei = img.getMinY();
		this.k = k;
		this.cent = new Pixel[this.k];
		this.groups = new HashMap<>();
		randomGenerate();
		this.dataset = new Pixel[this.width*this.height];
		generateData();
	}
	
	
	private void randomGenerate(){
		Random r = new Random();
		
		ArrayList<Integer> tmpX = new ArrayList<>();
		ArrayList<Integer> tmpY = new ArrayList<>();
		
		for(int i=0;i<this.k;){
			int x = r.nextInt(this.width-this.minWid)+this.minWid;
			int y = r.nextInt(this.height-this.minHei)+this.minHei;
			if(!tmpX.contains(x) && !tmpY.contains(y)){
				tmpX.add(x);
				tmpY.add(y);
				this.cent[i] = new Pixel(x,y,this.img.getRGB(x, y));
				i++;
			}
		}
	}
	
	private void generateData(){
		for(int i=0;i<this.k;i++)
			this.groups.put(i, new ArrayList<Pixel>());
		int k=0;
		for(int i=this.minWid;i<this.width;i++)
			for(int j=this.minHei;j<this.height;j++)
				this.dataset[k++] = new Pixel(i,j,this.img.getRGB(i, j));
	}
	
	private double dist(int idx, Pixel p){
		Pixel c = this.cent[idx];
		return dist(c,p);
	}
	
	private double dist(Pixel c, Pixel p){
		if(c!=null){
			double tmp = Math.pow((c.getR()-p.getR()),2)+Math.pow((c.getG()-p.getG()), 2)+Math.pow((c.getB()-p.getB()),2);
			return Math.sqrt(tmp);
		}
		else
			return Double.MAX_VALUE;
	}
	
	private int checkGroup(Pixel p){
		int gId = 0;
		double lastDist = dist(gId,p);
		
		for(int i=1;i<this.cent.length;i++){
			double dist = dist(i,p);
			if(dist<lastDist){
				gId = i;
				lastDist = dist;
			}
		}
		return gId;
	}
	
	private void groupData(){
		// clear the lists
		for(int i=0;i<this.groups.size();i++){
			this.groups.get(i).clear();
		}	
		
		// check group for each pixel and update group
		for(int i=0;i<this.dataset.length;i++){
			int gId = checkGroup(this.dataset[i]);
			this.groups.get(gId).add(this.dataset[i]);
		}
		
		// update means
		for(int i=0;i<this.cent.length;i++){
			
			ArrayList<Pixel> tmp = this.groups.get(i);
			int totalR=0;
			int totalG=0;
			int totalB=0;
			for(int j=0;j<tmp.size();j++){				
				Pixel p = tmp.get(j);
				totalR += p.getR();
				totalG += p.getG();
				totalB += p.getB();				
			}
			if(tmp.size()!= 0){
				int color = (int)(totalR/tmp.size());
				color = (color<<8)+(int)(totalG/tmp.size());
				color = (color<<8)+(int)(totalB/tmp.size());
				this.cent[i]=new Pixel(-1,-1,color);
			}
			else{
				this.cent[i]=null;
			}
		}
	}
	
	public void start(){
		int iter = 0;
		Pixel[] lastIdList = Arrays.<Pixel>copyOf(this.cent,this.cent.length);
		while(iter<100){
			System.out.println("iteration: "+iter);
			iter++;
			groupData();
			if(Arrays.deepEquals(lastIdList, this.cent))
				break;
			else
				lastIdList = Arrays.<Pixel>copyOf(this.cent,this.cent.length);
		}
	}	
	
	public void convert(){
		for(int i=0;i<this.cent.length;i++){
			Pixel c = this.cent[i];
			if(c!=null){
				ArrayList<Pixel> tmp = this.groups.get(i);
				for(int j=0;j<tmp.size();j++){
					Pixel t = tmp.get(j);
					t.setRGB(c.getRGB());
					this.img.setRGB(t.getX(),t.getY(),c.getRGB());
				}
			}
		}
	}
	
	public BufferedImage getImg(){
		return this.img;
	}	
}
