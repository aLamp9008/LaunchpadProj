package Sound;
import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IsKeyPressed
extends Applet
implements KeyListener{
	
	public void init(){
		this.addKeyListener(this);
		testingSound.sounds.put(90, "kick");
		testingSound.sounds.put(88, "snare");
		testingSound.sounds.put(67, "car");
		testingSound.sounds.put(81, "Sound1");
		testingSound.sounds.put(87, "Sound2");
		testingSound.sounds.put(69, "Sound3");
		testingSound.sounds.put(65, "Sound4");
		testingSound.sounds.put(83, "Sound5");
		testingSound.sounds.put(68, "Sound6");
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int a = e.getKeyCode();
				
		testingSound.test(a);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int a = e.getKeyCode();
		
	}
	
	
	
}