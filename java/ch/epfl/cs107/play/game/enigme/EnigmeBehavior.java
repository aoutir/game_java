
/*
 *	Author:      Hafsa Aoutir
 *	Date:        4 déc. 2018
 */
package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {

	public enum EnigmeCellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);

		final int type;

		private EnigmeCellType(int type) {
			this.type = type;
		}

		public static EnigmeCellType toType(int type) {
			for (EnigmeCellType d : EnigmeCellType.values()) {
				if (d.type == type) {
					return d;
				}
			}
			return NULL;
		}
	}
	public class EnigmeCell extends Cell {

		private EnigmeCellType type;

		private EnigmeCell(int x, int y, EnigmeCellType type) {
			super(x, y);
			this.type = type;

		}
		protected boolean canEnter(Interactable entity) {
			for( Interactable interactable : entities) {
					if(interactable.takeCellSpace()) {
						System.out.println("11111111111111111111111111111");
						return false ;
					}
			}
			if(this.type.equals(EnigmeCellType.WALL)  || this.type.equals(EnigmeCellType.NULL) ) {
				System.out.println("Waaaaaaaaaaall"+this.type.equals(EnigmeCellType.WALL) );
				System.out.println("NUUUUUULLLLLLLL"+this.type.equals(EnigmeCellType.NULL));
					System.out.println("222222222222222222222222222222222222222222222");
				return false ;
			}else {
				return true;
			}
			
		}
		protected boolean canLeave(Interactable entity) {
			
			return true ;
		}
		public boolean isDoor(Interactable entity) {
			if(this.type.equals(EnigmeCellType.DOOR) ) {
				return true ;
			}else {
				return false;
			}
		}
		
		public  EnigmeCellType getType() {
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
			((EnigmeInteractionVisitor)v).interactWith(this);
			
		}
	}
	
	public EnigmeBehavior(Window window , String fileName) {
		super(window, fileName);
		int w = super.getWidth();
		int h = super.getHeight();
		EnigmeCellType cellType ;
		
		for( int x = 0 ; x < w ; x++) {
			for( int y = 0 ; y < h ; y++) {
				 cellType = EnigmeCellType.toType(getBehaviorMap().getRGB(h-1-y, x));
				 cells [x][y] = new EnigmeCell(x,y,cellType);
				
			}
		}
	}
	
}

