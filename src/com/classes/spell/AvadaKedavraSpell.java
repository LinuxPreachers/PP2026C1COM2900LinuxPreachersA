package com.classes.spell;
import com.classes.character.Character;

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
	public void execute(Character caster, Character target)
    {
		target.instantKill();
    }
}
