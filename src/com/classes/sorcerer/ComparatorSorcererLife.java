package com.classes.sorcerer;

import java.util.Comparator;

public class ComparatorSorcererLife implements Comparator<Sorcerer> {
	@Override
	public int compare(Sorcerer s1, Sorcerer s2) {
		return s1.getHealthPoints() - s2.getHealthPoints();
	}
}
