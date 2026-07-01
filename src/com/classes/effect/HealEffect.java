package com.classes.effect;
import com.classes.sorcerer.Sorcerer;

public class HealEffect extends Effect {
	public final static int 
		HEALING_PER_TURN = 5,
		TURNS_DURATION = 2;
	
	public final static String NAME = "Curación";
	private double casterEffectiveness;
	
	public HealEffect(double casterEffectiveness) {
        super(NAME, TURNS_DURATION, Effect.EffectPolarity.BENEFICIAL);
        this.casterEffectiveness = casterEffectiveness;
    }
	
	@Override
	protected void applyLogic(Sorcerer target) {
		int healedHP = (int) Math.round(HEALING_PER_TURN * casterEffectiveness);
		target.heal(healedHP);
		// System.out.println(target.getName() + " se cura " + healedHP + " puntos de salud debido al efecto de " + this.name);
	}
}
