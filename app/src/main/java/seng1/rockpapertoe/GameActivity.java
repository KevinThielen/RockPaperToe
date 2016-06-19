package seng1.rockpapertoe;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import seng1.rockpapertoe.Game.*;
import seng1.rockpapertoe.Remote.RockPaperToeServerStub;
import seng1.rockpapertoe.Remote.Session;

/**
 * This Class extends the Game
 * @author André Tegeder
 * @author Kevin Thielen
 */

public class GameActivity extends AppCompatActivity {
    //remote server, currently a stub
    RockPaperToeServerStub stub;
    Session session;

    //buttons for player actions. Range [0,0],[3,3]
    Button buttons[][];

    //player´s name as TextView
    TextView playerView;
    //Opponent´s name as Textview
    TextView opponentView;
    int gameId;
    GameState gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameId = extras.getInt("gameId");
        }

        setContentView(R.layout.activity_game);

        stub = new RockPaperToeServerStub();
        session = new Session(getApplicationContext());

        playerView = (TextView) findViewById(R.id.playerView);
        opponentView = (TextView) findViewById(R.id.opponentsView);

        setButtons();
        setInputListener();
        new GameStateTask().execute();
    }

    /**
     * Get all cell of the board.
     * Get a handle for each of the 9 button that represents the cells on the board
     * und put them int an 2D-array.
     *
     * @author Kevin Thielen
     * @author Andre Tegeder
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
     * Set the player names to the views to
     * see witch Player are playing in this game
     * @author Andre Tegeder
     */
    void setPlayerNames(){
        opponentView.setText(gameState.getOpponent());

        playerView.setText(session.getUserName());
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
                        //gameServer.makeMove(player, x, y);
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
     * @author André Tegeder
     */
    void updateBoard() {
        Cell board[][] = gameState.getBoard();
        for(int x = 0; x<3; x++) {
            for (int y = 0; y < 3; y++) {
                // chosse the right image to set is a source
                switch (board[x][y].getValue()){
                    case ROCK:
                        //choose wether the player is the owner or not. Owner gets blue Images. The opponent red
                        if (board[x][y].getOwner()){
                            buttons[x][y].setBackgroundResource(R.drawable.ic_rock_blue);
                        }
                        else {
                            buttons[x][y].setBackgroundResource(R.drawable.ic_rock_red);
                        }
                        break;
                    case SCISSOR:
                        //choose wether the player is the owner or not. Owner gets blue Images. The opponent red
                        if (board[x][y].getOwner()){
                            buttons[x][y].setBackgroundResource(R.drawable.ic_scissor_blue);
                        }
                        else {
                            buttons[x][y].setBackgroundResource(R.drawable.ic_scissor_red);
                        }
                        break;
                    case PAPER:
                        //choose wether the player is the owner or not. Owner gets blue Images. The opponent red
                        if (board[x][y].getOwner()){
                            buttons[x][y].setBackgroundResource(R.drawable.ic_paper_blue);
                        }
                        else {
                            buttons[x][y].setBackgroundResource(R.drawable.ic_paper_red);
                        }
                        break;
                }
                buttons[x][y].invalidate();
            }
        }
    }


    class GameStateTask extends AsyncTask<String, Void, GameState> {

        private ProgressDialog pd = new ProgressDialog(GameActivity.this);

        @Override
        protected void onPreExecute(){
            this.pd.setMessage("Loading GameState");
            this.pd.setIndeterminate(false);
            this.pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.pd.setCancelable(false);
            this.pd.show();
        }

        protected GameState doInBackground(String... params){
            GameState result = stub.getGameState(gameId);
            return result;
        }

        protected void onPostExecute(ArrayList gms){
            this.pd.dismiss();
            setPlayerNames();
            updateBoard();
        }
    }
}
