
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

public class Lever  extends ActivableObjects {
	private Sprite lever ;
	public Lever(Area area, DiscreteCoordinates position, boolean active) {
		super(area,position,active);
		switchSprite();
	}
	@Override
	public boolean isOn() {
		if(active) {
			return true ;
		}
		return false;
	}
	
	//non traversable 
	@Override
	public boolean takeCellSpace() {
		return true;
	}
	//il accepte une interaction a distance 
	@Override
	public boolean isViewInteractable() {
		return true ;
	}
	@Override
	public boolean isCellInteractable() {
		return false;
	}
	
	@Override
	public void draw(Canvas canvas) {
	lever.draw(canvas);	
	}
	public void switchSprite() {
		if(isOn()) {
			lever = new Sprite("lever.big.left", 1, 1.f, this);
		}else {
			lever = new Sprite("lever.big.right", 1, 1.f, this);
		}

	}
	public void acceptInteraction(AreaInteractionVisitor v) { 
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	public void setActive(boolean active ) {
		this.active = active ;
	}


	
}
