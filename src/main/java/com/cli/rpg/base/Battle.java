package com.cli.rpg.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import com.cli.rpg.character.ActionLog;
import com.cli.rpg.character.Effect;
import com.cli.rpg.character.GameCharacter;
import com.cli.rpg.character.Saviour;
import com.cli.rpg.character.Vampire;
import com.cli.rpg.common.util.Utils;

class Battle implements Serializable{
	static final String SAVE_FILE_NAME = '.' + File.separator + "persistGame.ser";
	
	private final ActionLog actionLog;
	private final int vampireCount;
	private final int saviourCount;
	private final List<Vampire> vampireList;
	private final List<Saviour> saviourList;
	private final Stack<BattleLevel> levelsFinished;
	
	Battle(int saviourCount, int vampireCount){
		actionLog = new ActionLog();
		this.vampireCount = vampireCount;
		this.saviourCount = saviourCount;
		vampireList = new ArrayList<>(vampireCount);
		saviourList = new ArrayList<>(saviourCount);
		this.levelsFinished = new Stack<>();
	}
	
	void init(Executor executor) {
		actionLog.clear();
		levelsFinished.clear();
		saviourList.clear();
        vampireList.clear();
        System.out.println("Creating battle with " + saviourCount + ((saviourCount == 1) ? " saviour" : " saviours") + " against " + vampireCount + " vampires.");
        for (int i = 0; i < saviourCount; ++i) {
        	saviourList.add(executor.chooseSaviour());
            System.out.println();
        }
        for (int i = 0; i < vampireCount; ++i) {
            vampireList.add(executor.createRandomVampire());
        }
    }

    void begin() {
        if (levelsFinished.isEmpty()) {
            System.out.println("A new battle begins!");
        }

        BattleLevel nextBattleLevel = new BattleLevel(this);
        do {
            BattleLevel round = nextBattleLevel;
            nextBattleLevel = determineNextBattleLevel(round.play(), round);
        } while (nextBattleLevel != null);

        if (getLivingSaviours().isEmpty()) {
            System.out.println("The saviours were defeated :-(");
        } else if (getLivingVampires().isEmpty()) {
            System.out.println("The saviours were victorious!");
        } else {
            System.out.println("Quitters never win ;-)");
        }
    }

    private BattleLevel determineNextBattleLevel(BattleLevel.Result result, BattleLevel old) {
        char input = ' ';
        switch (result) {
            case UNDONE:
                if (!levelsFinished.isEmpty()) {
                    BattleLevel previous = levelsFinished.pop();
                    System.out.println(System.lineSeparator() + "Returning to previous round..." + System.lineSeparator() + "----------------------------------");
                    previous.undo();
                    return previous;
                } else {
                    undoLastAction(); // Ensures all actions are undone when playing a game from a save file.
                    System.out.println(System.lineSeparator() + "You are at the start of the battle!");
                    return new BattleLevel(this);
                }
            case NORMAL:
                levelsFinished.push(old);
                do {
                    System.out.print("Enter 'Q' to quit, 'S' to save, or any other character to continue: ");
                    input = Utils.readChar();
                    if (input == 's' || input == 'S') {
                        saveGame();
                    }
                } while (input == 's' || input == 'S');
            default:
                if (input == 'q' || input == 'Q') {
                    return null;
                }
        }
        if (result == BattleLevel.Result.NORMAL) {
            return new BattleLevel(this);
        }
        return null;
    }

    List<Saviour> getLivingSaviours() {
        return getLiving(saviourList);
    }

    List<Vampire> getLivingVampires() {
        return getLiving(vampireList);
    }

   void undoLastAction() {
        actionLog.undoLastAction();
    }

    private void saveGame() {
        try (FileOutputStream oFile = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(oFile)) {
            out.writeObject(this);
            System.out.println("Saved data to " + SAVE_FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving game.");
            e.printStackTrace();
        }
    }

    private static <E extends GameCharacter> List<E> getLiving(List<E> list) {
        List<E> living = new ArrayList<>();
        list.forEach((character) -> {
            if (character.isAlive())
                living.add(character);
        });
        return living;
    }

    void displayBattleStatus() {
        System.out.println("BattleLevels finished: " + levelsFinished.size() +
                System.lineSeparator() + "Saviours remaining: " + getLivingSaviours().size() +
                System.lineSeparator() + "Vampires remaining: " + getLivingVampires().size());
    }

    void logAction(Collection<Effect> effects) {
        actionLog.logAction(effects);
    }

    Optional<Saviour> getRandomLivingSaviour() {
        return Utils.getRandomElement(getLivingSaviours());
    }

    Optional<Vampire> promptForVampire() {
        System.out.println("Please choose a vampire:");
        return Utils.promptForElement(getLivingVampires());
    }
}