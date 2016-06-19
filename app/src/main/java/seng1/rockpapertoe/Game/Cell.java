package seng1.rockpapertoe.Game;

/**
 * Created by kevin on 03.05.16.
 */
public class Cell {
    boolean owner;
    ECell value;

    public Cell() {
        owner = false;
        value = ECell.EMPTY;
    }

    public Cell(boolean owned, ECell value) {
        owner = false;
        this.value = value;
    }

    public void setValue(ECell value) {
        this.value = value;
    }

    public ECell getValue() {
        return value;
    }

    public void setOwner(boolean owned) {
        owner = owned;
    }
    public boolean getOwner() {
        return owner;
    }
}
