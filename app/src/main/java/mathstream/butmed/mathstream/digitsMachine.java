package mathstream.butmed.mathstream;

import java.util.Random;

/**
 * Created by Adam on 8/18/2016.
 * This java class, digitsMachine, spits out two digits to be calculated by the user.
 * The sign is chosen at random via array.
 */
public class digitsMachine {
    private double Difficulty;
    private int firstDigit;
    private int secondDigit;
    private String sign = "";
    public digitsMachine(){
    }
    public String firstDigittoString(){ //Returns first digit as string

        return Integer.toString(firstDigit);
    }
    public String secondDigittoString(){ //Returns second digit as stringl

        return Integer.toString(secondDigit);
    }

    public int getFirst(){ //Returns first digit

        return firstDigit;
    }
    public int getSecond(){ //Returns second digit

        return secondDigit;
    }

    public int setFirst(){ //Sets first digit
        firstDigit = (int) Math.round(Math.random() * Difficulty);
        return firstDigit;
    }
    public int setSecond(){ //Sets second digit
        secondDigit = (int) Math.round(Math.random() * Difficulty);
        return secondDigit;
    }

    public String getSign(){ //Returns the sign

        return sign;
    }
    public String sign(){ //Picks the sign by random
        String[] signs = {"+","-","*"};
        Random random = new Random();
        int select = random.nextInt(signs.length);
        this.sign = signs[select];
        return signs[select];

    }
    public void setDifficulty(double Difficulty){ //Obviously sets difficulty. Go back to StartGame Activity and look at the editor if statement

        this.Difficulty = Difficulty;
    }

}
