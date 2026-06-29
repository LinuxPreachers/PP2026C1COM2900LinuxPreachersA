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
import com.classes.spell.Spell;


public class Team {
	private Set<Sorcerer> sorcerers = new HashSet<>();
	private Set<Spell> spellsUsedInTurn = new HashSet<>();
	private Map<Sorcerer, List<Spell>> spellsUsedPerSorcerer = new HashMap<Sorcerer, List<Spell>>();
	
	private int dyingPartnerLife = 5;
	private int dyingEnemyLife = 5;
	
	
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

	public void attack(Team targetTeam) {
		if (targetTeam.getSorcerers().isEmpty()) {
			System.err.println("Se intento atacar un equipo sin magos");
			return;
		}

		// random
		Random random = new Random();
		int randomIndex = -1;
		
		// get random sorcerer from target team
	    List<Sorcerer> enemies = new ArrayList<>(targetTeam.sorcerers);
	    randomIndex = random.nextInt(enemies.size());
	    Sorcerer enemy = enemies.get(randomIndex);
	    

		// get random sorcerer from our team
	    Spell spell = null;	
	    Sorcerer sorcerer = null;
	    List<Sorcerer> sorcerers = new ArrayList<>(targetTeam.sorcerers);
		while (spell == null && !sorcerers.isEmpty()) { // in case some sorcerers cannot use any spell (because forbidden or other reasons)
			randomIndex = random.nextInt(sorcerers.size());
			sorcerer = sorcerers.get(randomIndex);
			sorcerers.remove(randomIndex);			
			
			spell = this.chooseSpellRandom(sorcerer, spellsUsedInTurn, s -> s.getMagicType() == OFFENSIVE);
		}
		
		if (spell == null) {
			return;
		}
		
		spell.cast(sorcerer, enemy, sorcerer.getModifier(spell.getMagicType())); // TODO: Maybe the responsibility of calling this should be in the sorcerer  
		
		spellsUsedInTurn.add(spell);
		spellsUsedPerSorcerer.getOrDefault(sorcerer, new ArrayList<Spell>()).add(spell);
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
	
	public Spell chooseSpellRandom(Sorcerer s, Set<Spell> forbiddenSpells, Predicate<Spell> condition) {
	    List<Spell> possibleSpells = new ArrayList<>(s.getLearnedSpells());
	    
		for (Spell forbiddenSpell : forbiddenSpells) {
			possibleSpells.remove(forbiddenSpell);
		}
		
		if (condition != null) {
			possibleSpells.removeIf(condition);
		}
		
		if (possibleSpells.isEmpty()) {
			return null;
		}
		
	    Random random = new Random();
	    int randomIndex = random.nextInt(possibleSpells.size());

	    return possibleSpells.get(randomIndex);
	}
	
	public Spell chooseSpellAI(Sorcerer s, Team targetTeam, Set<Spell> forbiddenSpells, Predicate<Spell> condition) {
		Sorcerer partnerDying = this.memberDying(dyingPartnerLife);
		Sorcerer enemyDying = targetTeam.memberDying(dyingEnemyLife);
		
		List<Spell> possibleSpells = new ArrayList<>(s.getLearnedSpells());
		for (Spell forbiddenSpell : forbiddenSpells) {
			possibleSpells.remove(forbiddenSpell);
		}
		
		if (condition != null) {
			possibleSpells.removeIf(condition);
		}
		
		List<Spell> healthSpells = possibleSpells.stream().filter(spell -> spell.getMagicType() == HEALING).toList(); // TODO
		List<Spell> deffensiveSpells = possibleSpells.stream().filter(spell -> spell.getMagicType() == DEFFENSIVE).toList(); // TODO
		List<Spell> offensiveSpells = possibleSpells.stream().filter(spell -> spell.getMagicType() == OFFENSIVE).toList(); // TODO
		
		
		Random random = new Random();
		int randomIndex;
		
		randomIndex = random.nextInt(healthSpells.size());
		Spell healthSpell = healthSpells.get(randomIndex);
		randomIndex = random.nextInt(deffensiveSpells.size());
		Spell deffensiveSpell = deffensiveSpells.get(randomIndex);
		randomIndex = random.nextInt(offensiveSpells.size());
		Spell offensiveSpell = offensiveSpells.get(randomIndex);
		
		if (partnerDying != null && (healthSpell != null || deffensiveSpell != null)) {
			return healthSpell!=null ? healthSpell : deffensiveSpell;
		}
		
		if (enemyDying != null && offensiveSpell != null) {
			return offensiveSpell;
		}
		
		return this.chooseSpellRandom(s, forbiddenSpells, null);
	}
	
	public Sorcerer memberDying(int lifeLimit) {
		List<Sorcerer> members = new ArrayList<>(sorcerers);
		members.sort(new ComparatorSorcererLife());
		
		int i=0;
		Sorcerer leastLifeMember=null;
		while (leastLifeMember == null && members.get(i).getHealthPoints() < lifeLimit) {
			if (members.get(i).getHealthPoints() > 0) { // in case there are death members in list
				leastLifeMember = members.get(i);
			}
			i++;
		}
		
		return leastLifeMember;
	}
	
	public generateTeam(SorcererCreator sorcererCreator, int members) {
		for (int i=0; i<members; i++) {
			this.addSorcerer(sorcererCreator.createSorcerer());
		}
	}
	
}

