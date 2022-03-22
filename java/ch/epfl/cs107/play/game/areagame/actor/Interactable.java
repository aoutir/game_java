package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with
 * them)
 * 
 * @see Interactor This interface makes sense only in the "AreaGame" context
 *      with Actor contained into Area Cell
 */
public interface Interactable {
	// TODO implements me #PROJECT #TUTO
	List<DiscreteCoordinates> getCurrentCells();
	//indique si un interactable est traversable ou pas // true sera dit «non traversable »
	boolean takeCellSpace();
	//retourne un boolean indiquant s'il accepte les interactions a distance 
	boolean isViewInteractable();
	//retourne un boolean indiquant s'il accepte les interactions de contact
	boolean isCellInteractable();
	
	void acceptInteraction(AreaInteractionVisitor v );

}
