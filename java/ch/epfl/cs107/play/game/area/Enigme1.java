
/*
 *	Author:      Hafsa Aoutir
 *	Date:        12 déc. 2018
 */
package ch.epfl.cs107.play.game.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Coin;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Gold;
import ch.epfl.cs107.play.game.enigme.actor.Helmet;
import ch.epfl.cs107.play.game.enigme.actor.Monster;
import ch.epfl.cs107.play.game.enigme.actor.MovingRock1;
import ch.epfl.cs107.play.game.enigme.actor.OldMan;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Shield;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Sword;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea {
 private OldMan oldMan;
 private Monster monster1;
 private Monster monster2;
 private Helmet helmet;
 private Shield shield;
 private Sword sword;
 
 private Coin coin1;
 private Coin coin2;
 private Coin coin3;
 
 private Gold gold1;
 private Gold gold2;
 private Gold gold3;
 
 private MovingRock1 movingRock1;
 private SignalRock signalRock1;
 private SignalRock signalRock2 ;
 
 private PressureSwitch bouton1 ;
 private PressureSwitch bouton2 ;
 private PressureSwitch bouton3 ;
private PressureSwitch bouton4 ;
private PressureSwitch bouton5 ;
private PressureSwitch bouton6 ;
private PressureSwitch bouton7 ;
private PressureSwitch bouton8;
private PressureSwitch bouton9;

private SignalDoor signalDoor1;
private Door door1;
private Door door2;
 

 
	public Enigme1() {
		super("Enigme1");
		
	}

	@Override
	public String getTitle() {
		
		return "Enigme1";
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		oldMan = new OldMan(this,Orientation.DOWN,new DiscreteCoordinates(12,3));
		gold1 = new Gold(this, new DiscreteCoordinates(12,7),false);
		gold2 = new Gold(this, new DiscreteCoordinates(22,2),false);
		gold3 = 	new Gold(this, new DiscreteCoordinates(16,8),false);
		coin1 = new Coin(this, new DiscreteCoordinates(22,8));
		coin2 = new Coin(this, new DiscreteCoordinates(14,33));
		coin3 = new Coin(this, new DiscreteCoordinates(15,16));
		shield = new Shield(this,new DiscreteCoordinates(17,22),false);
		sword = new Sword(this,new DiscreteCoordinates(1,6),false);
		helmet = new Helmet(this,new DiscreteCoordinates(15,20),false);
		movingRock1 = new MovingRock1(this,Orientation.DOWN,new DiscreteCoordinates(21,6));
		signalRock1 = new SignalRock(this,new DiscreteCoordinates(19,25),Logic.FALSE);
		signalRock2= new SignalRock(this,new DiscreteCoordinates(16,32),Logic.FALSE);
		bouton1 = new PressureSwitch(this, new DiscreteCoordinates(17, 16), false);
		bouton2 = new PressureSwitch(this, new DiscreteCoordinates(18, 16), false);
		bouton3 = new PressureSwitch(this, new DiscreteCoordinates(19, 16), false);
		bouton4 = new PressureSwitch(this, new DiscreteCoordinates(17, 18), false);
		bouton5 = new PressureSwitch(this, new DiscreteCoordinates(19, 18), false);
		bouton6 = new PressureSwitch(this, new DiscreteCoordinates(19, 17), false);
		bouton7 = new PressureSwitch(this, new DiscreteCoordinates(21, 18), false);
		bouton8 = new PressureSwitch(this, new DiscreteCoordinates(20, 18), false);
		bouton9 = new PressureSwitch(this, new DiscreteCoordinates(18, 18), false);
		signalDoor1 = new SignalDoor(this, "Enigme1",new DiscreteCoordinates(12,14), new DiscreteCoordinates(17,25), new MultipleAnd(gold1,gold2,gold3,coin1,coin2,coin3),new DiscreteCoordinates(17,25));
		door2 = new Door(this, "LevelSelector", new DiscreteCoordinates(5,5), new DiscreteCoordinates(6,21),new DiscreteCoordinates(6,21));
		monster1 = new Monster(this, new DiscreteCoordinates(7,8),false, new MultipleAnd(sword,helmet,shield));
		monster2 = new Monster(this, new DiscreteCoordinates(6,8),false, new MultipleAnd(sword,helmet,shield));
		addActors();
		return true;
	}
	@Override
	protected void addActors() {
		registerActor(new Background(this));
		registerActor(oldMan);
		registerActor(monster1);
		registerActor(monster2);
		registerActor(gold1);
		registerActor(gold2);
		registerActor(gold3);
		registerActor(coin1);
		registerActor(coin2);
		registerActor(coin3);
		registerActor(shield);
		registerActor(sword);
		registerActor(helmet);
		registerActor(movingRock1);
		registerActor(signalRock1);
		registerActor(signalRock2);
		registerActor(bouton1);
		registerActor(bouton2);
		registerActor(bouton3);
		registerActor(bouton4);
		registerActor(bouton5);
		registerActor(bouton6);
		registerActor(bouton7);
		registerActor(bouton8);
		registerActor(bouton9);
		registerActor(signalDoor1);
		registerActor(door2);
		
	}
	

}
