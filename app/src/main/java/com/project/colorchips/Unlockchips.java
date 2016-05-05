package com.project.colorchips;

import java.util.ArrayList;
import java.util.List;

/*********************************************************************************
 * search algorithm to determine if the given input can unlock the master panel.
**********************************************************************************/
public class Unlockchips {

	public static final String UNLOCK_FAILED_MSG = "Cannot unlock master panel";
	private String result = UNLOCK_FAILED_MSG;
	private List<Chip> orderedResults = new ArrayList<Chip>();

	public void unlock(ChipsInput input) {
		orderedResults = new ArrayList<Chip>();

		if(input.isValid()) {
			unlockValidInput(input);
		} else {
			fail();
		}
	}

	private void unlockValidInput(ChipsInput input){
		//Place-holder chips to mark start and end of the pattern
		Chip startChip = new Chip("START", input.firstColor());
		Chip endChip = new Chip(input.endColor(), "END");

		List<Chip> toOrder = new ArrayList<Chip>(input.chips());
		toOrder.add(endChip);

		findPatternRec(startChip, toOrder);

		boolean foundPattern = isLast(endChip, orderedResults);

		if(foundPattern){
			orderedResults.remove(endChip);
			storeResults(orderedResults);
		} else {
			fail();
		}

	}

	private boolean fail() {
		result = UNLOCK_FAILED_MSG;
		return false;
	}

	private void findPatternRec(Chip startChip, List<Chip> toOrder) {
		//Copy to working list so we can modify the passed in list
		List<Chip> workingList = new ArrayList<Chip>(toOrder);
		for(Chip chip : workingList){
			toOrder.remove(chip);

			if(completesPath(startChip, chip, toOrder)){
				return;
			} else if(completesPath(startChip, chip.flip(), toOrder)){
				return;
			}

			toOrder.add(chip);
		}
	}

	private boolean completesPath(Chip prevChip, Chip nextChip, List<Chip> remainingChips){
		return prevChip.matches(nextChip) && completesPath(nextChip, remainingChips);
	}

	private boolean completesPath(Chip nextChip, List<Chip> remainingChips){
		orderedResults.add(nextChip);
		findPatternRec(nextChip, remainingChips);
		if(!remainingChips.isEmpty()){
			orderedResults.remove(nextChip);
		}

		return remainingChips.isEmpty();
	}

	private boolean isLast(Chip val, List<Chip> list) {
		if(list.isEmpty()) return false;

		return val.equals(list.get(list.size() - 1));
	}

	private void storeResults(List<Chip> chips) {
		StringBuilder sb = new StringBuilder();
		for(Chip chip : chips){
			sb.append(chip.toString());
			sb.append("\n");
		}

		result =  sb.toString().trim();
	}

	public Object getResult() {
		return result;
	}

}
