
/*
 *	Author:      Hafsa Aoutir
 *	Date:        5 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

abstract class Collectable extends AreaEntity {
	
protected boolean collecte ;
	//on donne une orientation qlq 
	public Collectable(Area area, DiscreteCoordinates position , boolean collecte ) {
		super(area,Orientation.DOWN,position);
		this.collecte = collecte ;
		
	}
	 public void leaveArea() {
			getOwnerArea().unregisterActor(this);
		}
	 
	 protected void setCollecte(boolean collecte) {
		 this.collecte = collecte ;
	 }
	 protected  boolean isCollected() {
		 return collecte ;
	 }
		
}
