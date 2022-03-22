
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Logic;

public abstract class LogicSignal implements Logic {

	@Override
	public final float getIntensity() {
		return getIntensity();
	}

	@Override
	//retourne 1 s
	public final float getIntensity(final float t) {
		
		if(isOn()) {
			return 1.0f;
		}
		else {
			return 0.0f;
		}
	}
}
