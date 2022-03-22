
/*
 *	Author:      Hafsa Aoutir
 *	Date:        4 déc. 2018
 */
package ch.epfl.cs107.play.game.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level1 extends EnigmeArea {
	protected Door door1 ;
	
	public Level1() {
		super("Level1");
	}
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		door1 = new Door(this, "LevelSelector", new DiscreteCoordinates(1, 6),new DiscreteCoordinates(5, 0), new DiscreteCoordinates(5, 0));
		addActors();
		return true ;
	}
	public String getTitle() {
		return "Level1";
	}
	protected void addActors( ) {
		registerActor(new Background(this));
		registerActor(door1);
	}

}
