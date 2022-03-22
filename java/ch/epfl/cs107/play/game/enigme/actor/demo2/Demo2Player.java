
/*
 *	Author:      Hafsa Aoutir
 *	Date:        3 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Demo2Player extends MovableAreaEntity {

	private boolean isPassing = false;
	private Sprite ghost;
	private final static int ANIMATION_DURATION = 8;
	private Keyboard keybord;

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		ghost = new Sprite("ghost.1", 1, 1.f, this);

	}

	public Demo2Player(Area area, DiscreteCoordinates coordinates) {
		this(area, Orientation.DOWN, coordinates);
	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		this.setOwnerArea(area);
		area.registerActor(this);
		setCurrentPosition(position.toVector());
		resetMotion();
	}

	public void leaveArea() {
		getOwnerArea().unregisterActor(this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	public void update(float deltaTime) {

		Keyboard keyboard = getOwnerArea().getKeyboard();
		Button LeftArrow = keyboard.get(Keyboard.LEFT);
		if (LeftArrow.isDown() && !getIsMoving()) {
			if (super.getOrientation().equals(Orientation.LEFT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.LEFT);

			}
		}
		Button RightArrow = keyboard.get(Keyboard.RIGHT);
		if (RightArrow.isDown() && !getIsMoving()) {
			if (super.getOrientation().equals(Orientation.RIGHT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.RIGHT);

			}
		}
		Button UpArrow = keyboard.get(Keyboard.UP);
		if (UpArrow.isDown() && !getIsMoving()) {
			if (super.getOrientation().equals(Orientation.UP)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.UP);

			}
		}
		Button DownArrow = keyboard.get(Keyboard.DOWN);
		if (DownArrow.isDown() && !getIsMoving()) {
			if (super.getOrientation().equals(Orientation.DOWN)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.DOWN);

			}
		}
		super.update(deltaTime);
		
	}

	public final boolean getIsPassing() {
		return isPassing;
	}

	public final void setIsPassing(boolean p) {
		isPassing = p;
	}
	//verifier si la cellule cible est une porte si cc'est le cas isPassing = true sinon isPassing false 
	protected boolean move(int framesForMove) {

		super.move(framesForMove);
		if (((Demo2Behavior) (getOwnerArea().getAreaBehavior())).isDoor(getTarget())) {
			isPassing = true;
		} else {
			isPassing = false;
		}
		return isPassing;
	}

	// il est non traversable
	@Override
	public boolean takeCellSpace() {
		// TODO Auto-generated method stub
		return true;
	}

	// Cet acteur acceptera tout type d’interaction
	@Override
	public boolean isViewInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		ghost.draw(canvas);

	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub

	}

}
