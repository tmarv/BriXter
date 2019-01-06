package brix.geektimch.brixter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private int screenH;
    private int[] topScTab;
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedpreferences = getSharedPreferences("topScorePref", Context.MODE_PRIVATE);
        String topSStr=sharedpreferences.getString("topS","0");
        topScTab=treatSharedPref("123,4643,2423");
        Arrays.sort(topScTab);

        getSupportActionBar().hide();
        // Load an ad into the AdMob banner view.
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        //adRequest.Builder.addTestDevice("9FE088C7248BEFDC3E495AE71A84B991");

        adView.loadAd(adRequest);

        Button playB= (Button)findViewById(R.id.Game);
        playB.setOnClickListener(NewGame);

        Button qAndA= (Button)findViewById(R.id.qAndA);
        qAndA.setOnClickListener(qAndAC);

        Button topScores= (Button)findViewById(R.id.topScores);
        topScores.setOnClickListener(topSc);



        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }
    private View.OnClickListener NewGame = new View.OnClickListener(){
        public void onClick(View view) {
            Intent playIntent= new Intent(view.getContext(),TheGameActivity.class);
            startActivity(playIntent);
        }
    };

    private View.OnClickListener topSc = new View.OnClickListener(){
        public void onClick(View view) {
            Intent topScIntent= new Intent(view.getContext(),TopScores.class);
            topScIntent.putExtra("daScores", topScTab);
            startActivity(topScIntent);
        }
    };

    private View.OnClickListener qAndAC = new View.OnClickListener(){
        public void onClick(View view) {
            Intent playIntent= new Intent(view.getContext(),HowToPlay.class);
            startActivity(playIntent);
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int[] treatSharedPref(String str){
        int[] toBeRet=new int[10];
        if(str.length()>1) {
            for (int i = 0; i < 10; i++) {
                 int k=str.indexOf(',');
                if(k>0){
                    String subs=str.substring(0,k);
                    toBeRet[i]=Integer.parseInt(subs);
                    //System.out.println("hello "+subs);
                    if(str.length()>subs.length()) {
                        str = str.substring(k + 1);
                    }

                }
                else{
                    if(str.length()>0) {
                        toBeRet[i]=Integer.parseInt(str);
                        //System.out.println("hello43 " + str);

                    }
                }
                if(str.length()==0){
                    toBeRet[i]=0;
                }
            }
        }

        return toBeRet;
    }
}
