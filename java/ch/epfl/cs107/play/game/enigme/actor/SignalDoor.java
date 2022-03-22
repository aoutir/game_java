
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door {
	private Logic signal;
	private Sprite door;

	public SignalDoor(Area area, String arrivalArea, DiscreteCoordinates arrivalCoordinates,
			DiscreteCoordinates currentMainCellCoordinates, Logic signal, DiscreteCoordinates... setOfCoordinates) {
		super(area, arrivalArea, arrivalCoordinates, currentMainCellCoordinates, setOfCoordinates);
		this.signal = signal;
		switchSprite();
	}

	@Override
	public boolean isCellInteractable() {
		if (signal.isOn()) {
			return true;
		}
		return false;
	}

	public boolean takeCellSpace() {
		if (signal.isOn()) {
			return false;
		}
		return true;
	}

	public void draw(Canvas canvas) {

		if (signal.isOn()) {
			switchSprite();
		}
		door.draw(canvas);
	}

	public void switchSprite() {
		if (signal.isOn()) {
			door = new Sprite("door.open.1", 1, 1.f, this);
		} else {
			door = new Sprite("door.close.1", 1, 1.f, this);
		}

	}

}