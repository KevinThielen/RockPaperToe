package seng1.rockpapertoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import seng1.rockpapertoe.Game.*;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {
    //remote server, currently a stub
    Game gameServer;

    //currentPlayer, will be changed to owner of activity when server goes live
    Player player;

    //buttons for player actions. Range [0,0],[3,3]
    Button buttons[][];

    //playes name as TextView
    TextView playerView;
    //Opponents name as Textview
    TextView opponentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameServer = new Game();

        playerView = (TextView) findViewById(R.id.playerView);
        opponentView = (TextView) findViewById(R.id.opponentsView);

        setButtons();
        setInputListener();
        updateBoard();
    }

    /**
     * Get all cell of the board.
     * Get a handle for each of the 9 button that represents the cells on the board
     * und put them int an 2D-array.
     *
     * @author Kevin Thielen
     */
    void setButtons() {
        buttons = new Button[3][3];
        buttons[0][0] = (Button) findViewById(R.id.button00);
        buttons[0][1] = (Button) findViewById(R.id.button01);
        buttons[0][2] = (Button) findViewById(R.id.button02);
        buttons[1][0] = (Button) findViewById(R.id.button10);
        buttons[1][1] = (Button) findViewById(R.id.button11);
        buttons[1][2] = (Button) findViewById(R.id.button12);
        buttons[2][0] = (Button) findViewById(R.id.button20);
        buttons[2][1] = (Button) findViewById(R.id.button21);
        buttons[2][2] = (Button) findViewById(R.id.button22);
    }

    /**
     * Set input listener for the Cells
     * Iterates the 2D-cell array and sets
     * the onClickListener for button presses
     *
     * @author Kevin Thielen
     */
    void setInputListener() {
        for(int x = 0; x<3; x++) {
            for(int y = 0; y<3; y++) {
                buttons[x][y].setOnClickListener(buttonListener);
            }

        }
    }

    /**
     * Translates the button press into a move
     * Takes the button x and y position to translate the button press
     * into a player action. The action will be sent to the server
     * and the board will copy the new board state from it.
     *
     * @author Kevin Thielen
     */
    View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick(View v) {
            for(int x = 0; x<3; x++) {
                for (int y = 0; y < 3; y++) {
                    if(buttons[x][y].getId() == v.getId()) {
                        gameServer.makeMove(player, x, y);
                        break;
                    }
                }
            }
            updateBoard();
        }
    };


    /**
     * Updates the board state
     * Sets the value for each button, depending on the board state.
     * The backgroundcolor will be set to color of the owning player
     * of that cell. If the cell has no ownership at that time, the color
     * will be set to gray. The text represents the ECell value.
     *
     * @author Kevin Thielen
     */
    void updateBoard() {
        Cell board[][] = gameServer.getBoard();
        for(int x = 0; x<3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y].setText(cellToText(board[x][y].getValue()));
                buttons[x][y].setBackgroundColor(cellToColor(board[x][y].getOwner()));
                buttons[x][y].invalidate();
            }
        }

        player = gameServer.getCurrentPlayer();
    }

    //TEMPORARY HELPER
    String cellToText(ECell value) {
        switch (value) {
            case ROCK:
                return "Rock";
            case PAPER:
                return "Paper";
            case SCISSOR:
                return "Scissor";
        }

        return "EMPTY";
    }

    int cellToColor(Player owner) {

        if (owner == null) {
            return 0x44444444;
        }

        if (owner == gameServer.getPlayer(0)) {
            return playerView.getCurrentTextColor();
        }
        else
            return opponentView.getCurrentTextColor();
    }

}
