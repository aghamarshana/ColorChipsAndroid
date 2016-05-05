package com.project.colorchips;

/**
 * Chip - Object representing each family member's security chip.
 *
 */
public class Chip {

	private final Object startColor;
	private final Object endColor;

	public Chip(Object firstColor, Object lastColor){
		this.startColor = firstColor;
		this.endColor = lastColor;
	}

	public Object startColor() {
		return startColor;
	}

	public Object endColor() {
		return endColor;
	}

	public boolean matches(Chip chip) {
		return endColor().equals(chip.startColor());
	}

	public Chip flip() {
		return new Chip(endColor, startColor);
	}

	public String toString(){
		return startColor() + "," + endColor();
	}

}
