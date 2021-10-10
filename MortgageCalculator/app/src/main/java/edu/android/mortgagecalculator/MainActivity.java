package edu.android.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final CharSequence MONTH_ERROR_MESSAGE = "Please select a number on months";
    EditText amount;
    CheckBox insurance;
    Button calculate;
    SeekBar customSeekBar;
    RadioGroup loanGroup;
    RadioButton loanTerm1;
    RadioButton loanTerm2;
    RadioButton loanTerm3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView monthlyPayment = findViewById(R.id.monthlyPayment);
        amount = (EditText) findViewById(R.id.amount);
        insurance = (CheckBox) findViewById(R.id.insurance);

        loanGroup = (RadioGroup) findViewById(R.id.loanGroup);
        loanTerm1 = (RadioButton) findViewById(R.id.loanTerm1);
        loanTerm2 = (RadioButton) findViewById(R.id.loanTerm2);
        loanTerm3 = (RadioButton) findViewById(R.id.loanTerm3);

        customSeekBar = (SeekBar) findViewById(R.id.seekBar);
        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView aprProgress = (TextView) findViewById(R.id.aprProgress);
                //Retrieved this piece of code from stack overflow, I was curiois to see how to get the
                //number underneath the seek bar to follow the seek bar when its position is changed
                //https://stackoverflow.com/questions/41774963/android-seekbar-show-progress-value-along-the-seekbar
                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                aprProgress.setText("" + progress/1.0);
                aprProgress.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        calculate = (Button) findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            //Default loan term for this app is 15 years, 60 months
            double amt = 0.0;


            @Override
            public void onClick(View v) {
                int months = 0;

                double progress = customSeekBar.getProgress();
                if(amount.getText().toString().equals("") == false) {
                    double amt = Double.parseDouble(amount.getText().toString());

                    if (amt != 0.0) {
                        double total = 0.0;
                        double monthly_interest = (progress / 1200.00000);
                        int id = loanGroup.getCheckedRadioButtonId();
                        double insurance_tax_interest = 0.0;
                        if (insurance.isChecked()) {
                            insurance_tax_interest = .001 * (amt);

                        }

                        if (loanTerm1.getId() == id) {
                            months = 15 * 12;
                        }

                        if (loanTerm2.getId() == id) {
                            months = 20 * 12;
                        }

                        if (loanTerm3.getId() == id) {
                            months = 30 * 12;
                        }

                        if (progress == 0) {

                            if (months == 0) {
                                Toast.makeText(getApplicationContext(), MONTH_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                            } else {
                                total = (amt / months) + insurance_tax_interest;

                                monthlyPayment.setText(String.format("Monthly Payment: $ %.2f ", total));
                            }

                        }

                        if (progress != 0) {

                            if (months == 0) {
                                Toast.makeText(getApplicationContext(), MONTH_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
                            } else {
                                total = (amt * (monthly_interest / (1 - Math.pow(1 + monthly_interest, -months)))) + insurance_tax_interest;
                                monthlyPayment.setText(String.format("Monthly Payment: $ %.2f", total));
                            }
                        }
                    }
                        else {
                            monthlyPayment.setText(String.format("Monthly Payment: "));
                        }

                }

            }

        });

    }
}