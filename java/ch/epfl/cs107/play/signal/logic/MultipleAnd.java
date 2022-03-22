
/*
 *	Author:      Hafsa Aoutir
 *	Date:        9 déc. 2018
 */
package ch.epfl.cs107.play.signal.logic;

import java.util.List;

import ch.epfl.cs107.play.signal.Logic;

public class MultipleAnd extends  LogicSignal{
	
	private Logic [] signals ;
	 
	public MultipleAnd(Logic...signals  ) {
		this.signals = signals ;
	}

	@Override
	public boolean isOn() {
		for( int i = 0 ; i < signals.length ; i++) {
			if(signals[i] == null) {
				return false;
			}
			if(!signals[i].isOn()) {
				return false ;
			}
	}
		return true ;
	}
}
