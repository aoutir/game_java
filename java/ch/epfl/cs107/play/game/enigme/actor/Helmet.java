
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

public class Helmet extends Collectable implements Logic{
 private Sprite helmet;
	public Helmet(Area area, DiscreteCoordinates position, boolean collecte) {
		super(area, position, collecte);
		helmet = new Sprite("helmet.1",0.75f,0.75f,this);
		
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
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		helmet.draw(canvas);
	}

	@Override
	public boolean isOn() {
		
		return collecte;
	}
	@Override
	public void leaveArea() {
	        super.leaveArea();
	
	}
	@Override
	protected void setCollecte(boolean collecte) {
		super.setCollecte(collecte);
	}

}
