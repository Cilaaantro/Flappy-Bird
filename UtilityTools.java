/**
 * @(#)UtilityTools.java
 *
 *
 * @author 
 * @version 1.00 2023/5/13
 */
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Color;
public class UtilityTools {
	GamePanel gp;
	Robot robo;
	Rectangle screenRect;
	int[][] convoKernel;
	BufferedImage image;
//	Color [][]colors=new Color[718][718];
	int[][] rgbVal=new int[720][720];

    public UtilityTools(GamePanel gp) {
    	this.gp=gp;
    	try{
    		robo = new Robot();
    	}
    	catch(Exception e){
    		
    	}
        
        //convoKernel = new int[][]{{1,2,1},{2,4,2},{1,2,1}};
        convoKernel = new int[][]{{1,4,7,4,1},{4,16,26,16,4},{7,26,41,26,7},{4,16,26,16,4},{1,4,7,4,1}};
    	
    }
    
    public BufferedImage getImage(){
    	try{
    		image = new BufferedImage(gp.getWidth(), gp.getHeight(), BufferedImage.TYPE_INT_RGB);
            gp.paint(image.getGraphics());	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return image;
    	
    }
    
    public void blurImage(BufferedImage image){
    	int sumR=0;
    	int sumG=0;
    	int sumB=0;
    	int sumA=0;
    	int avR=0;
    	int avG=0;
    	int avB=0;
    	int avA=0;
    	int kernCount=0;
    	for(int y=0;y<gp.screenWidth;y++){
    		for(int x=0;x<gp.screenHeight;x++){
    			for(int i=0;i<convoKernel.length;i++){
    				for(int k=0;k<convoKernel[i].length;k++){
    					if(x-2+k>=0&&y-2+i>=0&&x-2+k<=719&&y-2+i<=719){			
    						int pixel = image.getRGB(x - 2+k, y - 2+i);
                    		sumR += ((pixel >> 16) & 0xFF)*convoKernel[k][i];
                    		sumG += ((pixel >> 8) & 0xFF)*convoKernel[k][i];
                    		sumB += (pixel & 0xFF)*convoKernel[k][i];
                    		sumA+=((pixel >> 24) & 0xFF)*convoKernel[k][i];
                    		kernCount+=convoKernel[k][i];
    					}
    				}
    			}
    			avR=sumR/kernCount;
    			avB=sumB/kernCount;
    			avG=sumG/kernCount;
    			avA=sumA/kernCount;
    			sumR=0;
    			sumB=0;
    			sumG=0;
    			sumA=0;
    			rgbVal[x][y] = (avA << 24)| (avR << 16) | (avG << 8) | avB;
    			kernCount=0;
    			
    			
    		}
    	}
		for(int y=0;y<gp.screenHeight;y++){
			for(int x=0;x<gp.screenWidth;x++){
				image.setRGB(x,y,rgbVal[x][y]);
			}
		}
 
    	
    	
    }
    
    
}
