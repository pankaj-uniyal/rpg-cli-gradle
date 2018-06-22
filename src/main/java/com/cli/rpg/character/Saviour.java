package com.cli.rpg.character;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import com.cli.rpg.base.BattleAction;
import com.cli.rpg.base.ProbabilityCheck;
import com.cli.rpg.base.Range;
import com.cli.rpg.common.util.Utils;



public final class Saviour extends GameCharacter implements Serializable {
	   private final ProbabilityCheck blockCheck;
	   private final Set<BattleAction<Saviour>> actions;
	    
	   public Saviour(String name, int hitPoints, int attackSpeed, double chanceToHit, Range damageRange,
	            String basicAttackDescription, double chanceToBlock, Set<BattleAction<Saviour>> actions) {
	           super(name, hitPoints, attackSpeed, chanceToHit, damageRange, basicAttackDescription);
	           if (actions == null || actions.isEmpty()) {
	               throw new IllegalArgumentException("Invalid Player properties.");
	           }
	           this.blockCheck = new ProbabilityCheck(chanceToBlock);
	           this.actions = actions;
	       }
	   
	   public Optional<BattleAction<Saviour>> promptForAction() {
	        System.out.println("Please choose an action:");
	        return Utils.promptForElementWithAlternative(actions, "Undo Last Action");
	    }

	    @Override
	    public final DamageEffect subtractHitPoints(int points) {
	        if (blockCheck.pass()) {
	            System.out.println(getName() + " BLOCKED the attack!");
	            return null;
	        } else {
	            return super.subtractHitPoints(points);
	        }
	    }
}
