package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class PetrifyEffect extends Effect 
{	
	public PetrifyEffect(int turnsDuration) 
    {
        super("Petrificacion", turnsDuration, Effect.EffectPolarity.NEUTRAL);
    }
    
    @Override
    public boolean canAttack()
    {
    	return false;
    }

	@Override
	protected void applyLogic(Sorcerer target) 
	{
	}
}
