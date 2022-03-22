
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.signal;

public interface Logic extends Signal {
	//la définition concrète dépend du type spécifique de signal logique implémenté ;  
	boolean isOn();
	
	default float getIntensity() {
		if(isOn()) {
			return 1.0f;
		}
		else {
			return 0.0f;
		}
	}
	
	@Override
	default float getIntensity(float t) {
		return getIntensity();
	}
	//on cree une classe anonyme 
	//signal toujours active 
	 Logic TRUE = new Logic() {
		@Override
		public boolean isOn() {
			return true;
		}
	};
	//signal toujours desactive 
	 Logic FALSE = new Logic() {
			@Override
			public boolean isOn() {
				return false;
			}
		};
	

}
