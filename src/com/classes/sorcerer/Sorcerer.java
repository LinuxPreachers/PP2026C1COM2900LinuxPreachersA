package com.classes.sorcerer;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.classes.effect.Effect;
import com.classes.spell.Spell;
import com.classes.spell.SpellRepository;
import com.classes.spell.MagicType;

public abstract class Sorcerer implements Comparable<Sorcerer>
{
	protected String name;
	protected int level; // [0, 100]
	protected int healthPoints; 
	protected int maxHealthPoints;
	protected double accuracy;
	
	protected Set<Spell> learnedSpells = new HashSet<>();
	protected Map<MagicType, Double> knownMagicTypes = new HashMap<>();
	protected Set<Effect> activeEffects = new HashSet<>();
	
	public Sorcerer(String name, int level, int healthPoints) {
		this.name = name;
		this.level = level;
		this.healthPoints = healthPoints;
		this.maxHealthPoints = healthPoints;
	}
	
	public String getName() {
		return name; 
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getHealthPoints() { 
		return healthPoints; 
	}
	
	public double getAccuracy() { 
		return accuracy; 
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
	
	@Override
	public int compareTo(Sorcerer s) {

		if (this.level - s.level != 0) {
			return this.level - s.level;
		}
		if (this.healthPoints - s.healthPoints != 0) {
			return this.healthPoints - s.healthPoints;
		}
		if (this.name.compareTo(s.name) != 0) {
			return this.name.compareTo(s.name);
		}
		if (!Objects.equals(s, this)) {
			return 1;
		}
		return 0;
	}
	

	protected void learnSpells() {
		for (Spell spell : SpellRepository.SPELLS) {
			
			boolean knowsType = knownMagicTypes.containsKey(spell.getMagicType());
			boolean hasRequiredLevel = level >= spell.getRequiredLevel();
			
			if (knowsType && hasRequiredLevel)
				learnedSpells.add(spell);
		}
	}
	
	public void heal(int points) {
		if (points <= 0) {
			throw new IllegalArgumentException("Los puntos de curacón no pueden ser negativos o 0.");
		}
				
		this.healthPoints = Math.min(this.maxHealthPoints, this.healthPoints + points);
	}
	
	public boolean receiveDamage(int damagePoints) {	
		
		if (damagePoints <= 0) {
			throw new IllegalArgumentException("Los puntos de daño no pueden ser negativos o 0.");
		}
		
		if (healthPoints <= 0)
			return false;
		
		int finalDamage = damagePoints;
		
        for (Effect effect : activeEffects) {
            finalDamage = effect.filterReceivedDamage(finalDamage);
        }
		
		if (finalDamage >= this.healthPoints) {
			this.healthPoints = 0;
		} else {
			this.healthPoints -= finalDamage;
		}
		
		return true;
	}
	
	public boolean addEffect(Effect effect) {
		
		boolean blocked = false;
		Effect current;
		
		for (Iterator<Effect> it = this.activeEffects.iterator(); it.hasNext() && !blocked;) {
			current = it.next();
			blocked = current.blocks(effect);
        }
		
		if (blocked)
			return false;
			
		this.activeEffects.add(effect);
		return true;
	}
	
	public boolean hasEffect(String name) {
		
		if (name == null || name.trim() == "") {
			throw new IllegalArgumentException("Se debe indicar un nombre de efecto.");
		}
		
		Effect current;
		boolean found = false;
		
		for (Iterator<Effect> it = this.activeEffects.iterator(); it.hasNext() && !found;) {
			current = it.next();
			found = current.getName() == name;
        }
		
		return found;
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
	
	public boolean canAct() {
		
		if (healthPoints <= 0)
			return false;
		
		for (Effect effect : activeEffects) {
            if (!effect.canAttack()) {
                return false; 
            }
        }
		
		return true;
	}
	
	public boolean cast(Spell spell, Sorcerer target) {
		
		if (!canAct() || spell == null || target == null || !learnedSpells.contains(spell))
			return false;
		
		return spell.cast(this, target);
	}
	
	@Override
	public String toString() {
		return "Sourcerer[name=" + this.name + "|life=" + this.healthPoints + "|lvl" + this.level  + "]";
	}
	
	public void resetForBattle() {
	    this.healthPoints = this.maxHealthPoints;
	    this.activeEffects.clear();
	}
}