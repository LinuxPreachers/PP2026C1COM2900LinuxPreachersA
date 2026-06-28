package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class HealEffect extends Effect 
{
	private int healingPerTurn;
	
	public HealEffect(String description, int healingPerTurn, int turnsDuration) 
    {
        super(description, turnsDuration);
        this.healingPerTurn = healingPerTurn;
    }
	
    @Override
    public int filterReceivedDamage(int damagePoints) 
    {
    	return damagePoints;
    }
	
	@Override
	protected void applyLogic(Sorcerer target) 
	{
		target.heal(healingPerTurn);
	}
}
