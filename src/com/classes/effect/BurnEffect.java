package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class BurnEffect extends Effect 
{
    private int damagePerTurn;

    public BurnEffect(String description, int damagePerTurn, int turnsDuration) 
    {
        super(description, turnsDuration);
        this.damagePerTurn = damagePerTurn;
    }
    
    @Override
    public int filterReceivedDamage(int damagePoints) 
    {
    	return damagePoints + damagePerTurn;
    }

    @Override
    protected void applyLogic(Sorcerer target) 
    {
        target.receiveDamage(this.damagePerTurn);
    }
}