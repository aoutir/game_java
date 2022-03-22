
/*
 *	Author:      Hafsa Aoutir
 *	Date:        4 déc. 2018
 */
package ch.epfl.cs107.play.game.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torche;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {
	private Torche torche;
	private Door door;
	private Lever levier1;
	private Lever levier2;
	private Lever levier3;
	private PressureSwitch bouton1;
	private PressureSwitch bouton2;
	private PressureSwitch bouton3;
	private PressureSwitch bouton4;
	private PressureSwitch bouton5;
	private PressureSwitch bouton6;
	private PressureSwitch bouton7;
	private Key key;
	private PressurePlate pressurePlate;
	private SignalDoor signalDoor;
	private SignalRock rock1;
	private SignalRock rock2;
	private SignalRock rock3;
	private Logic signalRock2;
	private Logic signalRock3;

	public Level3() {
		super("Level3");
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		door = new Door(this, "LevelSelector", new DiscreteCoordinates(3, 6), new DiscreteCoordinates(5, 0),
				new DiscreteCoordinates(5, 0));
		torche = new Torche(this, new DiscreteCoordinates(7, 5), false);
		levier1 = new Lever(this, new DiscreteCoordinates(10, 5), false);
		levier2 = new Lever(this, new DiscreteCoordinates(9, 5), false);
		levier3 = new Lever(this, new DiscreteCoordinates(8, 5), false);
		bouton1 = new PressureSwitch(this, new DiscreteCoordinates(4, 4), false);
		bouton2 = new PressureSwitch(this, new DiscreteCoordinates(5, 4), false);
		bouton3 = new PressureSwitch(this, new DiscreteCoordinates(6, 4), false);
		bouton4 = new PressureSwitch(this, new DiscreteCoordinates(5, 5), false);
		bouton5 = new PressureSwitch(this, new DiscreteCoordinates(4, 6), false);
		bouton6 = new PressureSwitch(this, new DiscreteCoordinates(5, 6), false);
		bouton7 = new PressureSwitch(this, new DiscreteCoordinates(6, 6), false);
		key = new Key(this, new DiscreteCoordinates(1, 3));
		pressurePlate = new PressurePlate(this, new DiscreteCoordinates(9, 8), false);
		signalDoor = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3, 6), new DiscreteCoordinates(5, 9),
				key, new DiscreteCoordinates(5, 9));
		rock1 = new SignalRock(this, new DiscreteCoordinates(6, 8), pressurePlate);
		signalRock2 = new MultipleAnd(bouton1, bouton2, bouton3, bouton4, bouton5, bouton6, bouton7);
		rock2 = new SignalRock(this, new DiscreteCoordinates(5, 8), signalRock2);
		signalRock3 = new Or(new LogicNumber(5, levier1, levier2, levier3), torche);
		rock3 = new SignalRock(this, new DiscreteCoordinates(4, 8), signalRock3);
		addActors();
		return true;
	}

	public String getTitle() {
		return "Level3";
	}

	@Override
	protected void addActors() {
		registerActor(new Background(this));
		registerActor(door);
		registerActor(torche);
		registerActor(levier1);
		registerActor(levier2);
		registerActor(levier3);
		registerActor(bouton1);
		registerActor(bouton2);
		registerActor(bouton3);
		registerActor(bouton4);
		registerActor(bouton5);
		registerActor(bouton6);
		registerActor(bouton7);
		registerActor(key);
		registerActor(pressurePlate);
		registerActor(signalDoor);
		registerActor(rock1);
		registerActor(rock2);
		registerActor(rock3);

	}

}
