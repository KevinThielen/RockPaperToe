package seng1.rockpapertoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import seng1.rockpapertoe.Game.GameStatus;

/**
 * Created by André Tegeder
 * This Adapter helps to show custom list items in list view.
 */
public class GameStatusAdapter extends ArrayAdapter<GameStatus> {
    public GameStatusAdapter(Context context, ArrayList<GameStatus> gameStatuses) {
        super(context, 0, gameStatuses);    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        GameStatus gameStatus = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }
        //Lookup view for data population
        TextView tvOpponent = (TextView) convertView.findViewById(R.id.tvOpponent);
        TextView tvRound = (TextView) convertView.findViewById(R.id.tvRound);

        // Populate the data into the template view using the data object
        tvOpponent.setText(gameStatus.getOpponent().getName());
        tvRound.setText("Round: " +gameStatus.getRound());

        if (gameStatus.isYourTourn() == true){
            ImageView imageView = (ImageView) convertView.findViewById(R.id.list_image);
            imageView.setImageResource(R.drawable.ic_mood_black_24dp);
        }
        else {
            ImageView imageView = (ImageView) convertView.findViewById(R.id.list_image);
            imageView.setImageResource(R.drawable.ic_mood_bad_black_24dp);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
