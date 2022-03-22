
package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {
	private Room1 room1;
	private Room0 room0;
	private Demo2Player actor;
	static final int FrameRate = 22;

	@Override
	public int getFrameRate() {
		return FrameRate;
	}

	@Override
	public String getTitle() {
		return "Demo2";
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		room1 = new Room1();
		room0 = new Room0();
		super.addArea(room1);
		super.addArea(room0);
		room1.begin(window, fileSystem);
		room0.begin(window, fileSystem);
		Area currentArea = setCurrentArea("LevelSelector", false);
		actor = new Demo2Player(currentArea, new DiscreteCoordinates(5, 5));
		currentArea.setViewCandidate(actor);
		actor.enterArea(currentArea, new DiscreteCoordinates(5, 5));
		return true;
	}

	public void update(float deltaTime) {
		//System.out.println(actor.getIsPassing());
		if (actor.getIsPassing()) {
			
			if (super.getCurrentArea().getTitle().equals("Level1")) {
				actor.leaveArea();
				setCurrentArea("LevelSelector", false);
				actor.enterArea(room0, new DiscreteCoordinates(5, 5));
			    
			} else {
				actor.leaveArea();
				setCurrentArea("Level1", false);
				actor.enterArea(room1, new DiscreteCoordinates(5, 2));
				actor.setIsPassing(false);
				
			}
		}
		super.update(deltaTime);
		actor.update(deltaTime);
	}

}
