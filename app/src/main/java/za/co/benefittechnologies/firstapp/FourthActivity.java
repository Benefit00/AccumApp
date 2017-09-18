package za.co.benefittechnologies.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FourthActivity extends AppCompatActivity {

    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        final EditText P = (EditText)findViewById(R.id.principle);
        final EditText Ra = (EditText)findViewById(R.id.rate);
        final EditText N = (EditText)findViewById(R.id.duration);
        final EditText res = (EditText)findViewById(R.id.result);
        final EditText inter = (EditText)findViewById(R.id.totalInterest);

        calculateButton = (Button)findViewById(R.id.button4);

        calculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public  void onClick(View v) {
                String st1 = P.getText().toString();
                String st2 = Ra.getText().toString();
                String st3 = N.getText().toString();

                if(TextUtils.isEmpty(st1)) {
                    P.setError("Please enter a principal amount.");
                    P.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(st2)) {
                    Ra.setError("Please enter a interest rate.");
                    Ra.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(st3)) {
                    N.setError("Please enter a period of loan.");
                    N.requestFocus();
                    return;
                }

                float p = Float.parseFloat(st1);
                float r = Float.parseFloat(st2);
                float n = Float.parseFloat(st3);

                //Stores the principal value into the variable principal as a float
                float principal = calcPrin(p);

                //Stores the Rate into the variable rate stored in months as a decimal
                float rate = calcRate(r);

                //stores the duration of the loan in the variable term in months.
                float term = calcTerm(n);

                //Stores the (1 + r)^n in the variable calDiv
                float calDiv = CalDiv(rate, term);

                //calculates and stores the total amount to pay a month in variable FD
                float FD = calFD(principal, rate, calDiv);

                res.setText(String.valueOf(FD));
            }
        });



    }

    public float calcPrin(Float p){
        return p;
    }

    public float calcRate(Float r){
        return  (r/12/100);
    }

    public float calcTerm(Float n){
        return (n*12);
    }

    public float CalDiv(float rate, float term) {
        return (float) Math.pow(1 + rate,term);
    }

    public float calFD(float principal, float rate, float calDiv) {
        return ( principal * rate * calDiv / (calDiv - 1) ) ;
    }
}
