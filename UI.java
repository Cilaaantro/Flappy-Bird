import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Color;
import java.text.DecimalFormat;
import java.awt.image.BufferedImage;
public class UI
  {
    Graphics2D g2;
    double timer = 0.0;
    double increment = 1.0/60;
    GamePanel gp;
    DecimalFormat decimalFormat;
    BufferedImage pauseImage;
    Font myFont = new Font("Monospaced", Font.BOLD, 30);
    public int blurCount=0;
    public UI(GamePanel panel)
    {
      gp = panel;
      decimalFormat = new DecimalFormat("#00");
    }
    public void draw(Graphics2D g2)
    {
      this.g2 = g2;
      drawTimer();
      drawScore();
      if(gp.gameState == gp.pauseState){
        drawGaussianBlur();
        drawPaused();
      }
    }
    public void update()
    {
      if(gp.gameState == gp.playState)
      {
        timer += increment;
      }
      else if(gp.gameState == gp.pauseState)
      {
        
      }
    }
    
    public void drawTimer(){
    	
       	int minute = 0;
      	g2.setColor(Color.white);
      	g2.setFont(myFont);
      	if(timer > 60)
    	{
      		timer -= 60;
      		minute += 1;
    	}
      	g2.drawString("Time Elasped: " + minute + ":" + decimalFormat.format(timer),0,30);
    	
    }
    public void drawScore()
    {
      g2.setFont(myFont);
      g2.setColor(Color.white);
      g2.drawString("SCORE: " + gp.score, 520,30);
    }
   public void drawPaused()
   {
      g2.setFont(new Font("Monospaced", Font.BOLD, 90));
      g2.setColor(Color.white);
      g2.drawString("PAUSED", centerTextHorz("PAUSED"),centerTextVert("PAUSED"));
   } 
    public void drawGaussianBlur(){
      if(blurCount<10){
        blurCount++;
        gp.uTool.blurImage(pauseImage);
      }
    	g2.drawImage(pauseImage,0,0,720,720,null);
    }
    public int centerTextHorz(String text) {
      int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
      int x=gp.screenWidth/2-length/2;
      return x;
  }
  public int centerTextVert(String text) {
    int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getHeight();
    int x=gp.screenHeight/2-length/2;
    return x + 50;
}
  }