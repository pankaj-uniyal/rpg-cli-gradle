package com.cli.rpg.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


import com.cli.rpg.character.Saviour;
import com.cli.rpg.character.Vampire;
import com.cli.rpg.common.util.Utils;
import com.cli.rpg.factories.SaviourFactory;
import com.cli.rpg.factories.SaviourFactory.SaviourClass;

import com.cli.rpg.factories.VampireFactory;

public class Executor {
	
	private static final int DEFAULT_SAVIOUR_COUNT = 3, DEFAULT_VAMPIRE_COUNT = 3;
	private final SaviourFactory saviourFactory;
	private final VampireFactory vampireFactory;
	
	private Executor(){
		this.saviourFactory = new SaviourFactory();
		this.vampireFactory = new VampireFactory();
	}

	public static void main(String[] args) {
        Executor executor = new Executor();
        Battle battle;
        if (promptYes("Load existing save?")) {
            battle = executor.loadGame();
        } else {
            battle = new Battle(promptForSaviourCount(), DEFAULT_VAMPIRE_COUNT);
            battle.init(executor);
        }
        battle.begin();
        while (promptYes("Play again?")) {
            battle = new Battle(promptForSaviourCount(), DEFAULT_VAMPIRE_COUNT);
            battle.init(executor);
            battle.begin();
        }
    }

    private static boolean promptYes(String message) {
        System.out.println(message + " (Y/N)");
        char again = Utils.readChar();

        return (again == 'Y' || again == 'y');
    }

    private Battle loadGame() {
        Battle battle;
        try (FileInputStream iFile = new FileInputStream(Battle.SAVE_FILE_NAME);
             ObjectInputStream in = new ObjectInputStream(iFile)) {
            battle = (Battle) in.readObject();
            battle.displayBattleStatus();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading save; creating new game.");
            int saviourCount = promptForSaviourCount();
            battle = new Battle(saviourCount, DEFAULT_VAMPIRE_COUNT);
            battle.init(this);
        }
        return battle;
    }

    private static int promptForSaviourCount() {
        System.out.println("Please enter a number of heroes (1+):");
        int heroCount = Utils.readInt();
        if (heroCount < 1) {
            heroCount = DEFAULT_SAVIOUR_COUNT;
            System.out.println("Invalid number of heroes. Using default (" + DEFAULT_SAVIOUR_COUNT + ").");
        }
        return heroCount;
    }
	Saviour chooseSaviour() {
        System.out.println("Choose a hero:");
        for (int c = 0; c < SaviourClass.values().length; ++c) {
            System.out.println((c + 1) + ". " + Utils.toCamelCase(SaviourClass.values()[c].name()));
        }
        int choice = Utils.readInt();
        System.out.print("Enter character name: ");
        String name = Utils.readString();

        if (choice > 0 && choice < SaviourClass.values().length + 1) {
            return saviourFactory.createSaviour(SaviourClass.values()[choice - 1], name);
        } else {
            System.out.println("Invalid choice, returning default character.");
            return saviourFactory.createSaviour(SaviourClass.WARRIOR, name);
        }
    }
	
	Vampire createRandomVampire() {
        return vampireFactory.createRandomMonster();
    }


	
}
