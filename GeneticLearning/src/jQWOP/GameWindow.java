package jQWOP;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

public class GameWindow {

	private World world;
	private ArrayList<Body> bodyList;
	private JFrame GUI;
	
	public GameWindow(){
		GUI = createAndShowGUI();
		
		world = new World(new Vec2(0.0f, 0.02f));
		bodyList = new ArrayList<Body>();
		
		CreateBall(20, 20);
		
		GameLoop();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				GameWindow gameWindow = new GameWindow();
			}
		});
	}
	
	public JFrame createAndShowGUI(){
		System.out.println("GUI created on Thread #" + Thread.currentThread().getId());
		JFrame frame = new JFrame("Get the drawing working!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.add(new MyPanel());
		//frame.pack();
		frame.setVisible(true);
		return frame;
	}

	private void GameLoop(){
		
		BodyDef shelfDef = new BodyDef();
		shelfDef.position.set(0, 200);
		shelfDef.type = BodyType.STATIC;
		
		PolygonShape ps = new PolygonShape();
		Vec2[] vert = new Vec2[4];
		vert[0] = new Vec2(0, 200);
		vert[1] = new Vec2(200, 200);
		vert[2] = new Vec2(0, 220);
		vert[3] = new Vec2(200, 220);
		ps.set(vert, vert.length);
		
		FixtureDef fixDef = new FixtureDef();
		fixDef.shape = ps;
		fixDef.density = 0.5f;
		fixDef.friction = 0.5f;
		fixDef.restitution = 0.5f;
		
		Body Shelf = world.createBody(shelfDef);
		Shelf.createFixture(fixDef);
		
		float timeStep = 1.0f / 60.0f;
		int velocityIterations = 6;
		int positionIterations = 2;
		
		for(int i = 0;true; i++){
			world.step(timeStep, velocityIterations, positionIterations);
			//System.out.print("\r Steps: " + i);
		}
	}
	
	private void CreateBall(int x, int y){
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyType.DYNAMIC;
		
		CircleShape cs = new CircleShape();
		cs.m_radius = 0.5f;
		
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 0.5f;
		fd.friction = 0.5f;
		fd.restitution = 0.5f;
		
		Body newBody = world.createBody(bd);
		newBody.createFixture(fd);
		
		bodyList.add(newBody);
	}
}

class MyPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Body> drawList = new ArrayList<Body>();
	
	public MyPanel(){
		setSize(500, 500);
		addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e) {
                //CreateBall(e.getX(),e.getY());
            }

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//if(Is) return;
		for(Body b : drawList){
			g.drawOval((int)b.getPosition().x, (int)b.getPosition().y, 20, 20);
		}
	}
}