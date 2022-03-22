
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Logic;

public class Or extends LogicSignal {

	private Logic s1 ;
	private Logic s2 ;
	
	public Or(Logic s1 , Logic s2 ) {
		this.s1 = s1 ; 
		this.s2  = s2 ;
	}

	@Override
	public boolean isOn() {
		if ((s1.isOn() || s2.isOn()) && (s1 != null && s2 != null)) {
			return true;
		}
		return false;

	}
}
