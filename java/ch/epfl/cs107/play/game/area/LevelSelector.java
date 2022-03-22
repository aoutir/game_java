
/*
 *	Author:      Hafsa Aoutir
 *	Date:        4 déc. 2018
 */
package ch.epfl.cs107.play.game.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea {
	protected SignalDoor door1, door2, door3, door4, door5, door6, door7, door8 ,door9;
	

	public LevelSelector() {
		super("LevelSelector");
	}

	public boolean begin(Window window, FileSystem fileSystem) {
				super.begin(window, fileSystem);
				door1 = new SignalDoor(this, "Level1", new DiscreteCoordinates(5, 1),new DiscreteCoordinates(1, 7), Logic.TRUE,new DiscreteCoordinates(1, 7));
				door2 = new SignalDoor(this, "Level2", new DiscreteCoordinates(5, 1),new DiscreteCoordinates(2, 7),Logic.TRUE, new DiscreteCoordinates(2, 7));
				door3 = new SignalDoor(this, "Level3", new DiscreteCoordinates(5, 1), new DiscreteCoordinates(3, 7),Logic.TRUE,new DiscreteCoordinates(3, 7));
				door4 = new SignalDoor(this, "Enigme2", new DiscreteCoordinates(7, 2), new DiscreteCoordinates(4, 7),Logic.TRUE,new DiscreteCoordinates(4, 7));
				door5 = new SignalDoor(this, "", new DiscreteCoordinates(5, 5), new DiscreteCoordinates(5, 7),Logic.FALSE,new DiscreteCoordinates(5, 7));
				door6 = new SignalDoor(this, "", new DiscreteCoordinates(5, 5), new DiscreteCoordinates(6, 7),Logic.FALSE,new DiscreteCoordinates(6, 7));
				door7 = new SignalDoor(this, "", new DiscreteCoordinates(5, 5), new DiscreteCoordinates(7, 7),Logic.FALSE,new DiscreteCoordinates(7, 7));
				door8 = new SignalDoor(this, "", new DiscreteCoordinates(5, 5), new DiscreteCoordinates(8, 7),Logic.FALSE,new DiscreteCoordinates(8, 7));
				//door9 = new SignalDoor(this,"level2",new DiscreteCoordinates(5,1),new DiscreteCoordinates(1,3),Logic.TRUE,new DiscreteCoordinates(1,3));
				addActors();
				return true ;
	}

	public String getTitle() {
		return "LevelSelector";
	}
	@Override
	protected void addActors() {
		registerActor(new Background(this));
		registerActor(door1);
		registerActor(door2);
		registerActor(door3);
		registerActor(door4);
		registerActor(door5);
		registerActor(door6);
		registerActor(door7);
		registerActor(door8);
	
	}
}
