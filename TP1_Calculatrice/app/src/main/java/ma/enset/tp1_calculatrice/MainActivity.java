package ma.enset.tp1_calculatrice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText operationsView;
    private EditText resultView;
    private boolean operatorExist = false;
    private char operator;
    private double result = 0;
    private String inputNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the EditText views
        operationsView = findViewById(R.id.operationsView);
        resultView = findViewById(R.id.resultView);
        resultView.setText(Double.toString(result));

        // Find all the buttons
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMin = findViewById(R.id.btnMin);
        Button btnEgal = findViewById(R.id.btnEgal);
        Button btnMult = findViewById(R.id.btnMult);
        Button btnDiv = findViewById(R.id.btnDiv);
        //empty the screen button
        Button btnEmpty = findViewById(R.id.btnEmpty);

        // Set a single click listener for all number buttons
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                appendToOperationsView(button.getText().toString());
            }
        };

        // Set the click listener for number buttons
        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);


        // Set click listener for operator buttons
        btnPlus.setOnClickListener(operatorClickListener);
        btnMin.setOnClickListener(operatorClickListener);
        btnMult.setOnClickListener(operatorClickListener);
        btnDiv.setOnClickListener(operatorClickListener);
        // Add click listeners to operator buttons
        btnEgal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultView.setText(Double.toString(result));
                operationsView.setText(Double.toString(result));
                inputNumber = "";
                operatorExist = false;
            }
        });

        btnEmpty.setOnClickListener(v -> {
            result = 0;
            operatorExist = false;
            inputNumber = "";
            operationsView.setText("");
            resultView.setText(Double.toString(result));
        });
    }

    // Click listener for operator buttons
    private View.OnClickListener operatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            appendToOperationsView(button.getText().toString());
        }
    };

    // Method to append text to the operations view and clear the result view
    private void appendToOperationsView(String text) {
        if (operatorExist) {
            switch (operator) {
                case '+':
                    result += Double.parseDouble(text);
                    break;
                case '-':
                    result -= Double.parseDouble(text);
                    break;
                case 'X':
                    result *= Double.parseDouble(text);
                    break;
                case '/':
                    result /= Double.parseDouble(text);
                    break;
                default:
                    result += Double.parseDouble(text);
                    break;
            }
            resultView.setText(Double.toString(result));
            operatorExist = false;
        } else {
            if (!Objects.equals(text, "+") && !Objects.equals(text, "-") && !Objects.equals(text, "/") && !Objects.equals(text, "X") && !Objects.equals(text, "=")) {
                inputNumber += text;
                result = Double.parseDouble(inputNumber);
                resultView.setText(Double.toString(result));
            } else {
                operator = text.charAt(0);
                operatorExist = true;
            }
        }
        String currentText = operationsView.getText().toString();
        operationsView.setText(currentText + text);
    }
}
