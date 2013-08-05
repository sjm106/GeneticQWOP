package jQWOP;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.geom.RectangularShape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

class DrawPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2616891227917053429L;
	
	public void doDrawing(Graphics g, float posx, float posy){
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.blue);
		
		g.drawOval((int)posx, (int)posy, 5, 5);
		
		paintComponents(g);
		
	}
	
	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		//System.out.println("I'M PAINTING!");
		super.paintComponents(g);
	}
	
}

public class DrawFrame extends JFrame {

	
	//static JFrame frame = new JFrame("Test Window");
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7102642450533178914L;

	public DrawFrame() {
        initUI();
    }

    public final void initUI() {

        DrawPanel dpnl = new DrawPanel();
        add(dpnl);

        setSize(500, 500);
        setTitle("draws");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		final DrawFrame ex = new DrawFrame();
        final DrawPanel dp = new DrawPanel();
        Vec2 g = new Vec2(0.0f, 0.02f);
        World world = new World(g);
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ex.add(dp);
                ex.setVisible(true);
            }
        });
		
		//boolean doSleep = true;
		
		
		BodyDef def = new BodyDef();
		def.position.set(0, 200);
		def.type = BodyType.STATIC;
		
		PolygonShape ps = new PolygonShape();
		Vec2[] vertices = new Vec2[4];
		vertices[0] = new Vec2(0, 0);
		vertices[1] = new Vec2(0, 20);
		vertices[2] = new Vec2(200, 0);
		vertices[3] = new Vec2(200, 20);
		ps.set(vertices, vertices.length);
		
		FixtureDef fixD = new FixtureDef();
		fixD.shape = ps;
		fixD.density = 1.0f;
		fixD.friction = 0.5f;
		fixD.restitution = 0.5f;
		
		//**********************************
		//This creates the ball
		BodyDef bd = new BodyDef();
		bd.position.set(50, 50);
		bd.type = BodyType.DYNAMIC;
		
		CircleShape cs = new CircleShape();
		cs.m_radius = 0.5f;
		
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 0.5f;
		fd.friction = 0.5f;
		fd.restitution = 0.5f;
		//*********************************
		
		Body body = world.createBody(bd);
		body.createFixture(fd);
		Body shelf = world.createBody(def);
		shelf.createFixture(fixD);
		
		float timeStep = 1.0f / 60.0f;
		int velocityIterations = 6;
		int positionIterations = 2;
		
		//Game Loop.
		for(@SuppressWarnings("unused")
		int i = 0;true; i++){
			world.step(timeStep, velocityIterations, positionIterations);
			Vec2 position = body.getPosition();
			float angle = body.getAngle();
			dp.paintComponents(dp.getGraphics());
			dp.doDrawing(dp.getGraphics(), position.x, position.y);
			System.out.printf("%4.2f %4.2f %4.2f\n", position.x, position.y, angle);
		}
		
	}
	

}
