package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public abstract class Effect
{
	protected String name;
	protected int turnsDuration;
    
    public Effect(String name, int turnsDuration) 
    {
        this.name = name;
        this.turnsDuration = turnsDuration;
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
        
    /*
     * Por defecto, los efectos permiten atacar.
     */
    public boolean canAttack()
    {
    	return true;
    }
    
    public abstract int filterReceivedDamage(int damagePoints);
    protected abstract void applyLogic(Sorcerer target);
}
