package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public abstract class Effect
{
	public enum EffectPolarity {
		BENEFICIAL,
		HARMFUL,
		NEUTRAL
	}
	
	protected String name;
	protected int turnsDuration;
	protected EffectPolarity polarity;
	    
    public Effect(String name, int turnsDuration, EffectPolarity polarity) 
    {
        this.name = name;
        this.turnsDuration = turnsDuration;
        this.polarity = polarity;
    }
    
    public void act(Sorcerer target)
    {
        applyLogic(target);
        this.turnsDuration--;
    }
    
    public boolean isExpired() 
    {
        return this.turnsDuration <= 0;
    }
    
    public String getName() 
    {
        return this.name;
    }
    
    public EffectPolarity getPolarity() 
    {
        return this.polarity;
    }
        
    /*
     * Por defecto, los efectos permiten atacar.
     */
    public boolean canAttack()
    {
    	return true;
    }
    
    public int filterReceivedDamage(int damagePoints) 
    {
    	return damagePoints;
    }
    
    protected abstract void applyLogic(Sorcerer target);
}
