
/*
 *	Author:      Hafsa Aoutir
 *	Date:        4 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity  {

	private String arrivalArea;
	private DiscreteCoordinates arrivalCoordinates ;
	
	//on definit une orientation qlq 
	public Door(Area area , String arrivalArea , DiscreteCoordinates arrivalCoordinates,DiscreteCoordinates currentMainCellCoordinates , DiscreteCoordinates... discreteCoordinates ) {
		super(area,Orientation.UP,currentMainCellCoordinates);
		enterArea(area,currentMainCellCoordinates);
		this.arrivalArea = arrivalArea ;
		this.arrivalCoordinates = arrivalCoordinates;
	}
	public String getArrivalArea() {
		return arrivalArea;
	}
	public DiscreteCoordinates getArrivalCoordinates() {
		return  arrivalCoordinates ;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	//Il s’agit d’un acteur non traversable 
	@Override
	public boolean takeCellSpace() {
		return false;
	}
	//on sait pas 
	@Override
	public boolean isViewInteractable() {
		return false;
	}
	//accepte les interactions de contact
	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		
	}
	public void acceptInteraction(AreaInteractionVisitor v) { 
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}
	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		setCurrentPosition(position.toVector());
	}
	
}
