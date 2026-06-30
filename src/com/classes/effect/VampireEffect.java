package com.classes.effect;
import com.classes.sorcerer.Sorcerer;
import com.classes.spell.MagicType;

public class VampireEffect extends Effect 
{
	public final static int 
		DRAIN_PER_TURN = 5,
		TURNS_DURATION = 3;
	
    private Sorcerer caster;

    public VampireEffect(Sorcerer caster) 
    {
        super("Vampirismo", TURNS_DURATION, Effect.EffectPolarity.HARMFUL);
        this.caster = caster;
    }

    @Override
    protected void applyLogic(Sorcerer target) 
    {
    	int drainedHP = (int) Math.round(DRAIN_PER_TURN * caster.getModifier(MagicType.HEALING));
    	
    	target.receiveDamage(drainedHP);
    	caster.heal(drainedHP);
    }
}