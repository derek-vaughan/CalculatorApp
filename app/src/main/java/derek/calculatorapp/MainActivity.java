package derek.calculatorapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Objects for on-screen widgets:
    TextView screen;
    Button zeroButton, oneButton, twoButton, threeButton,
            fourButton, fiveButton, sixButton, sevenButton,
            eightButton, nineButton, clearButton, lParenButton,
            rParenButton, exponentButton, multiplyButton, divideButton,
            plusButton, minusButton, dotButton, equalsButton;

    // onCreate Method:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize each object on screen.
        screen = (TextView) findViewById(R.id.screen);
        zeroButton = (Button) findViewById(R.id.zeroButton);
        oneButton = (Button) findViewById(R.id.oneButton);
        twoButton = (Button) findViewById(R.id.twoButton);
        threeButton = (Button) findViewById(R.id.threeButton);
        fourButton = (Button) findViewById(R.id.fourButton);
        fiveButton = (Button) findViewById(R.id.fiveButton);
        sixButton = (Button) findViewById(R.id.sixButton);
        sevenButton = (Button) findViewById(R.id.sevenButton);
        eightButton = (Button) findViewById(R.id.eightButton);
        nineButton = (Button) findViewById(R.id.nineButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        lParenButton = (Button) findViewById(R.id.lParenButton);
        rParenButton = (Button) findViewById(R.id.rParenButton);
        exponentButton = (Button) findViewById(R.id.exponentButton);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        divideButton = (Button) findViewById(R.id.divideButton);
        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = (Button) findViewById(R.id.minusButton);
        dotButton = (Button) findViewById(R.id.dotButton);
        equalsButton = (Button) findViewById(R.id.equalsButton);

        // On-click listeners for each button:
        zeroButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("0");
            }
        });

        oneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("1");
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("2");
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("3");
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("4");
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("5");
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("6");
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("7");
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("8");
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("9");
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){screen.setText(null); // Clears all text from screen.
            }
        });

        lParenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("(");
            }
        });

        rParenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append(")");
            }
        });

        exponentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("^");
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("*");
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("/");
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("+");
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append("-");
            }
        });

        dotButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                screen.append(".");
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String userInput = screen.getText().toString();
                screen.setText(solve(userInput));
            }
        });


    }

    public String solve(String userInput){
        String operators[] = userInput.split("[0-9]+");
        String operands[] = userInput.split("[+-]");
        int answer = Integer.parseInt(operands[0]);

        for(int i = 1; i < operands.length; i++){
            if(operators[i].equals("+")){
                answer += Integer.parseInt(operands[i]);
            }
            else{
                answer -= Integer.parseInt(operands[i]);
            }
        }

        /*
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, answer, Toast.LENGTH_LONG);
        toast.show();
        */

        return Integer.toString(answer);
    }
}
