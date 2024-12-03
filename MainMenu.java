import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
public class MainMenu extends JPanel implements ActionListener{
    JButton startButton;
    JButton settingsButton;
    JButton closeButton;
    JFrame frame;
    GamePanel gp;
    Font flappyFont;
    int width = 400;
    int height = 50;
    BufferedImage bg;
    BufferedImage birdIdle,birdUp,birdDown,currentImage;
    ImageIcon hoverButton,exitButton;
    int spriteCount =0;
    int frameCount =0;
    Timer t;
    boolean settingsState = false;
    JRadioButton onMusic;
    MainMenu(JFrame frame, GamePanel gp)
    {
    	this.setLayout(null);
        this.setPreferredSize(new Dimension(720,720));
        this.setBackground(Color.pink);
        this.gp = gp;
        this.frame = frame;
        startButton = new JButton("Start");
       	settingsButton = new JButton("Settings");
       	closeButton = new JButton("Quit Game");
       	onMusic = new JRadioButton("Play Music?",true);
       	startButton.setBounds((720 - width) / 2,150 + 330,width,height);
       	settingsButton.setBounds((720-width) / 2, 150+400,width,height);
       	closeButton.setBounds((720 - width) / 2,150 + 470,width,height);
   		startButton.addActionListener(this);
   		settingsButton.addActionListener(this);
   		closeButton.addActionListener(this);
        this.add(startButton);
       	this.add(settingsButton);
       	this.add(closeButton);
        revalidate();
        makeFont();
        getBG();
        getBirdAnimation();
        Timer t = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        setButton();
        t.start();
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == startButton)
        {
            frame.remove(this);
            frame.add(gp);
            gp.setUpGame();
		    gp.startGameThread();
            frame.revalidate();
            gp.requestFocusInWindow();
            gp.playMusic(5);
        }
        if(e.getSource() == settingsButton)
        {
        	settingsState = true;
        	this.remove(startButton);
        	this.remove(settingsButton);
        	this.remove(closeButton);
        	onMusic.setBounds((720 - width) / 2,150 + 330,width,height);
        	this.revalidate();
        }
        if(e.getSource() == closeButton)
        {
        	frame.dispose();
        }
    }
    public void paintComponent(Graphics g){
    	
    	//G2 OBJECT
		Graphics2D g2= (Graphics2D)g;
		
		//BACKGROUND
        g2.drawImage(bg,0,0,720,720,null);
        
        //TITLE
        g2.setColor(Color.white);
        g2.setFont(flappyFont);
		g2.drawString("FLAPPY BIRD", centerTextHorz("FLAPPY BIRD",g2),150);
		
		//BIRD ANIMATION
		if(!settingsState)
		drawBird(g2);
		
	}
    public int centerTextHorz(String text,Graphics2D g2) {
        
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;
    }
    public int centerTextVert(String text,Graphics2D g2) {
    int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getHeight();
    int x=gp.screenHeight/2-length/2;
    return x;
	}
    public void makeFont(){
    	try{
    		flappyFont=Font.createFont(Font.TRUETYPE_FONT,new File("font/FlappyBirdy.ttf")).deriveFont(200f);
    		GraphicsEnvironment ge =GraphicsEnvironment.getLocalGraphicsEnvironment();
    		ge.registerFont(flappyFont);
    	}
    	catch(IOException|FontFormatException e){
    		
    	}
    	
    }
    
    public void getBG(){
    	try {
    		bg =ImageIO.read(getClass().getResourceAsStream("/background/background.png"));
			 
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
    }
    public void getBirdAnimation(){
    	try {
    		birdIdle =ImageIO.read(getClass().getResourceAsStream("/bird/flappy_bird1.png"));
    		birdUp =ImageIO.read(getClass().getResourceAsStream("/bird/flappy_bird2.png"));
    		birdDown =ImageIO.read(getClass().getResourceAsStream("/bird/flappy_bird3.png"));
			 
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 currentImage=birdIdle;
    	
    }
    public void drawBird(Graphics2D g2){
    	g2.drawImage(currentImage,232,170,256,256,null);
    	frameCount++;
    	if(frameCount==2){
    		spriteCount++;
    		if(spriteCount==4)
    			spriteCount=0;
    		
    		if(spriteCount==0)
    			currentImage=birdIdle;
    		if(spriteCount==1)
    			currentImage=birdUp;
    		if(spriteCount==2)
    			currentImage=birdIdle;
    		if(spriteCount==3)
    			currentImage=birdDown;
    		frameCount=0;
    	}
    	
    }
    public void setButton(){
    	 exitButton=new ImageIcon("/exit.png");
		 hoverButton=new ImageIcon("/hover.png");
		 startButton.setIcon(exitButton);
		 startButton.setRolloverIcon(hoverButton);
    }
}
