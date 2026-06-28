package com.classes.spell;
import com.classes.sorcerer.Sorcerer;

public class AvadaKedavraSpell implements Spell 
{
	@Override
	public String getName()
	{
		return "Avada Kedavra";
	}
	
	@Override
	public int getMagicLevelRequired()
	{
		return 10;
	}
	
	@Override		
	public void execute(Sorcerer caster, Sorcerer target)
    {
		target.instantKill();
    }
}
