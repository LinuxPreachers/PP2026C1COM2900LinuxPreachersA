package com.classes.sorcerer;

public class Wizard extends Sorcerer 
{
	private int housePoints;

	public Wizard(String name, int magicLevel, int healthPoints, int housePoints) 
	{
		super(name, magicLevel, healthPoints);
		this.healthPoints = housePoints;		
	}
	
	public int getHousePoints() {
        return this.housePoints;
    }
}
