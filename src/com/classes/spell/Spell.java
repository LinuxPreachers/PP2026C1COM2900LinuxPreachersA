package com.classes.spell;
import com.classes.sorcerer.Sorcerer;

public abstract class Spell {
	protected String name;
	protected MagicType magicType;
	protected int requiredLevel;
	protected double hitRate;

	public Spell(String name, MagicType magicType, int requiredLevel, double hitRate) {
		this.name = name;
		this.magicType = magicType;
		this.requiredLevel = requiredLevel;
		this.hitRate = hitRate;
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
    
    public double getHitRate() {
    	return this.hitRate;
    }
    
    protected abstract boolean apply(Sorcerer caster, Sorcerer target);
    
    public boolean cast(Sorcerer caster, Sorcerer target) {
    	
    	double probability = this.hitRate * caster.getAccuracy();
    	
    	if (Math.random() > probability)
    		return false;
    	
    	return apply(caster, target);
    }
    
    @Override
    public String toString() {
    	return this.name;
    }
}