package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class PetrifyEffect extends Effect 
{	
	public PetrifyEffect(String description, int turnsDuration) 
    {
        super(description, turnsDuration);
    }
	
    @Override
    public int filterReceivedDamage(int damagePoints) 
    {
    	return damagePoints;
    }
    
    @Override
    public boolean canAttack()
    {
    	return false;
    }

	@Override
	protected void applyLogic(Sorcerer target) {
		// TODO Auto-generated method stub
		
	}
}
