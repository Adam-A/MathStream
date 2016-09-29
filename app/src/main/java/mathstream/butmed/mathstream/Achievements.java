package mathstream.butmed.mathstream;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/*
   Adam Ali
   Ver 8.24.2016
    This activity is in charge of checking the boxes for achievements based on the highscore from sharedprefs
 */
public class Achievements extends AppCompatActivity {
    private AdView bannerAdStats;
    CheckBox Achievement1;
    CheckBox Achievement2;
    CheckBox Achievement3;
    CheckBox Achievement4;
    CheckBox Achievement5;
    CheckBox Achievement6;
    CheckBox Achievement7;
    Button backButton;
    SharedPreferences highScoresPrefs;
    SharedPreferences.Editor highScorePrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6495987282334475~1892641541");
        bannerAdStats = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        bannerAdStats.loadAd(adRequest);
        highScoresPrefs = getSharedPreferences("highscores", Context.MODE_PRIVATE);
        highScorePrefsEditor = highScoresPrefs.edit();
        int highScoreCheckButton = highScoresPrefs.getInt("highscore", 0);
        TextView curHs = (TextView) findViewById(R.id.curhs);
        curHs.setText("Current highscore: " + Integer.toString(highScoreCheckButton));
        backButton = (Button) findViewById(R.id.backbutton);
        Achievement1 = (CheckBox) findViewById(R.id.checkBox1);
        Achievement2 = (CheckBox) findViewById(R.id.checkBox2);
        Achievement3 = (CheckBox) findViewById(R.id.checkBox3);
        Achievement4 = (CheckBox) findViewById(R.id.checkBox4);
        Achievement5 = (CheckBox) findViewById(R.id.checkBox5);
        Achievement6 = (CheckBox) findViewById(R.id.checkBox6);
        Achievement7 = (CheckBox) findViewById(R.id.checkBox7);
        if (highScoreCheckButton >= 1) {
            Achievement1.setClickable(true);
            Achievement1.setEnabled(true);
            Achievement1.setChecked(true);
            Achievement1.setClickable(false);

        }
        if (highScoreCheckButton >= 5) {
            Achievement2.setClickable(true);
            Achievement2.setEnabled(true);
            Achievement2.setChecked(true);
            Achievement2.setClickable(false);
        }
        if (highScoreCheckButton >= 25) {
            Achievement3.setClickable(true);
            Achievement3.setEnabled(true);
            Achievement3.setChecked(true);
            Achievement3.setClickable(false);
        }
        if (highScoreCheckButton >= 50) {
            Achievement4.setClickable(true);
            Achievement4.setEnabled(true);
            Achievement4.setChecked(true);
            Achievement4.setClickable(false);
        }
        if (highScoreCheckButton >= 100) {
            Achievement5.setClickable(true);
            Achievement5.setChecked(true);
            Achievement5.setEnabled(true);
            Achievement5.setClickable(false);
        }
        if (highScoreCheckButton >= 250) {
            Achievement6.setClickable(true);
            Achievement6.setChecked(true);
            Achievement6.setEnabled(true);
            Achievement6.setClickable(false);
        }
        if (highScoreCheckButton >= 500) {
            Achievement7.setClickable(true);
            Achievement7.setChecked(true);
            Achievement7.setEnabled(true);
            Achievement7.setClickable(false);
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(Achievements.this, MainActivity.class);
                startActivity(backIntent);
                finish();
            }
        });

    }
}
