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
 * Created by andre on 15.06.2016.
 */
public class GameStatusListActivity extends AppCompatActivity {
    //lIst if Games and there Status
    private ArrayList<GameStatus> games;
    //Mockup TestData
    private GameStatusMockup gms;
    //Mockup Arraylist
    private ArrayList<GameStatus> gmsList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestatus);
        // GameStatus object for client
        gms =  new GameStatusMockup();
        //Async-Task execution
        new GameStatusDataLoadingTask().execute();
        //TODO Mochup austauschen
        gmsList = gms.getGameStatuses();
        games = gmsList;
         ArrayList<GameStatus> gameStatusArrayList = new ArrayList<GameStatus>();
        GameStatusAdapter gameStatusAdapter = new GameStatusAdapter(this, gameStatusArrayList);
        final ListView listView = (ListView) findViewById(R.id.listViewGameStatus);
        listView.setAdapter(gameStatusAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * This Method show the GameId from clicked item in the list
             * @param position Position of item in luist
             */
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

                   int index = games.get(position).getGameId();
                    //TODO ID weitergeben im in das Spiel zugelangen
                    String strI = String.valueOf(index);
                    Toast.makeText(getBaseContext(), strI, Toast.LENGTH_LONG).show();
                }
            }
        );
        gameStatusAdapter.addAll(games);
       }


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
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            return gms.getGameStatuses();
        }

        protected void onPostExecute(ArrayList gms){
            this.pd.dismiss();

        }
    }
}
