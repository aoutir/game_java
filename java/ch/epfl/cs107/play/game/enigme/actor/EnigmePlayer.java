
/*
 *	Author:      Hafsa Aoutir
 *	Date:        4 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {

	private boolean isPassing = false;
	private Window window;
	private FileSystem fileSystem;
	private final static int ANIMATION_DURATION = 8;
	private Door door;
	private EnigmePlayerHandler handler = new EnigmePlayerHandler();
	private Dialog dialog;
	private Sprite spriteReposDown;
	private Sprite spriteReposUp;
	private Sprite spriteReposLeft;
	private Sprite spriteReposRight;
	private Sprite sprite;
	private boolean drawDialog ;
	Vector anchor = new Vector(0.10f, 0.32f);
	static int step;

	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		enterArea(area, coordinates);
		dialog = new Dialog("", "dialog.3", area);

	}

	public EnigmePlayer(Area area, DiscreteCoordinates coordinates) {
		this(area, Orientation.DOWN, coordinates);
	}

	public void enterArea(Area area, DiscreteCoordinates position) {
		this.setOwnerArea(area);
		area.registerActor(this);
		isPassing = false;
		setCurrentPosition(position.toVector());
		area.setViewCandidate(this);
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
		
       
		Keyboard keyboard = getOwnerArea().getWindow().getKeyboard();
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
		if(getIsMoving()) {
			drawDialog = false ;
		}
		

	}

	public final boolean getIsPassing() {
		return isPassing;
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	// memoriser la porte qu'il est entrain de passer door
	// indiquer qu'il est entrain de passer une porte ===> isPassing = true
	public void setIsPassingDoor(Door door) {
		isPassing = true;
		this.door = door;
	}

	// retourne la derniere porte passee
	public Door passedDoor() {
		return door;
	}

	public void setIsPassing(boolean isPassing) {
		this.isPassing = isPassing;
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> fieldofview = new LinkedList<>();
		Orientation orientation = this.getOrientation();
		if (orientation.equals(Orientation.DOWN)) {
			fieldofview.add(this.getCurrentMainCellCoordinates().jump(new Vector(0, -1f)));
		}
		if (orientation.equals(Orientation.UP)) {
			fieldofview.add(this.getCurrentMainCellCoordinates().jump(new Vector(0, 1f)));
		}
		if (orientation.equals(Orientation.LEFT)) {
			fieldofview.add(this.getCurrentMainCellCoordinates().jump(new Vector(-1f, 0)));
		}
		if (orientation.equals(Orientation.RIGHT)) {
			fieldofview.add(this.getCurrentMainCellCoordinates().jump(new Vector(1f, 0)));
		}
		return fieldofview;
	}

	// accepte tout type d'interaction
	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		Keyboard keyboard = getOwnerArea().getKeyboard();
		Button LTouch = keyboard.get(Keyboard.L);
		if (LTouch.isPressed()) {
			return true;
		}
		return false;
	}

	// @Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		this.fileSystem = fileSystem;
		this.window = window;
		return true;
	}

	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
		// interaction avec la porte
		public void interactWith(Door door) {
			setIsPassingDoor(door);

		}

		// interaction avec la pomme
		@Override
		public void interactWith(Apple apple) {

			apple.setCollecte(true);
			apple.leaveArea();
		}

		// interaction avec la torche
		// on change l'etat de la torche active ou pas
		// apres on change de sprite
		@Override
		public void interactWith(Torche torche) {
			torche.setActive(!torche.getActive());
			torche.switchSprite();
		}

		// interaction avec le levier
		// on change l'etat du levier active ou pas
		// apres on change de sprite
		@Override
		public void interactWith(Lever levier) {
			levier.setActive(!levier.getActive());
			levier.switchSprite();
		}

		@Override
		public void interactWith(Key key) {
			key.setCollecte(true);
			key.leaveArea();

		}

		@Override
		public void interactWith(PressureSwitch pressureSwitch) {

			pressureSwitch.setActive(!pressureSwitch.getActive());
			pressureSwitch.switchSprite();

		}

		@Override
		public void interactWith(PressurePlate pressurePlate) {
			pressurePlate.setActive(true);
			pressurePlate.switchSprite();

		}

		@Override
		public void interactWith(SignalDoor signalDoor) {
			setIsPassingDoor(signalDoor);
		}

		@Override
		public void interactWith(Coin coin) {
			coin.setCollecte(true);
			coin.leaveArea();
		}

		@Override
		public void interactWith(MovingRock1 movingRock) {
			drawDialog = true ;
			dialog.resetDialog("Pour bouger la roche faut cliquer sur la touche L ");
			movingRock.setOrientation(EnigmePlayer.this.getOrientation());
			movingRock.move(ANIMATION_DURATION);
		}
		@Override
		public void interactWith(Gold gold) {
			gold.setCollecte(true);
			gold.leaveArea();
		}
		@Override
		public void interactWith(OldMan oldMan) {
			drawDialog = true ;
			dialog.resetDialog("Salutation a toi jeune aventurier ! Pour sortir de cette carte, tu devras d'abord collecter assez de ressources(armes et or et pieces, pour pouvoir affronter les Terribles monstres qui gardent la sortie)");
		}
		public void interactWith(Sword sword) {
			 sword.setCollecte(true);
        sword.leaveArea();
       
		}
		public void interactWith(Helmet helmet) {
			 helmet.setCollecte(true);
	        helmet.leaveArea();
	       
	       
			}
		public void interactWith(Shield shield) {
			 shield.setCollecte(true);
	        shield.leaveArea();
	      
			}
		public void interactWith(Monster monster) {
			drawDialog = true ;
			dialog.resetDialog("HAHAHAH t'es pas assez fort pour me tuer !! ");
			monster.setCollecte(true);
			monster.leaveArea();
			
		}
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		step++;
		step = step % 4;
		Orientation orientation = this.getOrientation();

		if (orientation.equals(Orientation.DOWN)) {
			if (getIsMoving()) {
				sprite = new Sprite("max.new.1", 1f, 1.2f, this, new RegionOfInterest(0, step * 21, 16, 21),
						anchor);
				sprite.draw(canvas);
			} else {
				spriteReposDown = new Sprite("max.new.1", 1f, 1.2f, this, new RegionOfInterest(0, 0, 16, 21),
						anchor);
				spriteReposDown.draw(canvas);
			}
		}
		if (orientation.equals(Orientation.LEFT)) {
			if (getIsMoving()) {
				sprite = new Sprite("max.new.1", 1f, 1.2f, this, new RegionOfInterest(16, step * 21, 16, 21),
						anchor);
				sprite.draw(canvas);
			} else {
				spriteReposLeft = new Sprite("max.new.1", 1f, 1.2f, this, new RegionOfInterest(16, 0, 16, 21),
						anchor);
				spriteReposLeft.draw(canvas);
			}
		}
		if (orientation.equals(Orientation.RIGHT)) {
			if (getIsMoving()) {
				sprite = new Sprite("max.new.1", 1f, 1.2f, this, new RegionOfInterest(48, (step) * 21, 16, 21),
						anchor);
				sprite.draw(canvas);
			} else {
				spriteReposRight = new Sprite("max.new.1", 1f, 1.2f, this, new RegionOfInterest(48, 0, 16, 21),
						anchor);
				spriteReposRight.draw(canvas);
			}
		}

		if (orientation.equals(Orientation.UP)) {
			if (getIsMoving()) {
				sprite = new Sprite("max.new.1", 1f, 1.2f, this, new RegionOfInterest(32, (step) * 21, 16, 21),
						anchor);
				sprite.draw(canvas);
			} else {
				spriteReposUp = new Sprite("max.new.1", 1f, 1.2f, this, new RegionOfInterest(32, 0, 16, 21),
						anchor);
				spriteReposUp.draw(canvas);
			}
		}
		if(drawDialog) {
			dialog.draw(canvas);

		}
	}

}