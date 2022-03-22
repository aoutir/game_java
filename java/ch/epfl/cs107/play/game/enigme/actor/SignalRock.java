
/*
 *	Author:      Hafsa Aoutir
 *	Date:        10 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.window.Canvas;

public  class SignalRock extends AreaEntity {
	
private Logic signal ;
private Sprite rock ;
	
	public SignalRock (Area area,DiscreteCoordinates position ,Logic signal) {
		super(area,Orientation.DOWN, position);
		rock = new Sprite("rock.3",1,1.f,this);
		this.signal = signal ;
	}
	
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	 public void leaveArea() {
			getOwnerArea().unregisterActor(this);
	}
	@Override
	public boolean isCellInteractable() {
		if(signal.isOn()) {
			return true ;
		}
		return false ;
	}
	public boolean takeCellSpace() {
		if (signal.isOn()) {
			getOwnerArea().unregisterActor(this);
			return false;
		}
		return true;
	}
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);	
	}
	@Override
	public boolean isViewInteractable() {
		return false;
	}
	@Override
	public void draw(Canvas canvas ) {
		if(!signal.isOn()) {
			rock.draw(canvas);
		}
	}
	
	
	
	
	
}
