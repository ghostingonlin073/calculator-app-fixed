package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private EditText display;
    private String currentNumber = "";
    private String operation = "";
    private double firstNumber = 0;
    private boolean isNewOperation = true;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        display = findViewById(R.id.display);
        
        // Number buttons
        setNumberButton(R.id.btn_0, "0");
        setNumberButton(R.id.btn_1, "1");
        setNumberButton(R.id.btn_2, "2");
        setNumberButton(R.id.btn_3, "3");
        setNumberButton(R.id.btn_4, "4");
        setNumberButton(R.id.btn_5, "5");
        setNumberButton(R.id.btn_6, "6");
        setNumberButton(R.id.btn_7, "7");
        setNumberButton(R.id.btn_8, "8");
        setNumberButton(R.id.btn_9, "9");
        
        // Operation buttons
        setOperationButton(R.id.btn_add, "+");
        setOperationButton(R.id.btn_subtract, "-");
        setOperationButton(R.id.btn_multiply, "*");
        setOperationButton(R.id.btn_divide, "/");
        
        // Other buttons
        findViewById(R.id.btn_equals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.btn_clear).setOnClickListener(v -> clear());
    }
    
    private void setNumberButton(int buttonId, String number) {
        findViewById(buttonId).setOnClickListener(v -> {
            if (isNewOperation) {
                currentNumber = number;
                isNewOperation = false;
            } else {
                currentNumber += number;
            }
            display.setText(currentNumber);
        });
    }
    
    private void setOperationButton(int buttonId, String op) {
        findViewById(buttonId).setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                firstNumber = Double.parseDouble(currentNumber);
                operation = op;
                isNewOperation = true;
            }
        });
    }
    
    private void calculateResult() {
        if (!currentNumber.isEmpty() && !operation.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = 0;
            
            switch (operation) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            
            currentNumber = String.valueOf(result);
            display.setText(currentNumber);
            operation = "";
            isNewOperation = true;
        }
    }
    
    private void clear() {
        currentNumber = "";
        operation = "";
        firstNumber = 0;
        isNewOperation = true;
        display.setText("0");
    }
}
