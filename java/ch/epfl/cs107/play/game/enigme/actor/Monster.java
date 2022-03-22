
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Monster extends Collectable {

	private Sprite monster;
	private Logic signal;
	Vector anchor = new Vector(0.20f, 0.20f);

	public Monster(Area area, DiscreteCoordinates position, boolean collecte, Logic signal) {
		super(area, position, collecte);
		monster = new Sprite("mob.1", 1f, 1f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
		this.signal = signal;

	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {

		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		System.out.println(signal.isOn());
		if (signal.isOn()) {
			System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLJJ");
			return true;
		} else {
			return false;
		}

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
		monster.draw(canvas);
	}

	public void setCollecte(boolean collecte) {
		this.collecte = collecte;
	}

}