package com.cli.rpg.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Stack;

import com.cli.rpg.character.GameCharacter;
import com.cli.rpg.character.Saviour;
import com.cli.rpg.character.Vampire;

public class BattleLevel implements Serializable {
    private final Battle battle;
    private final Stack<Saviour> savioursPlayed;
    private final Deque<Saviour> savioursRemaining;
    private int numberOfVampireActions;

    BattleLevel(Battle battle) {
        if (battle == null) {
            throw new IllegalArgumentException("Null battle!");
        }
        this.battle = battle;
        savioursPlayed = new Stack<>();
        savioursRemaining = new LinkedList<>(battle.getLivingSaviours());
        numberOfVampireActions = 0;
    }
    
    Result play() {
        if (savioursPlayed.isEmpty()) {
            System.out.println(System.lineSeparator() + "New round begins!" + System.lineSeparator() + "----------------------------------");
            calculateNumberOfSaviourTurns();
        }
        Result result = performSaviourTurns();
        if (result == Result.NORMAL) {
            result = performVampireTurns();
            return result;
        }
        return result;
    }
    
    private void calculateNumberOfSaviourTurns() {
        battle.getLivingSaviours().forEach((saviour) -> {
            Optional<Vampire> fastestVampire = battle.getLivingVampires().stream().max(Comparator.comparing(GameCharacter::getAttackSpeed));
            int numTurns;
            numTurns = fastestVampire.map(vampire -> Math.max(1, saviour.getAttackSpeed() / vampire.getAttackSpeed())).orElse(1);

            for (int i = 0; i < numTurns; ++i) {
                savioursRemaining.addLast(saviour);
            }
        });
    }

    private Result performSaviourTurns() {
        if (battle.getLivingVampires().isEmpty() || battle.getLivingSaviours().isEmpty()) {
            return Result.FINISHED;
        }
        while (!savioursRemaining.isEmpty()) {
            Saviour currentSaviour = savioursRemaining.peekFirst();
            if (battle.getLivingVampires().isEmpty()) {
                return Result.FINISHED;
            }
            System.out.println(currentSaviour.getName() + "'s turn:");
            Optional<BattleAction<Saviour>> action = currentSaviour.promptForAction();
            if (action.isPresent()) {
                battle.logAction(action.get().perform(this, currentSaviour, selectTargets(action.get(), true)));
                savioursRemaining.removeFirst();
                savioursPlayed.push(currentSaviour);
            } else {
                if (savioursPlayed.isEmpty()) {
                    return Result.UNDONE;
                } else {
                    battle.undoLastAction();
                    savioursRemaining.addFirst(savioursPlayed.pop());
                }
            }
            System.out.println();
        }

        if (battle.getLivingVampires().isEmpty()) {
            return Result.FINISHED;
        } else {
            return Result.NORMAL;
        }
    }

    private Result performVampireTurns() {
        battle.getLivingVampires().forEach((vampire) -> {
            if (battle.getLivingSaviours().isEmpty()) {
                return;
            }

            // This call should always succeed (the Vampire constructor ensures they have at least one action)
            vampire.getRandomAction().ifPresent((action) -> {
                battle.logAction(action.perform(this, vampire, selectTargets(action, false)));
                ++numberOfVampireActions;
                System.out.println();
            });
        });

        if (battle.getLivingSaviours().isEmpty()) {
            return Result.FINISHED;
        }
        return Result.NORMAL;
    }

    private Collection<GameCharacter> selectTargets(BattleAction action, boolean playerControlled) {
        Collection<GameCharacter> targets = new ArrayList<>();
        switch (action.getTargetType()) {
            case ALL:
                if (playerControlled) {
                    targets.addAll(battle.getLivingVampires());
                } else {
                    targets.addAll(battle.getLivingSaviours());
                }
            case SELF:
                return targets;
            case SINGULAR:
            case MULTIPLE:
                for (int i = 0; i < action.getTargetType().getCount(); ++i) {
                    // These calls should always succeed (each saviour/vampire turn ensures there are living saviours left)
                    if (playerControlled) {
                        battle.promptForVampire().ifPresent(targets::add);
                    } else {
                        battle.getRandomLivingSaviour().ifPresent(targets::add);
                    }
                }
            default:
                return targets;
        }
    }

    public void addTurn() {
        if (!savioursRemaining.isEmpty()) {
            savioursRemaining.addLast(savioursRemaining.peekFirst());
        }
    }

    void undo() {
        for (int i = 0; i < numberOfVampireActions; ++i) {
            battle.undoLastAction();
        }
        numberOfVampireActions = 0;
        assert !savioursPlayed.isEmpty();
        assert savioursRemaining.isEmpty();
        savioursRemaining.addFirst(savioursPlayed.pop());
    }

    enum Result {
        NORMAL, FINISHED, UNDONE
    }

}
