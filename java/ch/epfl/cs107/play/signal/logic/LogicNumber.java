
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Logic;

public class LogicNumber extends LogicSignal{

	private float nb;
	private Logic[] e;

	public LogicNumber(float nb, Logic... e) {
		this.nb = nb;
		this.e = e;
	}

	public boolean isOn() {

		float nbSignal = 0f;
		int indice = 0;

		if (e.length > 12 || nb < 0 || nb > Math.pow(2, e.length)) {
			return false;
		}
	
		for (Logic s : e) {
			nbSignal += Math.pow(2, indice) * (s.getIntensity());
			indice++;
		}
		
		if (nbSignal == nb) {
			return true;
		} else {
			return false;
		}
		
	}
}