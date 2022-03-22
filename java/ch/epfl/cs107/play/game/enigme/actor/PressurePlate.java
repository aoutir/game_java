
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressurePlate extends ActivableObjects {
	private Sprite pressurePlate;
	private final float tempsActivation = 0.3f;
	private float tempsEcoule;

	public PressurePlate(Area area, DiscreteCoordinates position, boolean active) {
		super(area, position, active);
		switchSprite();
	}

	@Override
	public boolean isOn() {
		if (getActive()) {
			return true;
		}
		return false;
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

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		pressurePlate.draw(canvas);
	}

	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void switchSprite() {
		if (isOn()) {
			pressurePlate = new Sprite("GroundLightOn", 1, 1.f, this);
		} else {
			pressurePlate = new Sprite("GroundPlateOff", 1, 1.f, this);
		}
	}

	public void update(float deltaTime) {
		if (getActive()) {
			System.out.println(deltaTime);
			tempsEcoule += deltaTime;
			System.out.println(tempsEcoule);

			if (tempsEcoule > tempsActivation) {
				setActive(false);
				switchSprite();
				tempsEcoule = 0;
			}
		}
	}
}
