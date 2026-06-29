package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class HealEffect extends Effect 
{
	private int healingPerTurn;
	
	public HealEffect(int healingPerTurn, int turnsDuration) 
    {
        super("Curacion", turnsDuration, Effect.EffectPolarity.BENEFICIAL);
        this.healingPerTurn = healingPerTurn;
    }
	
	@Override
	protected void applyLogic(Sorcerer target) 
	{
		target.heal(healingPerTurn);
	}
}
