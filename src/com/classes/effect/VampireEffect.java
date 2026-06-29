package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class VampireEffect extends Effect 
{
    private int drainPerTurn;
    private Sorcerer caster;

    public VampireEffect(int drainPerTurn, int turnsDuration, Sorcerer caster) 
    {
        super("Vampirismo", turnsDuration, Effect.EffectPolarity.HARMFUL);
        this.drainPerTurn = drainPerTurn;
        this.caster = caster;
    }

    @Override
    protected void applyLogic(Sorcerer target) 
    {
    	target.receiveDamage(drainPerTurn);
    	caster.heal(drainPerTurn);
    }
}