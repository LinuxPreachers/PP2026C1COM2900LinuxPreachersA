package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class PetrifyEffect extends Effect {	
	public final static String NAME = "Petrificción";
	
	public PetrifyEffect(int turnsDuration) {
        super(NAME, turnsDuration, Effect.EffectPolarity.NEUTRAL);
    }
    
    @Override
    public boolean canAttack() {
    	return false;
    }

	@Override
	protected void applyLogic(Sorcerer target) {
	}
}
