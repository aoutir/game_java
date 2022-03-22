
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

public class Torche extends ActivableObjects {
	
	private Sprite torch1;

	public Torche(Area area, DiscreteCoordinates position, boolean active) {
		super(area, position, active);
		switchSprite();
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

	// accepte uniquement les interactions à distance
	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}
	
	@Override
	public void draw(Canvas canvas) {
		torch1.draw(canvas);
	}

	@Override
	public boolean isOn() {
		if(getActive()) {
			return true ;
		}
		return false;
	}
	//activer et desactiver la torche 
	public void setActive(boolean active ) {
		this.active = active ;
	}
	//on change de sprite 
	public void switchSprite() {
		if(isOn()) {
			torch1 = new Sprite("torch.ground.on.1", 1, 1.f, this);
		}else {
			torch1 = new Sprite("torch.ground.off", 1, 1.f, this);
		}
	}
	public void acceptInteraction(AreaInteractionVisitor v) { 
		((EnigmeInteractionVisitor)v).interactWith(this);
	}

}
