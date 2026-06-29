package com.classes.sorcerer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.classes.effect.Effect;
import com.classes.spell.Spell;
import com.classes.spell.SpellRepository;
import com.classes.spell.MagicType;

public abstract class Sorcerer 
{
	protected String name;
	protected int level; // [0, 100]
	protected int healthPoints; 
	protected int maxHealthPoints;
	protected Set<Spell> learnedSpells = new HashSet<>();
	protected Map<MagicType, Double> knownMagicTypes = new HashMap<>();
	protected Set<Effect> activeEffects = new HashSet<>();
	
	/* Constructores */
	
	public Sorcerer(String name, int level, int healthPoints)
	{
		this.name = name;
		this.level = level;
		this.healthPoints = healthPoints;
		this.maxHealthPoints = healthPoints;
	}
	
	// Getters
	
	public String getName() {
		return name; 
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getHealthPoints() { 
		return healthPoints; 
	}
	
	public Set<MagicType> getKnownMagicTypes() {
		return Collections.unmodifiableSet(knownMagicTypes.keySet());
	}
	
	public Set<Spell> getLearnedSpells() {
		return Collections.unmodifiableSet(learnedSpells);
	}
	
	public double getModifier(MagicType magicType) {
	    return knownMagicTypes.getOrDefault(magicType, 1d);
	}
	
	// Metodos
	
	public void learnSpells() {
		for (Spell spell : SpellRepository.SPELLS) {
			
			boolean knowsType = knownMagicTypes.containsKey(spell.getMagicType());
			boolean hasRequiredLevel = level >= spell.getRequiredLevel();
			
			if (knowsType && hasRequiredLevel)
				learnedSpells.add(spell);
		}
	}
	
	/*
	 * Retorna true en caso de que el personaje ya no tenga puntos
	 * de vida luego de recibir el danio
	 */
	public void heal(int points) {
		if (points <= 0) {
			throw new IllegalArgumentException("Heal points cannot be negative or zero.");
		}
				
		this.healthPoints = Math.min(100, this.healthPoints + points);
	}
	
	public boolean receiveDamage(int damagePoints) {
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
	
	public void addEffect(Effect effect) {
		
		boolean blocked = false;
		Effect current;
		
		for (Iterator<Effect> it = this.activeEffects.iterator(); it.hasNext() && !blocked;) {
			current = it.next();
			blocked = current.blocks(effect);
        }
		
		if (!blocked)
			this.activeEffects.add(effect);
	}
	
	public void removeEffect(Effect effect) {
		this.activeEffects.remove(effect);
	}
	
	public void clearEffects() {
		this.activeEffects.clear();
	}
	
	public void clearEffects(Effect.EffectPolarity polarity) {
		
		Iterator<Effect> iterator = activeEffects.iterator();

		while (iterator.hasNext()) {
		    Effect effect = iterator.next();

		    if (effect.getPolarity() == polarity) {
		        iterator.remove();
		    }
		}
		
	}
	
	public void instantKill() {
	    this.healthPoints = 0; 
	}
	
	public void onTurnStart() {
        activeEffects.forEach(effect -> effect.act(this));
        activeEffects.removeIf(Effect::isExpired);
    }
	
	public boolean cast(Spell spell, Sorcerer target) {
		
		if (spell == null || target == null | !learnedSpells.contains(spell))
			return false;
		
		return spell.cast(this, target);
	}
	
	public boolean attack() {
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
}






















