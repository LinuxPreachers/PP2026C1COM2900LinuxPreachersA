package com.classes.spell;
import com.classes.sorcerer.Sorcerer;
import com.classes.effect.BurnEffect;

public class IncendioSpell implements Spell
{
	@Override
	public String getName()
	{
		return "Incendio";
	}
	
	@Override
	public int getMagicLevelRequired()
	{
		return 5;
	}
	
	@Override		
	public void execute(Sorcerer caster, Sorcerer target)
    {
		target.receiveDamage(10);
		target.addEffect(new BurnEffect("Quemadura de Incendio", 5, 3));
    }
}
