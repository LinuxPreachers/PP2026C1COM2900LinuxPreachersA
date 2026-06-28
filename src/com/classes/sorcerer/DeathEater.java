package com.classes.sorcerer;

public class DeathEater extends Sorcerer 
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
