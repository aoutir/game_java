
/*
 *	Author:      Hafsa Aoutir
 *	Date:        12 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Coin extends Collectable implements Logic {

	private Sprite coin;

	public Coin(Area area, DiscreteCoordinates position) {
		super(area, position, false);
		coin = new Sprite("coin.1", 1, 1.f, this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return !collecte;
	}

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
		coin.draw(canvas);
	}

	@Override
	public boolean isOn() {
		if(isCollected()) {
			return true ;
		}
		return false;
	}
	@Override
	public void leaveArea() {
		super.leaveArea();

	}
}
