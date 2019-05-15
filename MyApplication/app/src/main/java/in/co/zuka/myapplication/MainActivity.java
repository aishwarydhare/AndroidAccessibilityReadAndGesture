package in.co.zuka.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv = findViewById(R.id.result);
        final EditText num1 = findViewById(R.id.num1);
        final EditText num2 = findViewById(R.id.num2);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    calc(Integer.parseInt(num1.getText().toString()),
                            Integer.parseInt(num2.getText().toString()),
                            ((Button) view).getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        findViewById(R.id.add).setOnClickListener(clickListener);
        findViewById(R.id.subtract).setOnClickListener(clickListener);
        findViewById(R.id.multiply).setOnClickListener(clickListener);
        findViewById(R.id.divide).setOnClickListener(clickListener);
    }

    void calc(int num1, int num2, String ops){
        int result = 0;
        switch (ops){
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/": result = num1 / num2; break;
        }
        result_tv.setText(""+result);
    }
}

