package com.engine;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import com.classes.sorcerer.ComparatorSorcererLife;
import com.classes.sorcerer.Sorcerer;
import com.classes.sorcerer.SorcererCreator;
import com.classes.spell.MagicType;
import com.classes.spell.Spell;


public class Team 
{
	private Set<Sorcerer> sorcerers = new TreeSet<>();
	private Set<Spell> spellsUsedInTurn = new HashSet<>();
	private Map<Sorcerer, List<Spell>> spellsUsedPerSorcerer = new HashMap<Sorcerer, List<Spell>>();
	
	private int dyingPartnerLife = 5;
	private int dyingEnemyLife = 5;
	private boolean printEvents = false;
	private String outputEvents="";
	
	public static record DecisionAI(Spell spell, Sorcerer target) {}
	
	public int getDyingPartnerLife() {
		return dyingPartnerLife;
	}

	public void setDyingPartnerLife(int dyingPartnerLife) {
		this.dyingPartnerLife = dyingPartnerLife;
	}

	public int getDyingEnemyLife() {
		return dyingEnemyLife;
	}

	public void setDyingEnemyLife(int dyingEnemyLife) {
		this.dyingEnemyLife = dyingEnemyLife;
	}
	
	public void activePrintEvents() {
		printEvents=true;
	}
	
	public void deactivePrintEvents() {
		printEvents=false;
	}
	
	public String consumeEvents() {
		if (!printEvents) {
			return "";
		}
		String rv = outputEvents;
		outputEvents = "";
		return rv;
	}
	
	private void outputEvent(String out) {
		outputEvents += "\n" + out;
	}

	public void attackRandom(Team targetTeam) {
		
		spellsUsedInTurn.clear();
		
		if (targetTeam.getSorcerers().isEmpty()) {
			System.err.println("Se intento atacar un equipo sin magos");
			return;
		}
		
		// random
		Random random = new Random();
		int randomIndex = -1;
		
		spellsUsedInTurn.clear();
		
		for (Sorcerer s : sorcerers) {
			
			if (s.getHealthPoints() > 0) { // alive

			    randomIndex = random.nextInt(sorcerers.size());
			    Spell spell = this.chooseSpellRandom(s, null);
			    
			    if (spell != null) { // can cast any spell

				    if (spell.getMagicType() == MagicType.OFFENSIVE || spell.getMagicType() == MagicType.DARK_ARTS || spell.getMagicType() == MagicType.CONTROL) {
				   
						// get random sorcerer from target team
					    List<Sorcerer> enemies = new ArrayList<>(targetTeam.sorcerers);
					    randomIndex = random.nextInt(enemies.size());
					    Sorcerer enemy = enemies.get(randomIndex);
				    	
				    	s.cast(spell, enemy);
				    	
				    	outputEvent(s.getName() + " uso " + spell.getName() + " contra " + enemy.getName());
				    	
				    } else {
				    	
				    	// get the least life sorcerer from our team
				    	List<Sorcerer> allies = new ArrayList<>(this.sorcerers);
				    	Sorcerer ally = Collections.min(allies, new ComparatorSorcererLife());
				    	
				    	s.cast(spell, ally);
				    	
				    	outputEvent(s.getName() + " uso " + spell.getName() + " contra " + ally.getName());
				    }
				    
				    spellsUsedInTurn.add(spell);
				    spellsUsedPerSorcerer.getOrDefault(s, new ArrayList<Spell>()).add(spell);
			    	
			    } else {
			    	
			    	outputEvent(s.getName() + " no tiene que hacer ");
			    	
			    }
				
			}
		
		}
		
		spellsUsedInTurn.clear();
		
	}
	
	public void attackAI(Team targetTeam) {
		
		spellsUsedInTurn.clear();
		
		if (targetTeam.getSorcerers().isEmpty()) {
			System.err.println("Se intento atacar un equipo sin magos");
			return;
		}
		
		spellsUsedInTurn.clear();
		
		for (Sorcerer s : sorcerers) {
			
			if (s.getHealthPoints() > 0) { // alive
				DecisionAI decision = this.chooseAI(s, targetTeam, null);
				
	            if (decision != null && decision.spell() != null && decision.target() != null) {
	                s.cast(decision.spell(), decision.target());
	                spellsUsedInTurn.add(decision.spell());
	                spellsUsedPerSorcerer.getOrDefault(s, new ArrayList<Spell>()).add(decision.spell());
	            }
			}
		
		}
		
		spellsUsedInTurn.clear();
	}
	
	public boolean addSorcerer(Sorcerer s) {
		if (sorcerers.contains(s)) {
			return false;
		}
		sorcerers.add(s);
		return true;
	}
	
	public boolean removeSorcerer(Sorcerer s) {
		if (!sorcerers.contains(s)) {
			return false;
		}
		sorcerers.remove(s);
		return true;
	}
	
	public Set<Sorcerer> getSorcerers() {
		return Collections.unmodifiableSet(sorcerers);
	}
	
	public boolean hasHealthySorcerers() {
		for (Sorcerer s : sorcerers) {
			if (s.getHealthPoints() > 0) {
				return true;
			}
		}
		return false;
	}
	
