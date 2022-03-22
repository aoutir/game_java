
/*
 *	Author:      Hafsa Aoutir
 *	Date:        4 déc. 2018
 */
package ch.epfl.cs107.play.game.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea {

	protected Door door2;
	protected Apple apple ;

	public Level2() {
		super("Level2");
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		System.out.println("lllllllllllllllllllkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		super.begin(window, fileSystem);
		door2 = new Door(this, "LevelSelector", new DiscreteCoordinates(2, 6),new DiscreteCoordinates(5, 0), new DiscreteCoordinates(5, 0));
		apple = new Apple (this, new DiscreteCoordinates(5, 6));
		addActors();
		return true;
	}

	public String getTitle() {
		return "Level2";
	}
	protected void addActors() {
		System.out.println("Sooooooooooooooooooooorrrrrrryyyyy");
		registerActor(new Background(this));
		registerActor(door2);
		registerActor(apple);
		//apple.enterArea(this, new DiscreteCoordinates(5, 6));
	}
	

}
