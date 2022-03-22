package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects asking for interaction (i.e. can interact with some
 * Interactable)
 * 
 * @see Interactable This interface makes sense only in the "Area Context" with
 *      Actor contained into Area Cell
 */
public interface Interactor extends Actor {

	// TODO implements me #PROJECT #TUTO

	// methode retournant la liste de coordonnées des cellules occupees
	List<DiscreteCoordinates> getCurrentCells();

	// methode retournant retournant les coordonnées des cellules de son champs de vision
	List<DiscreteCoordinates> getFieldOfViewCells();

	// demande d'une interaction par contact
	boolean wantsCellInteraction();

	// demande d'une interction a distance
	boolean wantsViewInteraction();
	
	void interactWith(Interactable other);
}
