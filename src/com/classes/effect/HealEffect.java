package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class HealEffect extends Effect 
{
	public final static int 
		HEALING_PER_TURN = 5,
		TURNS_DURATION = 2;
	
	private double casterEffectiveness;
	
	public HealEffect(double casterEffectiveness) 
    {
        super("Curación", TURNS_DURATION, Effect.EffectPolarity.BENEFICIAL);
        this.casterEffectiveness = casterEffectiveness;
    }
	
	@Override
	protected void applyLogic(Sorcerer target) 
	{
		target.heal((int) Math.round(HEALING_PER_TURN * casterEffectiveness));
	}
}
