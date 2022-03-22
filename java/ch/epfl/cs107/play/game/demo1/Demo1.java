
package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;


import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;


public class Demo1 implements Game {
	private Actor actor1 ;
	private Actor actor2 ;
	private Window window ;
	private FileSystem fileSystem ;
	float radius = 0.2f;
	
	public String getTitle() {
		return "Demo1";
	}
	
	public int getFrameRate() {
		return 24 ;
	}
	public boolean begin(Window window, FileSystem fileSystem) {
	   this.window = window ;
	   this.fileSystem = fileSystem ;
	   //Transform viewTransform = Transform.I.scaled(10).translated(Vector.ZERO);
	   //window.setRelativeTransform(viewTransform);
	   actor1 = new GraphicsEntity(Vector.ZERO,new ShapeGraphics(new Circle(radius), null,Color.RED, 0.005f));
	   actor2 = new MovingRock(new Vector(0.2f,0.2f),"Hello, I'am a moving rock !");
		return true;
	}
	public void end () {
		System.out.println(" The end ");
	}
	
	public void update(float deltaTime) {
		// ici donner un peu de vie au premier acteur si nécessaire
		actor1.draw(window);
		actor2.draw(window);
		
		Keyboard keyboard = window.getKeyboard(); 
		Button downArrow = keyboard.get(Keyboard.DOWN);
		float x1 = actor1.getPosition().x ;
		float y1 = actor1.getPosition().y ;
		float x2 = actor2.getPosition().x ;
		float y2 = actor2.getPosition().y ;
		
		if(downArrow.isDown()) {
			actor2.update(deltaTime);
			actor2.draw(window);
			}
			TextGraphics boum = new TextGraphics(" BOUM !!!",0.05f,Color.RED);
			float dsquare = (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
			if(dsquare <= radius*radius) {
				boum.setParent(actor2);
				boum.draw(window);
			
			}
	}
	
}
