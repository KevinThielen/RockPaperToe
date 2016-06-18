package seng1.rockpapertoe;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import seng1.rockpapertoe.Game.Game;
import seng1.rockpapertoe.Game.GameStatus;
import seng1.rockpapertoe.Game.GameStatusMockup;

/**
 * Created by André Tegeder
 */
public class GameStatusListActivity extends AppCompatActivity {
    //List of  GamesStatus
    private ArrayList<GameStatus> games;
    //Mockup TestData
    private GameStatusMockup gms;
    //Mockup Arraylist
    private ArrayList<GameStatus> gmsList;
    // ListView witch shows Games ans Status
    private ListView listView;

    /**
     * Loads content and design on cerateing the activity
     * @author Andre Tegeder
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestatus);
        // GameStatus object for client
        gms =  new GameStatusMockup();
        //Async-Task execution
        new GameStatusDataLoadingTask().execute();
        // ListView of the activity find by her id
        listView = (ListView) findViewById(R.id.listViewGameStatus);
        //TODO Mochup austauschen
        gmsList = gms.getGameStatuses();
        games = gmsList;


       }

    /**
     * This Method add the Gamestatus adapter to show custom listitem in listview
     * @author Andre Tegeder
     */
    void setListAdapter(){
        GameStatusAdapter gameStatusAdapter = new GameStatusAdapter(this, games);
        listView.setAdapter(gameStatusAdapter);
        gameStatusAdapter.addAll(games);
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
                    //TODO ID weitergeben im in das Spiel zugelangen
                    String strI = String.valueOf(index);
                    Toast.makeText(getBaseContext(), strI, Toast.LENGTH_LONG).show();
                }
            }
        );
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

                try {
                    Thread.sleep(5000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                return gms.getGameStatuses();
            }

            protected void onPostExecute(ArrayList gms){
                this.pd.dismiss();
                setListAdapter();
                setClickListener();
            }
        }
}
