package com.classes.character;

public class DeathEater extends Character 
{
	private int darkMarkIntensity;
	
	public DeathEater(String name, int magicLevel, int healthPoints, int darkMarkIntensity)
	{
		super(name, magicLevel, healthPoints);
		this.darkMarkIntensity = darkMarkIntensity;
	}
	
	public int getDarkMarkIntensity() {
        return this.darkMarkIntensity;
    }
}
