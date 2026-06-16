package com.classes.effect;
import com.classes.character.Character;

public class BurnEffect extends BaseEffect 
{
    private int damagePerTurn;

    public BurnEffect(String description, int damagePerTurn, int turnsDuration) 
    {
        super(description, turnsDuration);
        this.damagePerTurn = damagePerTurn;
    }

    @Override
    protected void applyEffectLogic(Character target) 
    {
        target.receiveDamage(this.damagePerTurn);
    }
}