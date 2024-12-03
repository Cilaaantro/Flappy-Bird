/**
 * @(#)Pipe.java
 *
 *
 * @author 
 * @version 1.00 2023/5/3
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;


public class Pipe {
	final int width=120;
	final int pipeWidth=80;
	final int gapHeight=250;
	public int gapY;
	public int topPipeHieght;
	public int bottomPipeHieght;
	public int topPipeY;
	public int bottomPipeY;
	GamePanel gp;
	
	final int bellWidth=104;
	final int bellHeight=44;
	
	
	public int xPos;
	public int speed =2;
	
	public Rectangle topPipe;
	public Rectangle bottomPipe;
	public Rectangle score;
	
	public Rectangle topBell,bottomBell;
	
	BufferedImage topPipeImg, bottomPipeImg;
	

    public Pipe(int xPos, GamePanel gp) {
    	this.gp=gp;
    	setValues(xPos);
    }
    
    public void setValues(int xPos){
    	gapY=(int)(Math.random()*(gp.screenHeight-gapHeight-100))+100;
    	//gapY=400;
    	topPipeY=(gp.screenWidth-gapY)*-1;
    	bottomPipeY=gapY+gapHeight;
    	this.xPos=xPos;
    	topPipeHieght=gapY;
    	bottomPipeHieght=gp.screenHeight-gapY-gapHeight;
    	topPipe= new Rectangle(xPos+(width-pipeWidth)/2,topPipeY,pipeWidth,gp.screenHeight-bellHeight);
    	bottomPipe= new Rectangle(xPos+(width-pipeWidth)/2,bottomPipeY+bellHeight,pipeWidth,bottomPipeHieght-bellHeight);
    	score= new Rectangle(xPos + width / 2,gapY,2,gapHeight);
    	getPipeImage();
    	
    	topBell= new Rectangle(xPos+(width-bellWidth)/2,gapY-bellHeight,bellWidth,bellHeight);
    	bottomBell= new Rectangle(xPos+(width-bellWidth)/2,gapY+gapHeight,bellWidth,bellHeight);
    	
    }
    public void getPipeImage(){
    	try {
			 topPipeImg=ImageIO.read(getClass().getResourceAsStream("/pipe/top_pipe.png"));
			 bottomPipeImg=ImageIO.read(getClass().getResourceAsStream("/pipe/bottom_pipe.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
    }
    public void draw(Graphics2D g2){
    	g2.setColor(Color.black);
    	g2.drawImage(topPipeImg,xPos,topPipeY,width,gp.screenHeight,null);
    	g2.drawImage(bottomPipeImg,xPos,bottomPipeY,width,gp.screenHeight,null);
    }
    
    public void update(){
    	//System.out.println("hi");
    	if(xPos==width*-1-width)
    		setValues(gp.screenHeight);
    	else if(!gp.bird.collision){
    		xPos-=speed;
    	}
    	topPipe.x=xPos+(width-pipeWidth)/2;
    	bottomPipe.x=xPos+(width-pipeWidth)/2;
    	topBell.x=xPos+(width-bellWidth)/2;
    	bottomBell.x=xPos+(width-bellWidth)/2;
    	score.x = xPos + width / 2;
    }
    
    
}