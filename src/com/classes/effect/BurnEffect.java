package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class BurnEffect extends Effect 
{
	
	public final static int DAMAGE_PER_TURN = 5;
	private double casterEffectiveness;

    public BurnEffect(int turnsDuration, double casterEffectiveness) 
    {
        super("Quemadura", turnsDuration, Effect.EffectPolarity.HARMFUL);
        this.casterEffectiveness = casterEffectiveness;
    }
    
    @Override
    public int filterReceivedDamage(int damagePoints) 
    {
    	return damagePoints + (int) Math.round(DAMAGE_PER_TURN * casterEffectiveness);
    }

    @Override
    protected void applyLogic(Sorcerer target) 
    {
        target.receiveDamage((int) Math.round(DAMAGE_PER_TURN * casterEffectiveness));
    }
}