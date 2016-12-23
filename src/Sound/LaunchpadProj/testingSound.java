package LaunchpadProj;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class testingSound extends JFrame implements KeyListener {

	static HashMap<Integer, String> sounds = new HashMap<Integer, String>();

	static JTextField keyText = new JTextField(80);
	
	Player player;

	public testingSound() {
		player = new Player(sounds.keySet().toArray(new Integer[0]));
		keyText.addKeyListener(this);
		setSize(400, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(keyText, BorderLayout.CENTER);
		
		//(new Thread(player)).start();

	}

	public static void main(String[] args) {
		testingSound.sounds.put(KeyEvent.VK_Z, "kick.wav");
		testingSound.sounds.put(KeyEvent.VK_X, "snare.wav");
		testingSound.sounds.put(KeyEvent.VK_Q, "SSound1.wav");
		testingSound.sounds.put(KeyEvent.VK_W, "SSound2.wav");
		testingSound.sounds.put(KeyEvent.VK_E, "SSound3.wav");
		testingSound.sounds.put(KeyEvent.VK_R, "SSound4.wav");
		testingSound.sounds.put(KeyEvent.VK_T, "Soundd1.aif");
		testingSound.sounds.put(KeyEvent.VK_Y, "Back1.aif");
		testingSound.sounds.put(KeyEvent.VK_U, "Vocal.aif");
		
		testingSound.sounds.put(KeyEvent.VK_A, "Sound1.wav");
		testingSound.sounds.put(KeyEvent.VK_S, "Sound2.wav");
		testingSound.sounds.put(KeyEvent.VK_D, "Sound3.wav");
		testingSound.sounds.put(KeyEvent.VK_F, "Sound4.wav");
		testingSound.sounds.put(KeyEvent.VK_G, "Sound5.wav");
		testingSound.sounds.put(KeyEvent.VK_H, "Sound6.wav");
		

		new testingSound();
	}

	public static void playSound(int num) {
		// System.out.println(num);
		// System.out.println(sounds.get(key));
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// System.out.println(e.getKeyCode());
		// System.out.println(e.getKeyCode());
		// playSound(e.getKeyCode());
		player.play(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}

class Player implements Runnable {

	Queue<Integer> playing = new LinkedList<>();
	Queue<Integer> toPlay = new LinkedList<>();
	HashMap<Integer, Clip> clips = new HashMap<>();

	public Player(Integer... ids) {
		for (int i : ids) {
			String fileName = testingSound.sounds.get(i);
			Clip clip = null;
			try {
				clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				System.out.println("Failure to load file for id " + i);
			}
			clips.put(i, clip);
		}
	}

	public void play(int keyCode) {
		System.out.println(keyCode + "");
		if (!clips.keySet().contains(keyCode)) {
			return;
		}
		synchronized (this) {
			toPlay.add(keyCode);
			clips.get(keyCode).stop();
			clips.get(keyCode).setFramePosition(0);
			clips.get(keyCode).start();
		}
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				for (Iterator<Integer> i = toPlay.iterator(); i.hasNext();) {
					int id = i.next();
					if (clips.get(id).isRunning()) {
						playing.add(id);
						i.remove();
					}
				}
				for (Iterator<Integer> i = playing.iterator(); i.hasNext();) {
					int id = i.next();
					if (!clips.get(id).isRunning()) {
						i.remove();
					}
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// Ignore
			}
		}

	}
}
/*
class a extends Thread implements Runnable{
	private int num;
	public a(int b){
		num = b;
	}
	public void play(){
		System.out.println(num);
		String fileName = testingSound.sounds.get(num) + ".wav";
		try
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
			clip.start();
			while (!clip.isRunning())
			    Thread.sleep(10);

			while (clip.isRunning())
			    Thread.sleep(10);

			
			clip.close();
		}
		catch (Exception exc)
		{
			exc.printStackTrace(System.out);
		}
	}
	
	public void run(){
		play();
	}
	
	
}

*/