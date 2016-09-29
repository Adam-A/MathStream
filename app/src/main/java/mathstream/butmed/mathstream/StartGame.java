package mathstream.butmed.mathstream;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/*
 Adam Ali
 Ver 8.24.2016
 This Activity is the actual game it self. This happens when the user presses the start button on MainActivity
 */
public class StartGame extends AppCompatActivity {
    private int userAnswer; //users answer
    private TextView countDown; //countdown textview
    private int rightAnswer; //obvious v2
    private int highScore = 0; //obvious
    private int currHighScore = 0;
    private TextView numbersFromdigitsMachine; //actual digits
    private EditText answerform; //answer form
    CountDownTimer countDownTimerTask; //countdown timer
    SharedPreferences.Editor highScoresPrefsEditor; //saves the highest score onto a file for Achievements activity
    SharedPreferences highScoresPrefs; //see right avove this line

    @Override
    /*
     * When the MainActivity's startButton is pressed, it does all this. Which is essentially the heart of the game
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        final InputMethodManager numericControl = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final digitsMachine temp = new digitsMachine();
        numbersFromdigitsMachine = (TextView) findViewById(R.id.numbers);
        answerform = (EditText) findViewById(R.id.answerform);
        answerform.requestFocus();
        highScoresPrefs = this.getSharedPreferences("highscores", Context.MODE_PRIVATE);
        numericControl.showSoftInput(answerform, InputMethodManager.SHOW_IMPLICIT); //shows the numeric keyboard oncreation
        highScoresPrefsEditor = highScoresPrefs.edit();
        countDown = (TextView) findViewById(R.id.countdown);
        temp.setDifficulty(10);
        temp.sign();
        temp.setFirst(); //For only the first game, I had to do this so then the user actually gets a game. Not sure why it worked
        temp.setSecond();
        numbersFromdigitsMachine.setText(temp.firstDigittoString() + " " + temp.getSign() + " " + temp.secondDigittoString());
        countDownTimerTask = new CountDownTimer(6000, 1000) {
            public void onTick(long millisUntilFinished) { //after countdown starts
                numericControl.showSoftInput(answerform, InputMethodManager.SHOW_IMPLICIT);
                long temp = millisUntilFinished / 1000;
                Long.toString(temp);
                countDown.setText(Long.toString(temp));

            }

            public void onFinish() { //after countdown runs out
                Intent outOfTime = new Intent(StartGame.this, MainActivity.class);
                int highscorecheck = highScoresPrefs.getInt("highscore", 0);
                if (highscorecheck < highScore) { //highscore check to see whether their highest score in that game is greater than overall hs
                    highScoresPrefsEditor.putInt("highscore", highScore);
                    highScoresPrefsEditor.commit();

                }
                if (temp.getSign().equals("*")) {
                    rightAnswer = (temp.getFirst() * temp.getSecond());
                } else if (temp.getSign().equals("+")) {
                    rightAnswer = (temp.getFirst() + temp.getSecond());
                } else {
                    rightAnswer = (temp.getFirst() - temp.getSecond());
                }
                countDownTimerTask.cancel();
                Toast outOfTimeToast = Toast.makeText(getApplicationContext(), "Not fast enough! The correct answer was " + rightAnswer+ ". Highest level achieved this game was "+ currHighScore, Toast.LENGTH_LONG);
                outOfTimeToast.show();
                startActivity(outOfTime);
                finish();

            }

        }.start();
        answerform.setOnEditorActionListener(new EditText.OnEditorActionListener() { //what happens when the editor is on focus
            Intent wrongAnsGameover = new Intent(StartGame.this, MainActivity.class);

            public boolean onEditorAction(TextView v, int actionID, KeyEvent event) {
                if (actionID == EditorInfo.IME_ACTION_SEARCH ||
                        actionID == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (answerform.getText() == null || answerform.getText().toString().equals("") || answerform.getText().toString().equals("-")) {
                        Toast noAnsToast = Toast.makeText(getApplicationContext(), "Please enter a valid answer", Toast.LENGTH_SHORT);
                        noAnsToast.show();
                    } else {
                        userAnswer = Integer.parseInt(answerform.getText().toString());
                        double difficulty = 10;
                        if (temp.getSign().equals("+") && userAnswer == (temp.getFirst() + temp.getSecond())) { //This giant if statement resets the game if the user wins else goes back to main menu on loss
                            currHighScore++;
                            countDownTimerTask.cancel();
                            countDownTimerTask.start();
                            highScore++;
                            difficulty = difficulty * 1.5;
                            temp.setDifficulty(difficulty);
                            temp.sign();
                            temp.setFirst();
                            temp.setSecond();
                            answerform.setText(null);
                            numbersFromdigitsMachine.setText(temp.firstDigittoString() + " " + temp.getSign() + " " + temp.secondDigittoString());
                        } else if (temp.getSign().equals("-") && userAnswer == (temp.getFirst() - temp.getSecond())) {
                            currHighScore++;
                            countDownTimerTask.cancel();
                            countDownTimerTask.start();
                            highScore++;
                            difficulty = difficulty * 1.5;
                            temp.setDifficulty(difficulty);
                            temp.sign();
                            temp.setFirst();
                            temp.setSecond();
                            answerform.setText(null);
                            numbersFromdigitsMachine.setText(temp.firstDigittoString() + " " + temp.getSign() + " " + temp.secondDigittoString());
                        } else if (temp.getSign().equals("*") && userAnswer == (temp.getFirst() * temp.getSecond())) {
                            currHighScore++;
                            countDownTimerTask.cancel();
                            countDownTimerTask.start();
                            highScore++;
                            difficulty = difficulty * 1.1;
                            temp.setDifficulty(difficulty);
                            temp.sign();
                            temp.setFirst();
                            temp.setSecond();
                            answerform.setText(null);
                            numbersFromdigitsMachine.setText(temp.firstDigittoString() + " " + temp.getSign() + " " + temp.secondDigittoString());

                        } else {
                            if (temp.getSign().equals("*")) {
                                rightAnswer = (temp.getFirst() * temp.getSecond());
                            } else if (temp.getSign().equals("+")) {
                                rightAnswer = (temp.getFirst() + temp.getSecond());
                            } else {
                                rightAnswer = (temp.getFirst() - temp.getSecond());
                            }
                            Toast correctAnswerToast = Toast.makeText(getApplicationContext(), "Wrong! The correct answer was " + rightAnswer + ". Highest level achieved this game was "+ currHighScore, Toast.LENGTH_LONG);
                            correctAnswerToast.show();
                            int highscorecheck = highScoresPrefs.getInt("highscore", 0);
                            if (highscorecheck < highScore) {
                                highScoresPrefsEditor.putInt("highscore", highScore);
                                highScoresPrefsEditor.commit();
                            }
                            countDownTimerTask.cancel();
                            startActivity(wrongAnsGameover);
                            finish();
                        }
                    }
                }
                return true;
            }


        });
    }
}
