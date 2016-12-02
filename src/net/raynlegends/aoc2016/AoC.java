package net.raynlegends.aoc2016;

import lombok.Getter;
import net.raynlegends.aoc2016.managers.GuiManager;
import net.raynlegends.aoc2016.managers.PuzzleManager;

@Getter
public class AoC {

	@Getter
	private static AoC instance;
	
	private GuiManager guiManager;
	private PuzzleManager puzzleManager;
	
	protected AoC() {
		instance = this;
		
		puzzleManager = new PuzzleManager();
		guiManager = new GuiManager();
	}
	
}
