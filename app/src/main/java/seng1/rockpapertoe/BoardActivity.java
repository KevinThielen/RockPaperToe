package seng1.rockpapertoe;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

public class BoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
    }

    public void getTop3(View v){
        TextView output = (TextView) findViewById(R.id.editText);

        HighscoreMockupTest hmt = new HighscoreMockupTest();

        for (HighscoreMockup hm: hmt.getTop3()) {
            output.setText(hm.toString() + System.getProperty("line.separator"));
            Log.d("Datensatz:", hm.toString());
        }

    }
}
