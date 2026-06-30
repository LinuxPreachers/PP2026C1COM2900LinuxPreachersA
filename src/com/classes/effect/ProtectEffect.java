package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class ProtectEffect extends Effect 
{
	public final static int TURNS_DURATION = 2;
	public final static double BASE_PROTECTION_PERCENTAGE = 0.25d;
		
    private double casterEffectiveness;

    public ProtectEffect(double casterEffectiveness) 
    {
        super("Protección", TURNS_DURATION, Effect.EffectPolarity.BENEFICIAL);
		this.casterEffectiveness = casterEffectiveness;
    }
    
    @Override
    public int filterReceivedDamage(int damagePoints) 
    {
    	double protectionPercentage = 
    		Math.clamp(BASE_PROTECTION_PERCENTAGE * casterEffectiveness, 0d, 1d);
    	
    	return (int) Math.round(damagePoints * (1d - protectionPercentage));
    }

    @Override
    protected void applyLogic(Sorcerer target) 
    {
    }
    
    @Override
    public boolean blocks(Effect effect) {
    	return effect.getPolarity() == Effect.EffectPolarity.HARMFUL;
    }
}
