package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class BurnEffect extends Effect 
{
	
	public final static int DAMAGE_PER_TURN = 5;
	public final static String NAME = "Quemadura";
	
	private double casterEffectiveness;

    public BurnEffect(int turnsDuration, double casterEffectiveness) 
    {
        super(NAME, turnsDuration, Effect.EffectPolarity.HARMFUL);
        this.casterEffectiveness = casterEffectiveness;
    }
    
    @Override
    public int filterReceivedDamage(int damagePoints) 
    {
    	return damagePoints + (int) Math.round(DAMAGE_PER_TURN * casterEffectiveness);
    }

    @Override
    protected void applyLogic(Sorcerer target) 
    {
    	int damage = (int) Math.round(DAMAGE_PER_TURN * casterEffectiveness);
        target.receiveDamage(damage);
        // System.out.println(target.getName() + " sufre " + damage + " puntos de daño debido al efecto de " + this.name);
    }
}