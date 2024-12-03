/**
 * @(#)PipeManager.java
 *
 *
 * @author 
 * @version 1.00 2023/5/5
 */
import java.awt.Graphics2D;


public class PipeManager {
	
	Pipe [] pipeList = new Pipe[3];
	public int distance;
	GamePanel gp;

    public PipeManager(GamePanel gp) {
    	this.gp=gp;
    	distance =(gp.screenWidth-(100*pipeList.length))/2;
    	setUpPipe();
    }
    public void setUpPipe(){
    	for(int x=0;x<pipeList.length;x++){
    		pipeList[x]=new Pipe(gp.screenWidth+(distance+100)*x,gp);
    	}
    }
    public void update(){
    	gp.bird.collision=false;
    	for(int x=0;x<pipeList.length;x++){
    		if(gp.cc.checkPipeCollision(gp.bird,pipeList[x]))
    			gp.bird.collision=true;
    		pipeList[x].update();
    		if(gp.keyH.restartPressed)
    		{
    			distance =(gp.screenWidth-(100*pipeList.length))/2;
    			setUpPipe();
    		}
    		if((gp.gameState == gp.playState||gp.gameState == gp.aiMode)&&gp.bird.collision!=true)
    		{
    			gp.cc.checkScoreCollision(gp.bird,pipeList[x]);
    		}
    	}
    }
    public void draw(Graphics2D g2){
    	for(int x=0;x<pipeList.length;x++){
    		pipeList[x].draw(g2);
    	}
    }
    
    
}