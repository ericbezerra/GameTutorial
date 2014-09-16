package br.shussangames.game.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame implements KeyListener{
	
	private final int WIDTH = 200;
	private final int HEIGHT = 150;
	private final int SCALE = 3;
	private Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	private Thread thread;
	
	BufferedImage backBuffer;
	char teclaPressionada;
	int velocidade = 10;
	int x = 200;
	int y = 200;
	boolean running = true;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.run();
	}
	
	public void init(){
		setSize(dimension);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		backBuffer = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
		addKeyListener(this);
		
	}
	
	public void paint(){
		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		bbg.setColor(Color.WHITE);
		bbg.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		bbg.setColor(Color.BLACK);
		bbg.fillOval(x, y, 50, 50);
		g.drawImage(backBuffer, 0, 0, this);
	}

	public void run(){
		this.init();
		while(running){
			try{
			Thread.sleep(1000/30);
			}catch(InterruptedException e){
				e.printStackTrace();
				stop();
			}
			this.paint();
		}
	}
	
	public void stop(){
		running = false;
		try{
		thread.join();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		teclaPressionada = e.getKeyChar();
		
		if(e.getKeyCode() == KeyEvent.VK_A){
			x -= velocidade;
			System.out.println("pressed");
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			x += velocidade;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){
			y -= velocidade;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			y += velocidade;
		}
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
