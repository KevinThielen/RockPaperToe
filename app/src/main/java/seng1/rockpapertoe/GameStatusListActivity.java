package seng1.rockpapertoe;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import seng1.rockpapertoe.Game.GameStatus;
import seng1.rockpapertoe.Game.GameStatusMockup;
import seng1.rockpapertoe.Remote.RockPaperToeServerStub;
import seng1.rockpapertoe.Remote.Session;

/**
 * Created by André Tegeder
 */
public class GameStatusListActivity extends AppCompatActivity {
    //List of  GamesStatus
    private ArrayList<GameStatus> games;
    //Mockup TestData
    private RockPaperToeServerStub gms;
    //Mockup Arraylist
    private ArrayList<GameStatus> gmsList;
    // ListView witch shows Games ans Status
    private ListView listView;
    //Start Button object
    private Button startButton;
    // ImageButton object
    private ImageButton refreshButton;

    Session session;
    /**
     * Loads content and design on cerateing the activity
     * @author Andre Tegeder
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        session = new Session(getApplicationContext());
        setContentView(R.layout.activity_gamestatus);
        // GameStatus object for client
        gms =  new RockPaperToeServerStub();
        //Async-Task execution
        new GameStatusDataLoadingTask().execute();
        // ListView of the activity find by her id
        listView = (ListView) findViewById(R.id.listViewGameStatus);
        // Button of the activity find by his id
        startButton = (Button) findViewById(R.id.buttonStartGame);
        //Button of the activity find by his id
        refreshButton = (ImageButton) findViewById(R.id.buttonRefresh);
    }

    /**
     * This Method add the Gamestatus adapter to show custom listitem in listview
     * @author Andre Tegeder
     */
    void setListAdapter(){
        GameStatusAdapter gameStatusAdapter = new GameStatusAdapter(this, games);

        listView.setAdapter(gameStatusAdapter);

        Log.d("ListAdapter", "Number of games in Adapter: "+games.size());
      //  gameStatusAdapter.addAll(games);
      //  gameStatusAdapter.clear();
    }

    /**
     * Add a clicklistener to the listview of gamestatus
     * @author Andre Tegeder
     */
    void setClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

                    int index = games.get(position).getGameId();
                    Log.d("Click on", "Game Id selected"+index);
                    //TODO ID weitergeben im in das Spiel zugelangen
                    String strI = String.valueOf(index);
                    Toast.makeText(getBaseContext(), strI, Toast.LENGTH_LONG).show();
                }
            }
        );
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshList();
            }
        });
    }

    /**
     * Refresh the list of games form Server
     * author Andre Tegeder
     */
    void refreshList(){
        new GameStatusDataLoadingTask().execute();
    }
    /**
     * Add a new game to the list
     * author Andre Tegeder
     */
    void newGame(){
        new GameStatusNewGameTask().execute();
    }

    /**
     * Inner Class for Asyn-Task
     * onPreExecute displays a loading Element to ensure that the app is loading data
     * doInBackground is loading Data from the Database (Mockup) and returns an ArrayList with gamestatus objects
     * onPostExecute hides the loading Element
     * @author Andre Tegeder
     */
    class GameStatusDataLoadingTask extends AsyncTask<String, Void, ArrayList> {

        private ProgressDialog pd = new ProgressDialog(GameStatusListActivity.this);

            @Override
            protected void onPreExecute(){
                this.pd.setMessage("Loading Games");
                this.pd.setIndeterminate(false);
                this.pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                this.pd.setCancelable(true);
                this.pd.show();
            }

            protected ArrayList<GameStatus> doInBackground(String... params){
                ArrayList<GameStatus> result = gms.getGames(session.getSessionId());
                games = new ArrayList<>();
                games = result;

                return result;
            }

            protected void onPostExecute(ArrayList gms){
                this.pd.dismiss();
                setListAdapter();
                setClickListener();
            }
        }

    /**
     * Inner Class for Asyn-Task
     * @author Andre Tegeder, Kevin Thielen
     */
    class GameStatusNewGameTask extends AsyncTask<String, Void, ArrayList> {

        private ProgressDialog pd = new ProgressDialog(GameStatusListActivity.this);

        @Override
        protected void onPreExecute(){
            this.pd.setMessage("Loading Games");
            this.pd.setIndeterminate(false);
            this.pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.pd.setCancelable(true);
            this.pd.show();
        }

        protected ArrayList<GameStatus> doInBackground(String... params){
            ArrayList<GameStatus> result = gms.findNewGame(session.getSessionId());

            games = new ArrayList<>();
            games = result;
            Log.d("GameList", "Found "+result.size()+" games");
            return result;
        }

        protected void onPostExecute(ArrayList gms){
            this.pd.dismiss();
            setListAdapter();
            setClickListener();
        }
    }
}
