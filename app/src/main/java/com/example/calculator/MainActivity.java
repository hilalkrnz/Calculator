package com.example.calculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText calc_edt_formula;
    private AppCompatTextView calc_tv_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calc_edt_formula = findViewById(R.id.calc_edt_formula);
        calc_edt_formula.setShowSoftInputOnFocus(false);

        calc_tv_result = findViewById(R.id.calc_tv_result);
    }

    private void updateText(String stringToAdd) {
        String oldString = Objects.requireNonNull(calc_edt_formula.getText()).toString();
        int cursorPos = calc_edt_formula.getSelectionStart();
        String leftString = oldString.substring(0, cursorPos);
        String rightString = oldString.substring(cursorPos);
        calc_edt_formula.setText(String.format("%s%s%s", leftString, stringToAdd, rightString));
        calc_edt_formula.setSelection(cursorPos + 1);
    }


    public void clickZero(View view) {
        updateText("0");
        operation();
    }

    public void clickOne(View view) {
        updateText("1");
        operation();
    }

    public void clickTwo(View view) {
        updateText("2");
        operation();
    }

    public void clickThree(View view) {
        updateText("3");
        operation();
    }

    public void clickFour(View view) {
        updateText("4");
        operation();
    }

    public void clickFive(View view) {
        updateText("5");
        operation();
    }

    public void clickSix(View view) {
        updateText("6");
        operation();
    }

    public void clickSeven(View view) {
        updateText("7");
        operation();
    }

    public void clickEight(View view) {
        updateText("8");
        operation();
    }

    public void clickNine(View view) {
        updateText("9");
        operation();
    }

    public void clickDiv(View view) {
        updateText("÷");
        operation();
    }

    public void clickMul(View view) {
        updateText("×");
        operation();
    }

    public void clickSub(View view) {
        updateText("-");
        operation();
    }

    public void clickAdd(View view) {
        updateText("+");
        operation();
    }

    public void clickEqual(View view) {
        operation();
        String resultString = calc_tv_result.getText().toString();
        calc_edt_formula.setText(resultString);
        calc_edt_formula.setSelection(resultString.length());
        calc_tv_result.setText("");
    }

    public void clickDot(View view) {
        updateText(".");
    }

    public void clickPlusMinus(View view) {
        updateText("-");
    }

    public void clickClear(View view) {
        calc_edt_formula.setText("");
        calc_tv_result.setText("");
    }

    public void clickDelete(View view) {
        int cursorPos = calc_edt_formula.getSelectionStart();
        int textLength = Objects.requireNonNull(calc_edt_formula.getText()).length();

        if (cursorPos != 0 && textLength != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) calc_edt_formula.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            calc_edt_formula.setText(selection);
            calc_edt_formula.setSelection(cursorPos - 1);
        }

        calc_tv_result.setText("");
    }

    public void clickParenthesis(View view) {
        int cursorPos = calc_edt_formula.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLength = Objects.requireNonNull(calc_edt_formula.getText()).length();


        for (int i = 0; i < cursorPos; i++) {
            if (calc_edt_formula.getText().toString().charAt(i) == '(') {
                openPar += 1;
            }
            if (calc_edt_formula.getText().toString().charAt(i) == ')') {
                closePar += 1;
            }
        }

        if (openPar == closePar || calc_edt_formula.getText().toString().charAt(textLength - 1) == '(') {
            updateText("(");
        } else if (closePar < openPar && !(calc_edt_formula.getText().toString().charAt(textLength - 1) == '(')) {
            updateText(")");

        }
        calc_edt_formula.setSelection(cursorPos + 1);
    }


    public void operation() {
        String data = Objects.requireNonNull(calc_edt_formula.getText()).toString();
        String[] numbers = data.split("(?<=[+×÷-])|(?=[+×÷-])");

        double result = 0.0;
        String operator = "";
        for (String s : numbers) {
            String number = s.trim();
            if (number.equals("+") || number.equals("-") || number.equals("×") || number.equals("÷")) {
                operator = number;
            } else {
                double operand = Double.parseDouble(number);
                if (operator.equals("")) {
                    result = operand;
                } else {
                    switch (operator) {
                        case "+":
                            result += operand;
                            break;
                        case "-":
                            result -= operand;
                            break;
                        case "×":
                            result *= operand;
                            break;
                        case "÷":
                            if (operand == 0) {
                                Toast.makeText(this, "Cannot divide by zero!", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            result /= operand;
                            break;
                    }
                }
            }
        }
        if (result % 1 == 0) {
            calc_tv_result.setText(String.format("%.0f", result));
        } else {
            calc_tv_result.setText(String.valueOf(result));
        }
    }



}