
package ch.epfl.cs107.play.game.area;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

 abstract class EnigmeArea extends Area {
	private final String title ;

	public EnigmeArea(String title ) {
		this.title = title ;
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		setBehavior(new EnigmeBehavior(window, getTitle()));
		return true;
	}

	public float getCameraScaleFactor() {
		return 25f;
	}
	
	public abstract String getTitle();
	
	protected abstract void  addActors();
		
}
