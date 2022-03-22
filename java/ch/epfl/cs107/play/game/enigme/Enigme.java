package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.area.Enigme0;
import ch.epfl.cs107.play.game.area.Enigme1;
import ch.epfl.cs107.play.game.area.Level1;
import ch.epfl.cs107.play.game.area.Level2;
import ch.epfl.cs107.play.game.area.Level3;
import ch.epfl.cs107.play.game.area.LevelSelector;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the
 * notion of Player When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

	/// The player is a concept of RPG games
	// TODO implements me #PROJECT
	private LevelSelector levelSelector;
	private Level1 level1;
	private Level2 level2;
	private Level3 level3 ;
	private Enigme0 enigme0 ;
	private Enigme1 enigme1 ;
	private EnigmePlayer actor;

	

	/// Enigme implements Playable

	@Override
	public String getTitle() {
		return "Enigme";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		// TODO implements me #PROJECT
		super.begin(window, fileSystem);
		
		levelSelector = new LevelSelector();
		level1 = new Level1();
		level2 = new Level2();
		level3 = new Level3();
		enigme0 = new Enigme0();
		enigme1 = new Enigme1();
		
		super.addArea(level1);
		super.addArea(level2);
		super.addArea(levelSelector);
		super.addArea(level3);
		super.addArea(enigme0);
		super.addArea(enigme1);
		
		levelSelector.begin(window,fileSystem);
		level1.begin(window, fileSystem);
		level2.begin(window, fileSystem);
		level3.begin(window, fileSystem);
		enigme0.begin(window, fileSystem);
		enigme1.begin(window, fileSystem);
		
		Area currentArea = setCurrentArea("LevelSelector", false);
		actor = new EnigmePlayer(currentArea, new DiscreteCoordinates(5, 5));
		currentArea.setViewCandidate(actor);
		levelSelector.registerActor(actor);
		actor.begin(window, fileSystem);

		return true;
	}

	@Override
	public void update(float deltaTime) {

		super.update(deltaTime);
 		
		if (actor.getIsPassing()) {
			Door door = actor.passedDoor();
			String s = door.getArrivalArea();
			if(!s.equals("")) {
			System.out.println(s);
			actor.leaveArea();
			setCurrentArea(s,false);
			actor.enterArea(getCurrentArea(), door.getArrivalCoordinates());
			System.out.println(getCurrentArea());
			actor.update(deltaTime);
			}
		}
		
	}

	@Override
	public int getFrameRate() {
		return 24;
	}
}
