package com.classes.spell;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class SpellRepository {

	public static final Set<Spell> SPELLS = new HashSet<>();
	
	static {
		SPELLS.add(new AvadaKedavraSpell());
		SPELLS.add(new BombardaSpell());
		SPELLS.add(new EpiskeySpell());
		SPELLS.add(new ExpelliarmusSpell());
		SPELLS.add(new IncendioSpell());
		SPELLS.add(new PetrificusTotalusSpell());
		SPELLS.add(new ProtegoSpell());
		SPELLS.add(new SectumsempraSpell());
		SPELLS.add(new VulneraSanenturSpell());
	}
	
	public static Set<Spell> getAll() {
		return Collections.unmodifiableSet(SPELLS);
	}
	
	public static Spell getByName(String name) {
		
		Spell spell = null;
		
		for (Iterator<Spell> it = SPELLS.iterator(); spell == null && it.hasNext();) {
			spell = it.next();
			
			if (spell.getName() != name)
				spell = null;
		}
		
		return spell;
	}
}
