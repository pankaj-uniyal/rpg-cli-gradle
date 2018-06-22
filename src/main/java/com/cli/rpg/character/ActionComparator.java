package com.cli.rpg.character;

import java.io.Serializable;
import java.util.Comparator;

import com.cli.rpg.base.BattleAction;

public final class ActionComparator implements Serializable, Comparator<BattleAction> {
    @Override
    public int compare(BattleAction o1, BattleAction o2) {
        return o1.toString().compareTo(o2.toString());
    }
}