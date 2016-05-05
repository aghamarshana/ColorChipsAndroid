package com.project.colorchips;

import java.util.*;

/**
 * UnlockerInput handles parsing the input for the Unlocker class.
 *
 */
public class ChipsInput {

	private static final String LINE_SEPARATOR = "\n";
	private static final String COLOR_SEPARATOR = ",";
	private Object startColor;
	private Object endColor;
	List<Chip> chips = new ArrayList<Chip>();

	public void parse(String input) {
		List<Chip> parsedChips = toChips(String.valueOf(input).split(LINE_SEPARATOR));
		if(parsedChips.size() > 0){
			startColor = parsedChips.get(0).startColor();
			endColor = parsedChips.get(0).endColor();
			chips = parsedChips.subList(1, parsedChips.size());
		}
	}

	private static List<Chip> toChips(String[] lines) {
		List<Chip> chips = new ArrayList<Chip>();
		for(String line : lines){
			chips.add(parseChip(line));
		}
		return chips;
	}

	private static Chip parseChip(String line) {
		String[] parts = line.split(COLOR_SEPARATOR);
		if(parts.length != 2){
			throw new RuntimeException("Could not parse line: [" + line + "]");
		}
		return new Chip(parts[0].trim(), parts[1].trim());
	}

	public boolean isValid() {
		return firstColor() != null && endColor() != null && !chips.isEmpty();
	}

	public Object firstColor() {
		return startColor;
	}

	public Object endColor() {
		return endColor;
	}

	public Collection<Chip> chips() {
		return chips;
	}

}
