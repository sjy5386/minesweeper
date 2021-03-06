package com.sysbot32.minesweeper.game;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Cell {
    private boolean isOpened = false;

    private boolean hasMine = false;
    private int nearbyMines = 0;

    private boolean isFlagged = false;
    private boolean hasQuestionMark = false;

    public Cell(boolean hasMine) {
        this(hasMine, 0);
    }

    public Cell(int nearbyMines) {
        this(false, nearbyMines);
    }

    public Cell(boolean hasMine, int nearbyMines) {
        this.hasMine = hasMine;
        this.nearbyMines = nearbyMines;
    }

    public void open() {
        if (!isFlagged && !hasQuestionMark) {
            isOpened = true;
        }
    }

    public void flag() {
        if (isOpened) {
            return;
        }

        if (!isFlagged && !hasQuestionMark) {
            isFlagged = true;
        } else if (isFlagged && !hasQuestionMark) {
            isFlagged = false;
            hasQuestionMark = true;
        } else {
            hasQuestionMark = false;
        }
    }

    @Override
    public String toString() {
        if (isOpened) {
            if (hasMine) {
                return "\033[0;35m[*]\u001B[0m";
            } else if (nearbyMines > 0) {
                return "[\u001B[36m" + nearbyMines + "\u001B[0m]";
            } else {
                return "[ ]";
            }
        } else {
            if (isFlagged) {
                return "\u001B[33m[>]\u001B[0m";
            } else if (hasQuestionMark) {
                return "\u001B[32m[?]\u001B[0m";
            } else {
                return "\u001B[34m[+]\u001B[0m";
            }
        }
    }
}
