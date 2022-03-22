
package ch.epfl.cs107.play.game.enigme;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


public class Demo2Behavior extends AreaBehavior {

	
	
	public enum Demo2CellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);

		final int type;

		private Demo2CellType(int type) {
			this.type = type;
		}

		public static Demo2CellType toType(int type) {
			for (Demo2CellType d : Demo2CellType.values()) {
				if (d.type == type) {
					return d;
				}
			}
			return NULL;
		}
	}
	public class Demo2Cell extends Cell {

		private Demo2CellType type;

		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x, y);
			this.type = type;

		}
		@Override
		protected  boolean canEnter(Interactable entity) {
			if(this.type.equals(Demo2CellType.WALL)  || this.type.equals(Demo2CellType.NULL)) {
				return false ;
			}else {
				return true;
			}
			
		}
		protected boolean canLeave(Interactable entity ) {
			return true ;
		}
		
		public  Demo2CellType getType() {
			return type ;
		}

		@Override
		public boolean takeCellSpace() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isViewInteractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isCellInteractable() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public Demo2Behavior(Window window , String fileName) {
		super(window, fileName);
		int w = super.getWidth();
		int h = super.getHeight();
		Demo2CellType cellType ;
		
		   for( int x = 0 ; x < w ; x++) {
	            for( int y = 0 ; y < h ; y++) {
	                 cellType = Demo2CellType.toType(getBehaviorMap().getRGB(h-1-y, x));
	                 cells [x][y] = new Demo2Cell(x,y,cellType);
		}
	}
	}
	public boolean isDoor(DiscreteCoordinates dc ) {
	return ((Demo2Cell)getCellule(dc)).getType() == Demo2CellType.DOOR ;
	}
}