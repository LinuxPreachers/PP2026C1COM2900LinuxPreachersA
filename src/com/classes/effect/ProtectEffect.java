package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class ProtectEffect extends Effect 
{
    private double percentagePerTurn;

    public ProtectEffect(double percentagePerTurn, int turnsDuration) 
    {
        super("Proteccion", turnsDuration, Effect.EffectPolarity.BENEFICIAL);
        this.percentagePerTurn = Math.clamp(percentagePerTurn, 0.0f, 1.0f);
    }
    
    @Override
    public int filterReceivedDamage(int damagePoints) 
    {
    	return (int)(damagePoints * (1.0f - this.percentagePerTurn));
    }

    @Override
    protected void applyLogic(Sorcerer target) 
    {
    }
}