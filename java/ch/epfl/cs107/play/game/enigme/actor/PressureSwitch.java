
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressureSwitch extends ActivableObjects {
	private Sprite pressureSwitch;

	public PressureSwitch(Area area, DiscreteCoordinates position, boolean active) {
		super(area, position, active);
		switchSprite();
	}

	@Override
	public boolean isOn() {
		if (active) {
			return true;
		}
		return false;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	// traversable
	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	// accepte uniquement les interactions de contact
	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		pressureSwitch.draw(canvas);
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void switchSprite() {
		if (isOn()) {
			pressureSwitch = new Sprite("GroundLightOn", 1, 1.f, this);
		} else {
			pressureSwitch = new Sprite("GroundLightOff", 1, 1.f, this);
		}
		
	}
}
