/*
* Derek Vaughan
* Simple Android Calculator Application
* 30 October, 2018
*/
package derek.calculatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        equalsButton = (Button) findViewById(R.id.equalsButton);

        // On-click listeners for each button:
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("0");
            }
        });
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("1");
            }
        });
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("2");
            }
        });
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("3");
            }
        });
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("4");
            }
        });
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("5");
            }
        });
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("6");
            }
        });
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("7");
            }
        });
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("8");
            }
        });
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("9");
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText(null);
            }
        });
        lParenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("(");
            }
        });
        rParenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append(")");
            }
        });
        exponentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("^");
            }
        });
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("*");
            }
        });
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("/");
            }
        });
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("+");
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.append("-");
            }
        });
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = screen.getText().toString();
                screen.setText(inToPost(userInput));
            }
        });
    }

    // Infix -> Postfix Conversion Function:
    public String inToPost(String userInput){
        // Pre-Check for Valid Expression:
        if(!validate(userInput)){
           return "Error!";
        }

        StringBuffer postfixInput = new StringBuffer(userInput.length());
        Stack<Character> stack = new Stack<Character>();
        char ch;
        int inputLength = userInput.length();

        // Adaptation of the Shunting Yard Algorithm:
        for(int i = 0; i < inputLength; i++){
            ch = userInput.charAt(i);

            if(ch == ' '){
                // Do nothing
            }
            else if(isOperand(ch) || ch == '.'){
                postfixInput.append(ch);
            }
            else if(ch == '('){
                stack.push(ch);
            }
            else if(ch == ')'){
                while((!stack.isEmpty()) && (stack.peek() != '(')){
                    postfixInput.append(stack.pop());
                }

                if(!stack.isEmpty()){
                    stack.pop();
                }
                else{
                    return "Parentheses Error!";
                }
            }
            else if(isOperator(ch)){
                postfixInput.append(' ');

                while((!stack.isEmpty()) && (getPrecedence(stack.peek()) >= getPrecedence(ch))){
                    postfixInput.append(stack.pop());
                }

                stack.push(ch);
            }
            else{
                return "Input Error!";
            }
        }

        while(!stack.isEmpty()){
            postfixInput.append(stack.pop());
        }

        String finalAnswer = postfixInput.toString();
        return solve(finalAnswer);
    }

    // Solve function evaluates a postfix expression:
    public String solve(String postfixExp) {
        Stack<String> stack = new Stack<String>();
        char ch;
        int inputLength = postfixExp.length();

        for (int i = 0; i < inputLength; i++) {
            ch = postfixExp.charAt(i);

            if (ch == ' ') {
                // Do nothing, space found.
            }
            else if (isOperand(ch)) {
                int n = 0; // This will eventually hold our final value.

                while (isOperand(ch)) {
                    n = (n * 10) + (int) (ch - '0');
                    i++;
                    ch = postfixExp.charAt(i);
                }
                i--;

                stack.push(Integer.toString(n));
            }
            else {
                int tempOne = Integer.parseInt(stack.pop());
                int tempTwo = Integer.parseInt(stack.pop());

                switch (ch) {
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
                        if (tempOne == 0) {
                            return "Error: Division by 0";
                        } else {
                            stack.push(Integer.toString(tempTwo / tempOne));
                        }
                        break;
                    case '^':
                        int temp3 = (int)(Math.pow(tempTwo, tempOne));
                        stack.push(Integer.toString(temp3));
                        break;
                }
            }
        }

        String evaluation = stack.pop();
        return evaluation;
    }

    // Validation function to check for correct input:
    public boolean validate(String userInput){
        int operatorCount = 0, leftParenCount = 0, rightParenCount = 0, inputLength = userInput.length();
        char ch;

        for(int i = 0; i < inputLength; i++){
            ch = userInput.charAt(i);

            if(ch == '('){
                leftParenCount++;
            }
            else if(ch == ')'){
                rightParenCount++;
            }

            if(isOperator(ch)){
                operatorCount++;
            }
        }

        if((operatorCount == 0) || (leftParenCount != rightParenCount)){
            return false;
        }

        else if(isOperator(userInput.charAt(0)) && (userInput.charAt(0) != '(')){
            return false;
        }

        return true; // Validation Successful!
    }

    // isOperator function returns true if the passed argument is a operator:
    public boolean isOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '(' || ch == ')') {
            return true;
        }
        else {
            return false;
        }
    }

    // isOperand function returns true if the passed argument is a operand (digit):
    public boolean isOperand(char ch) {
        if (Character.isDigit(ch)) {
            return true;
        }
        else {
            return false;
        }
    }

    /* getPrecendence function assigns each operator a precedence level according to
       the order of operations: */
    public int getPrecedence(char operator) {
        switch (operator) {
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
