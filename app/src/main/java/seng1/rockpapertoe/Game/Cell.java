package seng1.rockpapertoe.Game;

/**
 * Created by kevin on 03.05.16.
 */
public class Cell {
    Player owner;
    ECell value;

    public Cell() {
        owner = null;
        value = ECell.EMPTY;
    }

    public void setValue(ECell value) {
        this.value = value;
    }

    public ECell getValue() {
        return value;
    }

    public void setOwner(Player player) {
        owner = player;
    }
    public Player getOwner() {
        return owner;
    }
}
