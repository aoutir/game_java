package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior {

	/// The behavior is an Image of size height x width
	private final Image behaviorMap;
	private final int width, height;
	protected final Cell[][] cells;

	// TODO implements me #PROJECT #TUTO
	/**
	 * Default AreaBehavior Constructor
	 * 
	 * @param window
	 *            (Window): graphic context, not null
	 * @param fileName
	 *            (String): name of the file containing the behavior image, not null
	 */
	public AreaBehavior(Window window, String fileName) {
		// TODO implements me #PROJECT #TUTO
		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();
		cells = new Cell[width][height];
	}

	// retourne la largeur
	protected int getWidth() {
		return behaviorMap.getWidth();
	}

	// retourne la hauteur
	protected int getHeight() {
		return behaviorMap.getHeight();
	}

	// retourne l'image associee a behaviorMap
	protected Image getBehaviorMap() {
		return behaviorMap;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public boolean isInGrid(int x, int y) {
		if (x < width && y < height) {
			return true;
		}
		return false;
	}

	// parcourir les cellules de l'ensemble coordinates
	// return true si entity peut quitter toutes les cellules de l'ensemble
	// coordinates
	// on applique la methode canLeave a chaque cellule
	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates coord : coordinates) {
			if (!cells[coord.x][coord.y].canLeave(entity)) {
				return false;
			}
		}
		return true;
	}

	// canEnter = true si entity peut entrer dans les cellules de l’ensemble
	// coordinates
	// on applique la methode canEnter a chaque cellule de l'ensemble coordinates
	// limitGrid = true si chacune des coordonnées de coordinates ne depasse les
	// limites de la grille.
	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		boolean canEnter = true;
		boolean limitGrid = true;
		
		for (DiscreteCoordinates coord : coordinates) {
			if (!(coord.x < width) || !(coord.y < height) || (coord.x < 0) || (coord.y < 0)) {
				return false ;
			}
		}
		for (DiscreteCoordinates coord : coordinates) {
			if (!cells[coord.x][coord.y].canEnter(entity)) {
				canEnter = false;
			}
		}
		System.out.println(canEnter + "       "+ limitGrid);
		return canEnter & limitGrid;

	}

	// on applique la methode leave a chaque cellule de l'ensemble coordinates
	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates coord : coordinates) {
			cells[coord.x][coord.y].leave(entity);
		}
	}

	// on applique la methode enter a chaque cellule de l'ensemble coordinates
	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (DiscreteCoordinates coord : coordinates) {
			cells[coord.x][coord.y].enter(entity);
		}
	}

	// gere les interactions de contact entre un interactor et les Interactables de
	// aux mêmes positions que celles qu’il occupe
	// parcourir toutes les cellules et dont les coordonees sont
	// interactor.getCurrentCells()
	// et on applique la methode cellInteractionOf a cette cellule
	public void cellInteractionOf(Interactor interactor) {
		for (DiscreteCoordinates coord : interactor.getCurrentCells()) {
			cells[coord.x][coord.y].cellInteractionOf(interactor);
		}
	}

	// gere les interactions a distance entre un interactor et les Interactables de
	// son champs de vision
	// parcourir toutes les cellules dont les coordonees sont
	// interactor.getFieldOfViewCells()
	// et on applique la methode viewInteractionOf a cette cellule
	public void viewInteractionOf(Interactor interactor) {
	
		for (DiscreteCoordinates coord : interactor.getFieldOfViewCells()) {
			cells[coord.x][coord.y].viewInteractionOf(interactor);
		}
	}

	// retourne la cellule dont ses cordonnnees discretes sont egales a dc
	// (l'argument de la methode )
	public Cell getCellule(DiscreteCoordinates dc) {
		return cells[dc.x][dc.y];
	}

	// TODO implements me #PROJECT #TUTO
	public abstract class Cell implements Interactable {

		protected Set<Interactable> entities = new HashSet<>();
		private DiscreteCoordinates coordinates;

		public Cell(int x, int y) {
			coordinates = new DiscreteCoordinates(x, y);
		}

		public List<DiscreteCoordinates> getCurrentCells() {
			List<DiscreteCoordinates> currentCells = new LinkedList<>();
			currentCells.add(coordinates);
			return currentCells;
		}

		// permettant l’ajout d’un Interactable donné à cet ensemble
		private void enter(Interactable entity) {
			entities.add(entity);
		}

		// permettant le retrait d’un Interactable de cet ensemble
		private void leave(Interactable entity) {
			entities.remove(entity);
		}

		// methodes abstraites redefinines dans les classes Demo2Behavior et
		// EnigmeBehavior
		protected abstract boolean canEnter(Interactable entity);

		protected abstract boolean canLeave(Interactable entity);

		// verifie si chaque inetractable accepte les interactions de contact si c'est
		// le cas on appelle la methode interactWith
		private void cellInteractionOf(Interactor interactor) {
			for (Interactable interactable : entities) {
				if (interactable.isCellInteractable()) {
					(interactor).interactWith(interactable);
				}
			}
		}

		// verifie si chaque inetractable accepte les interactions a distance si c'est
		// le cas on appelle la methode interactWith
		private void viewInteractionOf(Interactor interactor) {
			for (Interactable interactable : entities) {
				if (interactable.isViewInteractable()) {
					interactor.interactWith(interactable);
				}
			}
		}

		public void setCoordinates(DiscreteCoordinates dc) {
			coordinates = dc;
		}
	}
}
