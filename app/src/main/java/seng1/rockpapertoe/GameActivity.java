package seng1.rockpapertoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import seng1.rockpapertoe.Game.*;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {
    //remote server
    Game gameServer;
    Player player;
    Button buttons[][];

    TextView playerView;
    TextView opponentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameServer = new Game();
        //TODO: current player hack till player login

        player = gameServer.getCurrentPlayer();

        playerView = (TextView) findViewById(R.id.playerView);
        opponentView = (TextView) findViewById(R.id.opponentsView);

        setButtons();
        setInputListener();
        updateBoard();
    }

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

    void setInputListener() {
        for(int x = 0; x<3; x++) {
            for(int y = 0; y<3; y++) {
                buttons[x][y].setOnClickListener(buttonListener);
            }

        }
    }

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
