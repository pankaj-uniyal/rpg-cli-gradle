package com.cli.rpg.character;

import java.io.Serializable;

public interface Effect extends Serializable {
    void undo();
}
