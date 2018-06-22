package com.cli.rpg.character;

import java.io.Serializable;
import java.util.Collection;
import java.util.Stack;

public final class ActionLog implements Serializable {
    private final Stack<LogEntry> log = new Stack<>();

    public void logAction(Collection<Effect> effects) {
        log.add(new LogEntry(effects));
    }

    public void undoLastAction() {
        if (!log.isEmpty()) {
            log.pop().undo();
        }
    }

    public void clear() {
        log.clear();
    }

    private static final class LogEntry implements Serializable {
        private final Collection<Effect> effects;

        LogEntry(Collection<Effect> effects) {
            this.effects = effects;
        }

        void undo() {
            effects.forEach((effect) -> {
                if (effect != null) {
                    effect.undo();
                }
            });
        }
    }
}
