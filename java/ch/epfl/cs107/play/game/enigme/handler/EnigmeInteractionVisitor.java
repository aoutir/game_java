
/*
 *	Author:      Hafsa Aoutir
 *	Date:        5 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Coin;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Gold;
import ch.epfl.cs107.play.game.enigme.actor.Helmet;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Monster;
import ch.epfl.cs107.play.game.enigme.actor.MovingRock1;
import ch.epfl.cs107.play.game.enigme.actor.OldMan;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Shield;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.Sword;
import ch.epfl.cs107.play.game.enigme.actor.Torche;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {

	default void interactWith(Apple a) {

	}

	default void interactWith(Door d) {

	}

	default void interactWith(EnigmeBehavior.EnigmeCell cell) {

	}

	default void interactWith(EnigmePlayer player) {

	}

	default void interactWith(Torche torche) {

	}

	default void interactWith(Lever levier) {

	}

	default void interactWith(PressureSwitch pressureSwitch) {

	}

	default void interactWith(Key key) {

	}

	default void interactWith(PressurePlate pressurePlate) {

	}

	default void interactWith(SignalDoor signalDoor) {

	}

	default void interactWith(Coin coin) {

	}

	default void interactWith(MovingRock1 movingRock) {

	}

	default void interactWith(Gold gold) {

	}

	default void interactWith(OldMan oldMan) {

	}

	default void interactWith(Monster monster) {

	}

	default void interactWith(Sword sword) {

	}

	default void interactWith(Helmet helmet) {

	}

	default void interactWith(Shield shield) {

	}

}
