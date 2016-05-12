package part3;

import part3.Commands;

public class Launcher {
	
	public static void main(String[] args){
		Commands cmd = new Commands(Integer.parseInt(args[0]),args[1],args[2]);
		ImgIO.readImg(cmd.getInUrl());
		KMeans km = new KMeans(cmd.getK(),ImgIO.getImg());
		km.start();
		km.convert();
		ImgIO.setImg(km.getImg());
		ImgIO.writeImg(cmd.getOutUrl());
	}
}
