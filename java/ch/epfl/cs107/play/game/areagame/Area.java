package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and
 * a List of Actors
 */
public abstract class Area implements Playable {

	private Window window;
	private FileSystem fileSystem;
	private List<Actor> actors; // liste d'acteurs
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;
	private Actor viewCandidate;
	private Vector viewCenter;
	private AreaBehavior areaBehavior;
	private boolean abordee = false;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave;
	private List<Interactor> interactors; // liste d'interactors
	private boolean pause = false;

	// Context objects
	// TODO implements me #PROJECT #TUTO
	/**
	 * @return (float): camera scale factor, assume it is the same in x and y
	 *         direction
	 */
	public abstract float getCameraScaleFactor();

	/**
	 * Add an actor to the actors list
	 * 
	 * @param a
	 *            (Actor): the actor to add, not null
	 * @param forced
	 *            (Boolean): if true, the method ends
	 */
	private void addActor(Actor a, boolean forced) {
		// TODO implements me #PROJECT #TUTO

		boolean errorOccured = !actors.add(a);
		if (a instanceof Interactable) {
			errorOccured = errorOccured || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
		}
		if (a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.add((Interactor) a);
		}
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added so remove it from where it was");
			removeActor(a, true);
		}
	}

	/**
	 * Remove an actor form the actor list
	 * 
	 * @param a
	 *            (Actor): the actor to remove, not null
	 * @param forced
	 *            (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced) {
		// TODO implements me #PROJECT #TUTO
		boolean errorOccured = !actors.remove(a);
		if (a instanceof Interactable) {
			errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
		}
		if (a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.remove((Interactor) a);
		}
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely removed so add it ");
			addActor(a, true);
		}
	}

	/**
	 * Register an actor : will be added at next update
	 * 
	 * @param a
	 *            (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a) {
		// TODO implements me #PROJECT #TUTO
		if (a != null) {
			System.out.println(a);
			registeredActors.add(a);
			return true;
		} else
			return false;

	}

	/**
	 * Unregister an actor : will be removed at next update
	 * 
	 * @param a
	 *            (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a) {
		// TODO implements me #PROJECT #TUTO
		if (a != null) {
			unregisteredActors.add(a);
			return true;
		} else
			return false;

	}

	/**
	 * Getter for the area width
	 * 
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth() {
		// TODO implements me #PROJECT #TUTO
		return areaBehavior.getWidth();
	}

	/**
	 * Getter for the area height
	 * 
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight() {
		// TODO implements me #PROJECT #TUTO
		return areaBehavior.getHeight();
	}

	/** @return the Window Keyboard for inputs */
	public final Keyboard getKeyboard() {
		// TODO implements me #PROJECT #TUTO
		return window.getKeyboard();
	}

	/// Area implements Playable

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements me #PROJECT #TUTO
		this.window = window;
		this.fileSystem = fileSystem;
		actors = new LinkedList<>();
		registeredActors = new LinkedList<>();
		unregisteredActors = new LinkedList<>();
		interactablesToEnter = new HashMap<>();
		interactablesToLeave = new HashMap<>();
		interactors = new LinkedList<>();
		viewCenter = Vector.ZERO;
		viewCandidate = null;
		abordee = true;
		return true;
	}

	/**
	 * Resume method: Can be overridden
	 * 
	 * @param window
	 *            (Window): display context, not null
	 * @param fileSystem
	 *            (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem) {
		if (window != null && fileSystem != null) {
			return true;
		} else
			return false;
	}

	// responsable de lancer les interactions
	@Override
	public void update(float deltaTime) {
		// TODO implements me #PROJECT #TUTO
		
		 Keyboard keyboard = window.getKeyboard();
		 Button space = keyboard.get(Keyboard.SPACE);
		 if (space.isPressed() && pause == false) {
		 pause = true; }
		 else if(space.isPressed() && pause == true) { 
			 pause = false ;
		 }
		 
		purgeRegistration();
		updateCamera();
	
		if(!pause) { 
			for (Actor actor : actors) {
				actor.update(deltaTime);
			}
		}
		
		for (Actor actor : actors) {
			actor.draw(window);
		}

		for (Interactor interactor : interactors) {
			if (interactor.wantsCellInteraction()) {
				areaBehavior.cellInteractionOf(interactor);
				// demander à la grille de mettre en place les interactions de contact
			}
			if (interactor.wantsViewInteraction()) {
				areaBehavior.viewInteractionOf(interactor);
				// demander à la grille de mettre en place les interaction distantes
			}
		}
	}

	private void updateCamera() {
		// TODO implements me #PROJECT #TUTO
		if (viewCandidate != null) {
			viewCenter = viewCandidate.getPosition();
		}
		float scaleFactor = getCameraScaleFactor();
		Transform viewTransform = Transform.I.scaled(scaleFactor).translated(viewCenter);
		window.setRelativeTransform(viewTransform);

	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public void suspend() {
		// Do nothing by default
		purgeRegistration();
	}

	@Override
	public void end() {
		// TODO save the AreaState somewhere
		System.out.println("the End");
	}

	private final void purgeRegistration() {

		for (Actor a : registeredActors) {
			addActor(a, false);
		}
		for (Actor b : unregisteredActors) {
			removeActor(b, false);
		}
		registeredActors.clear();
		unregisteredActors.clear();
		for (Entry<Interactable, List<DiscreteCoordinates>> pair : interactablesToEnter.entrySet()) {
			areaBehavior.enter(pair.getKey(), pair.getValue());
		}
		// pour toute entrée entry de interactablesToEnter, faire en sorte que
		// l’interactable lié à la clé de entry, entre dans les cellules données par
		// la valeur de entry (voir l’annexe 9 sur les Map), puis vider
		// interactablesToEnter ;

		interactablesToEnter.clear();
		for (Entry<Interactable, List<DiscreteCoordinates>> pair : interactablesToLeave.entrySet()) {
			areaBehavior.leave(pair.getKey(), pair.getValue());
		}

		interactablesToLeave.clear();
	}

	public final void setViewCandidate(Actor a) {
		this.viewCandidate = a;
	}

	protected final void setBehavior(AreaBehavior ab) {
		areaBehavior = ab;
	}

	public boolean getAbordee() {
		return abordee;
	}

	public AreaBehavior getAreaBehavior() {
		return areaBehavior;
	}

	public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canLeave(entity, coordinates)) {
			interactablesToLeave.put(entity, coordinates);
			return true;
		}
		return false;
	}

	public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {

		if (areaBehavior.canEnter(entity, coordinates)) {
			interactablesToEnter.put(entity, coordinates);
			return true;
		}
		return false;
	}

	public Window getWindow() {
		return window;
	}

	public FileSystem getfile() {
		return fileSystem;
	}

}
