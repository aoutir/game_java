
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends Collectable implements Logic {

	private Sprite key1;

	// une cle est toujours non collecte au debut
	// Alors elle est nécessairement désactivée
	public Key(Area area, DiscreteCoordinates position) {
		super(area, position, false);
		key1 = new Sprite("key.1", 1, 1.f, this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	// acteur non traversable
	@Override
	public boolean takeCellSpace() {
		return true;
	}

	// accepte uniquement les interactions a distance
	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {

		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		key1.draw(canvas);
	}

	protected boolean isCollected() {
		return collecte;
	}

	public void leaveArea() {
		super.leaveArea();

	}
	@Override
	public boolean isOn() {
		if (isCollected()) {
			return true;
		} else {
			return false;
		}
	}

}
