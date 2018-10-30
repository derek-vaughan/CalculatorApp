package derek.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Stack;

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
            public void onClick(View v){screen.setText(null);
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
                screen.setText(inToPost(userInput));
            }
        });
    }

    public String inToPost(String userInput){
        StringBuffer postfixInput = new StringBuffer(userInput.length());
        Stack<Character> stack = new Stack<Character>();
        char ch;
        int inputLength = userInput.length();

        for(int i = 0; i < inputLength; i++){
            ch = userInput.charAt(i);
            while(ch == ' '){
                i++;
            }

            if(isOperand(ch)){
                postfixInput.append(userInput.charAt(i));

                if((i + 1) >= inputLength || (isOperator(userInput.charAt(i + 1)))){
                    postfixInput.append(' ');
                }
            }
            else if(getPrecedence(ch) != 0) {
                while ((!stack.isEmpty()) && (getPrecedence(stack.peek()) >= getPrecedence(ch)) && (stack.peek() != '(')) {
                    postfixInput.append(stack.peek());
                    postfixInput.append(' ');
                    stack.pop();
                }

                stack.push(ch);
            }
            else if(ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfixInput.append(stack.peek());
                    stack.pop();
                }

                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            postfixInput.append(stack.pop());
            postfixInput.append(' ');
        }

        String finalAnswer = solve(postfixInput.toString());
        //String finalAnswer = postfixInput.toString();
        return finalAnswer;
    }

    public String solve(String postfixExp){
        Stack<String> stack = new Stack<String>();
        char ch;
        int inputLength = postfixExp.length();

        for(int i = 0; i < inputLength; i++){
            ch = postfixExp.charAt(i);

            if(ch == ' '){
                // Do nothing, space found.
            }
            else if(isOperand(ch)){
                int n = 0; // This will eventually hold our final value.

                while(isOperand(ch)){
                    n = (n * 10) + (int)(ch - '0');
                    i++;
                    ch = postfixExp.charAt(i);
                }
                i--;

                stack.push(Integer.toString(n));
            }
            else{
                int tempOne = Integer.parseInt(stack.pop());
                int tempTwo = Integer.parseInt(stack.pop());

                switch(ch){
                    case '+':
                        stack.push(Integer.toString(tempTwo + tempOne));
                        break;
                    case '-':
                        stack.push(Integer.toString(tempTwo - tempOne));
                        break;
                    case '*':
                        stack.push(Integer.toString(tempTwo * tempOne));
                        break;
                    case '/':
                        stack.push(Integer.toString(tempTwo / tempOne));
                        break;
                    case '^':
                        stack.push(Double.toString(Math.pow(tempTwo, tempOne)));
                        break;
                }
            }
        }

        String evaluation = stack.pop();
        return evaluation;
    }

    public boolean isOperator(char ch){
        if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '(' || ch == ')'){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isOperand(char ch){
        if(Character.isDigit(ch)){
            return true;
        }
        else{
            return false;
        }
    }

    public int getPrecedence(char operand){
        switch(operand){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
}
