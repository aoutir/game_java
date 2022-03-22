package ch.epfl.cs107.play.game.enigme.actor;
import java.util.Collections;
import java.util.List;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Shield extends Collectable implements Logic{
 private Sprite shield;
	public Shield(Area area, DiscreteCoordinates position, boolean collecte) {
		super(area, position, collecte);
		shield = new Sprite("shield.1",0.75f,0.75f,this);
		
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return !collecte;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		shield.draw(canvas);
	}

	@Override
	public boolean isOn() {
		System.out.println(collecte);
		return collecte;
	}
	@Override
	protected void setCollecte(boolean collecte) {
		super.setCollecte(collecte);
	}
	@Override
	public void leaveArea() {
	        super.leaveArea();
	
	}
}
