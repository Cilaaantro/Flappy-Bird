

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
	
	Clip clip;//used to open audio file;
	URL soundURL[]=new URL[30];//Store path of sound files
	FloatControl mixer;
	Float level;
	
	public Sound() {
		soundURL[0]=getClass().getResource("/sound/score.wav");
		soundURL[1]=getClass().getResource("/sound/flap.wav");
		soundURL[2]=getClass().getResource("/sound/dead.wav");
		soundURL[3]=getClass().getResource("/sound/fall.wav");
		soundURL[4]=getClass().getResource("/sound/Summer.wav");
		soundURL[5]=getClass().getResource("/sound/Overture.wav");
		soundURL[6]=getClass().getResource("/sound/Stardrop.wav");
		
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(soundURL[i]);
			clip=AudioSystem.getClip();
			clip.open(ais);
			mixer = (FloatControl)(clip.getControl(FloatControl.Type.MASTER_GAIN));
			
		}catch(Exception e) {
			
		}
		
	}
	public void play() {
		clip.start();
		
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	public void stop() {
		clip.stop();
		
	}
	public void setVol(int x) {
		mixer.setValue((float)x);
	}

}
