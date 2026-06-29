package com.classes.sorcerer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.classes.effect.Effect;
import com.classes.spell.Spell;

public abstract class Sorcerer 
{
	protected String name;
	protected int magicLevel;
	protected int healthPoints; // [0, 100]
	protected List<Spell> learnedSpells = new ArrayList<>();
	protected List<Effect> activeEffects = new ArrayList<>();
	
	/* Constructores */
	
	public Sorcerer(String name, int magicLevel, int healthPoints)
	{
		this.name = name;
		this.magicLevel = magicLevel;
		this.healthPoints = healthPoints;
	}
	
	// Getters

	public int getHealthPoints()
	{ 
		return healthPoints; 
	}
	
	public String getName() 
	{
		return name; 
	}
	
	// Metodos
	
	/*
	 * Retorna true en caso de que el personaje ya no tenga puntos
	 * de vida luego de recibir el danio
	 */
	public boolean receiveDamage(int damagePoints)
	{
		if (damagePoints <= 0) {
			throw new IllegalArgumentException("Damage points cannot be negative or zero.");
		}
		
		int finalDamage = damagePoints;
        for (Effect effect : activeEffects) {
            finalDamage = effect.filterReceivedDamage(finalDamage);
        }
		
		if (finalDamage >= this.healthPoints) {
			this.healthPoints = 0;
		} else {
			this.healthPoints -= finalDamage;
		}
		
		return healthPoints == 0;
	}
	
	public void instantKill() 
	{
	    this.healthPoints = 0; 
	}
	
	public void heal(int points)
	{
		if (points <= 0) {
			throw new IllegalArgumentException("Heal points cannot be negative or zero.");
		}
				
		this.healthPoints = Math.min(100, this.healthPoints + points);
	}
	
	public void addEffect(Effect effect)
	{
		this.activeEffects.add(effect);
	}
	
	public void onTurnStart() 
	{
        activeEffects.forEach(effect -> effect.act(this));
        activeEffects.removeIf(Effect::isExpired);
    }
	
	public boolean attack()
	{
		if (healthPoints == 0) {
			return false;
		}
		
		for (Effect effect : activeEffects) {
            if (!effect.canAttack()) {
                return false; 
            }
        }
		
        return true;
	}
	
	public List<Spell> getLearnedSpells() {
		return Collections.unmodifiableList(learnedSpells);
	}
}






















