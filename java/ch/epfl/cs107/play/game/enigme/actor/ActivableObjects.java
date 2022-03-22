
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
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
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.signal.Signal;

abstract class ActivableObjects extends AreaEntity implements Logic  {
		
		protected boolean active ;
		
		public ActivableObjects(Area area, DiscreteCoordinates position, boolean active) {
			super(area, Orientation.DOWN, position);
			this.active = active ;
		}
		public boolean getActive() {
			return active;
		}
		public void setActive(boolean active ) {
			this.active = active ;
		}
		
		public void leaveArea() {
			getOwnerArea().unregisterActor(this);
		}
		
		public List<DiscreteCoordinates> getCurrentCells() {
			return Collections.singletonList(getCurrentMainCellCoordinates());
		}
		//tout les objets de cette classe switch de sprite
		public abstract void switchSprite();
		
		public void acceptInteraction(AreaInteractionVisitor v) { 
			((EnigmeInteractionVisitor)v).interactWith(this);
		}
		

}	