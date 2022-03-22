
/*
 *	Author:      Hafsa Aoutir
 *	Date:        11 déc. 2018
 */
package ch.epfl.cs107.play.game.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Coin;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Gold;
import ch.epfl.cs107.play.game.enigme.actor.MovingRock1;
import ch.epfl.cs107.play.game.enigme.actor.OldMan;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea {
	protected Door door3;
	protected MovingRock1 movingRock ;
	protected PressurePlate pressurePlate ;
	protected SignalDoor signalDoor1;
	
	public Enigme0() {
		super("Enigme2");
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		//door3 = new Door(this, "Enigme1", new DiscreteCoordinates(10, 6),new DiscreteCoordinates(7, 0), new DiscreteCoordinates(7, 0));
		
		movingRock = new MovingRock1(this,Orientation.DOWN, new DiscreteCoordinates(4,5));
		pressurePlate = new PressurePlate(this,new DiscreteCoordinates(4,8),false);
		signalDoor1 = new SignalDoor(this, "Enigme1", new DiscreteCoordinates(5,5), new DiscreteCoordinates(7,11), pressurePlate, new DiscreteCoordinates(7,11));
		addActors();
		return true;
	}

	public String getTitle() {
		return "Enigme2";
	}
	protected void addActors() {
		
		registerActor(new Background(this));
		registerActor(movingRock);
		registerActor(pressurePlate);
		registerActor(signalDoor1);
	
	}
	public void update(float deltaTime) {
		super.update(deltaTime);
		if(movingRock.getCurrentCells().equals(pressurePlate.getCurrentCells())) {
			pressurePlate.setActive(true);
		}
		
		
	}
	

}
