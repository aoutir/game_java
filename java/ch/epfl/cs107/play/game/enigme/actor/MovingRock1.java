
/*
 *	Author:      Hafsa Aoutir
 *	Date:        12 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class MovingRock1 extends MovableAreaEntity {
	private Sprite rock;

	public MovingRock1(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		rock = new Sprite("rock.3", 1, 1.f, this);
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		System.out.println("LlLlLlllLlllLllLl");
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		rock.draw(canvas);

	}
	@Override
	protected void setOrientation(Orientation orientation) {
		super.setOrientation(orientation);
	}
	@Override
	protected boolean move(int framesForMove) {
		return super.move(framesForMove);
	}

}
