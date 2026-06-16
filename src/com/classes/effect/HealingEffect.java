package com.classes.effect;
import com.classes.character.Character;

public class HealingEffect extends BaseEffect 
{
	private int healingPerTurn;
	
	public HealingEffect(String description, int healingPerTurn, int turnsDuration) 
    {
        super(description, turnsDuration);
        this.healingPerTurn = healingPerTurn;
    }

	@Override
	protected void applyEffectLogic(Character target) 
	{
		target.heal(healingPerTurn);
	}
}
