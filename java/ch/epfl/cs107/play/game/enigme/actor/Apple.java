
/*
 *	Author:      Hafsa Aoutir
 *	Date:        4 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;
//ajouter leaveArea enterArea
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;


public class Apple extends Collectable  {
	
	private Sprite apple ;
	
	//une pomme est toujours non collectee a la creation 
	public Apple(Area area, DiscreteCoordinates position ) {
		super(area,position,false);
		apple = new Sprite("apple.1", 1, 1.f, this);
	}

	@Override

	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}
	
	//apple est non traversable 
	@Override
	public boolean takeCellSpace() {
		return !collecte ;
	}
	//apple accepte les interactions a distance alors la methode retourne true 
	@Override
	public boolean isViewInteractable() {
		return true ;
	}
	//apple n'accepte pas les intetractions a distance alors la methode retourne false
	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		apple.draw(canvas);
	}
	
	
	@Override
	public void leaveArea() {
	        super.leaveArea();
	
	}
	public void acceptInteraction(AreaInteractionVisitor v) { 
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
	protected void setCollecte(boolean collecte) {
		super.setCollecte(collecte);
	}

	
}
