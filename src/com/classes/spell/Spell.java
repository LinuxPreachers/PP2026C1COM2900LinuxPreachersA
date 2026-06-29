package com.classes.spell;
import com.classes.sorcerer.Sorcerer;
import com.classes.effect.Effect;

public abstract class Spell {
	protected String name;
	protected MagicType magicType;
	protected int requiredLevel;

	public Spell(String name, MagicType magicType, int requiredLevel) {
		this.name = name;
		this.magicType = magicType;
		this.requiredLevel = requiredLevel;
	}
	
    public String getName() {
    	return this.name;
    }
    
    public MagicType getMagicType() {
    	return this.magicType;
    }
    
    public int getRequiredLevel() {
    	return this.requiredLevel;
    }
    
    public abstract boolean cast(Sorcerer caster, Sorcerer target);
}