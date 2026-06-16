package com.classes.effect;
import com.classes.character.Character;

public abstract class BaseEffect implements Effect
{
	protected String name;
	protected int turnsDuration;
    
    public BaseEffect(String name, int turnsDuration) 
    {
        this.name = name;
        this.turnsDuration = turnsDuration;
    }
    
    @Override
    public void onTurnStart(Character target)
    {
        applyEffectLogic(target);
        this.turnsDuration--;
    }
    
    protected abstract void applyEffectLogic(Character target);
    
    @Override
    public int filterReceivedDamage(int damagePoints) 
    {
        return damagePoints;
    }

    @Override
    public boolean canAttack() {
        return true;
    }

    @Override
    public boolean isExpired() {
        return this.turnsDuration <= 0;
    }

    @Override
    public String getName() {
        return this.name;
    }
   
}
