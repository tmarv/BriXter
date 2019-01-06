package brix.geektimch.brixter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TopScores extends Activity {

    private int[] theNames={R.id.first,R.id.second,R.id.third,R.id.fourth,R.id.fifth,R.id.sixth,
            R.id.seventh,R.id.eighth,R.id.ninth,R.id.tenth};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);

        Intent intent=this.getIntent();
        int[] daTable=intent.getIntArrayExtra("daScores");
        System.out.println("da scores "+daTable.length);

        for(int i =0;i<10;i++){
            TextView localTV=(TextView)findViewById(theNames[i]);
            String str=Integer.toString(daTable[i]);
            localTV.setText(str);
        }


    }
}
