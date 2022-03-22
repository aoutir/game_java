package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

	/// Indicate if the actor is currently moving
	private boolean isMoving ;
	/// Indicate how many frames the current move is supposed
	private int framesForCurrentMove;
	/// The target cell (i.e. where the mainCell will be after
	private DiscreteCoordinates targetMainCellCoordinates;

	/**
	 * Default MovableAreaEntity constructor
	 * 
	 * @param area
	 *            (Area): Owner area. Not null
	 * @param position
	 *            (Coordinate): Initial position of the entity. Not null
	 * @param orientation
	 *            (Orientation): Initial orientation of the entity. Not null
	 */
	public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		// TODO implements me #PROJECT #TUTO
		resetMotion();
	}

	/**
	 * Initialize or reset the current motion information
	 */
	protected void resetMotion() {
		// TODO implements me #PROJECT #TUTO
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();
	}

	/**
	 * 
	 * @param frameForMove
	 *            (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */

	protected boolean move(int framesForMove) {
		// TODO implements me #PROJECT #TUTO
		
		if (!isMoving || getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
			boolean enter = getOwnerArea().enterAreaCells(this,getEnteringCells()); 
			boolean leave = getOwnerArea().leaveAreaCells(this,getLeavingCells());
			
			if (enter && leave) {
				if (framesForMove < 1) {
					framesForMove = 1;
				}
				framesForCurrentMove = framesForMove;
				Vector orientation = getOrientation().toVector();
				targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
				isMoving = true ;
				return true;
			}
		}
		return false;
	}
	protected void setOrientation(Orientation orientation) {
		if(!isMoving) {
			super.setOrientation(orientation);
		}
	
	}
	protected Orientation getOrientation() {
		 return super.getOrientation();
	}
	/// MovableAreaEntity implements Actor

	@Override
	public void update(float deltaTime) {
		// TODO implements me #PROJECT #TUTO 
		if (isMoving && !getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
			Vector distance = getOrientation().toVector();
			distance = distance.mul(1.0f / framesForCurrentMove);
			setCurrentPosition(getPosition().add(distance));
			System.out.println(getPosition());
		} else {
			resetMotion();
		}

	}

	/// Implements Positionable

	@Override
	public Vector getVelocity() {
		// TODO implements me #PROJECT #TUTO
		// the velocity must be computed as the orientation vector
		// (getOrientation().toVector() mutiplied by
		// framesForCurrentMove  
		return new Vector(getOrientation().toVector().x*framesForCurrentMove , getOrientation().toVector().y*framesForCurrentMove );

	}

	public  List<DiscreteCoordinates> getCurrentCells() {
		List<DiscreteCoordinates> currentCells = new LinkedList<>();
		currentCells.add(getCurrentMainCellCoordinates());
		return currentCells;
	}

	protected final List<DiscreteCoordinates> getLeavingCells() {
		return getCurrentCells();
	}

	protected final List<DiscreteCoordinates> getEnteringCells() {
		List<DiscreteCoordinates> enteringCells = new LinkedList<>();
		
		for (DiscreteCoordinates coord : getCurrentCells()) {
			System.out.println("actooooooooooooooooooooooooooooooooooooooor"+coord);
			
			int x = coord.jump(getOrientation().toVector()).x;
			int y = coord.jump(getOrientation().toVector()).y;

			if (getOwnerArea().getAreaBehavior().isInGrid(x, y)) {
				enteringCells.add(coord.jump(getOrientation().toVector()));
			}
		}
		return enteringCells;
	}
	public boolean getIsMoving() {
		return isMoving ;
	}
	public DiscreteCoordinates getTarget() {
		return targetMainCellCoordinates;
	}

}
