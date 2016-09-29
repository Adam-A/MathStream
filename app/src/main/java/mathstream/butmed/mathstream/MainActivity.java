package mathstream.butmed.mathstream;
/**
 * Adam Ali
 * MathTrials -- A math game where you have to do math really fast
 * Ver 8.22.2016
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity { //main activity
    private Button startButton; //duh
    private Button exitButton; //duh
    private Button achievementButton;
    private TextView helpTitle; //help texts
    private TextView helpBody;
    private AdView bannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6495987282334475~1892641541");
        bannerAd = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAd.loadAd(adRequest);
        exitButton = (Button) findViewById(R.id.exitbutton);
        startButton = (Button) findViewById(R.id.startbutton);
        achievementButton = (Button) findViewById(R.id.highscorebutton);
        helpTitle = (TextView) findViewById(R.id.howitworks1);
        helpBody = (TextView) findViewById(R.id.howitworks2);


        startButton.setOnClickListener(new View.OnClickListener() { //startbutton what it does
            public void onClick(View v) {
                Intent StartGameIntent = new Intent(MainActivity.this, StartGame.class);
                startActivity(StartGameIntent);
                finish();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() { //exit button what it does.. duh
            public void onClick(View v) {
                finish();
            }
        });
        achievementButton.setOnClickListener(new View.OnClickListener() { //highscore button
            @Override
            public void onClick(View v) {
                Intent Achievements = new Intent(MainActivity.this, Achievements.class);
                startActivity(Achievements);
                finish();
            }
        });


    }
}