	public Spell chooseSpellRandom(Sorcerer s, Predicate<Spell> conditionToRemove) {
	    List<Spell> possibleSpells = new ArrayList<>(s.getLearnedSpells());
	    
		for (Spell forbiddenSpell : this.spellsUsedInTurn) {
			possibleSpells.remove(forbiddenSpell);
		}
		
		if (conditionToRemove != null) {
			possibleSpells.removeIf(conditionToRemove);
		}
		
		if (possibleSpells.isEmpty()) {
			return null;
		}
		
	    Random random = new Random();
	    int randomIndex = random.nextInt(possibleSpells.size());

	    return possibleSpells.get(randomIndex);
	}
	
public DecisionAI chooseAI(Sorcerer s, Team targetTeam, Predicate<Spell> conditionToRemove) {
		
		Sorcerer partnerDying = this.memberDying(dyingPartnerLife);
		Sorcerer enemyDying = targetTeam.memberDying(dyingEnemyLife);
		
		List<Spell> possibleSpells = new ArrayList<>(s.getLearnedSpells());
		for (Spell forbiddenSpell : this.spellsUsedInTurn) {
			possibleSpells.remove(forbiddenSpell);
		}
		
		if (conditionToRemove != null) {
			possibleSpells.removeIf(conditionToRemove);
		}
		
		List<Spell> healthSpells = possibleSpells
				.stream()
				.filter(spell -> spell.getMagicType() == MagicType.HEALING)
				.toList();
		
		List<Spell> defensiveSpells = possibleSpells
				.stream()
				.filter(spell -> spell.getMagicType() == MagicType.DEFENSIVE)
				.toList();
		
		List<Spell> offensiveSpells = possibleSpells
				.stream()
				.filter(spell -> spell.getMagicType() == MagicType.OFFENSIVE)
				.toList();

		Random random = new Random();
		int randomIndex;
		
		Spell healthSpell = null;
	    if (!healthSpells.isEmpty()) {
	        randomIndex = random.nextInt(healthSpells.size());
	        healthSpell = healthSpells.get(randomIndex);
	    }
	    
	    Spell deffensiveSpell = null;
	    if (!defensiveSpells.isEmpty()) {
	        randomIndex = random.nextInt(defensiveSpells.size());
	        deffensiveSpell = defensiveSpells.get(randomIndex);
	    }
	    
	    Spell offensiveSpell = null;
	    if (!offensiveSpells.isEmpty()) {
	        randomIndex = random.nextInt(offensiveSpells.size());
	        offensiveSpell = offensiveSpells.get(randomIndex);
	    }
		
	    if (partnerDying != null && (healthSpell != null || deffensiveSpell != null)) {
	        Spell chosen = (healthSpell != null) ? healthSpell : deffensiveSpell;
	        return new DecisionAI(chosen, partnerDying);
	    }
	    
	    if (enemyDying != null && offensiveSpell != null) {
	        return new DecisionAI(offensiveSpell, enemyDying);
	    }
		
	    int sorcererLevel = s.getLevel();
	    int deltaLevel = 5;
	    Sorcerer similarEnemy = null;
	    for (Sorcerer enemy : targetTeam.sorcerers) {
	        if (Math.abs(enemy.getLevel() - sorcererLevel) <= deltaLevel) {
	            similarEnemy = enemy;
	            break;
	        }
	    }
	    
	    if (similarEnemy != null && offensiveSpell != null) {
	        return new DecisionAI(offensiveSpell, similarEnemy);
	    }
	    
	    Spell randomSpell = this.chooseSpellRandom(s, null);
	    if (randomSpell == null) {
	        return null;
	    }
	    
	    Sorcerer defaultTarget;
	    if (randomSpell.getMagicType() == MagicType.OFFENSIVE || randomSpell.getMagicType() == MagicType.DARK_ARTS || randomSpell.getMagicType() == MagicType.CONTROL) {
	        List<Sorcerer> enemies = new ArrayList<>(targetTeam.sorcerers);
	        defaultTarget = enemies.isEmpty() ? null : enemies.get(random.nextInt(enemies.size()));
	    } else {
	        List<Sorcerer> allies = new ArrayList<>(this.sorcerers);
	        defaultTarget = Collections.min(allies, new ComparatorSorcererLife());
	    }
	    
	    return new DecisionAI(randomSpell, defaultTarget);
	}

	public Sorcerer memberDying(int lifeLimit) {
		List<Sorcerer> members = new ArrayList<>(sorcerers);
		members.sort(new ComparatorSorcererLife());
		
		int i = 0;
		Sorcerer leastLifeMember = null;
		
		while (leastLifeMember == null && members.get(i).getHealthPoints() < lifeLimit) {
			if (members.get(i).getHealthPoints() > 0) { // in case there are death members in list
				leastLifeMember = members.get(i);
			}
			i++;
		}
		
		return leastLifeMember;
	}
	
	public void generateTeam(SorcererCreator sorcererCreator, int members) {
		for (int i=0; i<members; i++) {
			this.addSorcerer(sorcererCreator.createSorcerer());
		}
	}
	
	public void onTurnStart() {
		for (Sorcerer s : sorcerers) {
			s.onTurnStart();
		}
	}
	
	public String toString() {
		String rv = "\nTeam";
		for (Sorcerer s : sorcerers) {
			rv += "\n" + s.getName() + " / " + s.getHealthPoints() + " ❤︎ / lvl" + s.getLevel();
		}
		return rv;
	}
	
}
