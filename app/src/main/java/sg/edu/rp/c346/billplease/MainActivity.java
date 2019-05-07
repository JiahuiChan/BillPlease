package sg.edu.rp.c346.billplease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmt, etPax, etDiscount;
    TextView tvTotalBill, tvEachPays;
    ToggleButton tbtnServiceCharge, tbtnGST;
    Button btnSplitBill, btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmt = findViewById(R.id.etAmt);
        etPax = findViewById(R.id.etPax);
        tvTotalBill = findViewById(R.id.tvTotalBill);
        tbtnServiceCharge = findViewById(R.id.tbtnServiceCharge);
        tbtnGST = findViewById(R.id.tbtnGST);
        btnSplitBill = findViewById(R.id.btnSplitBill);
        btnReset = findViewById(R.id.btnReset);
        etDiscount = findViewById(R.id.etDiscount);

        btnSplitBill.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                if (etAmt.getText().length() > 0) {
                                                    double totalAmt = Double.parseDouble(etAmt.getText().toString());
                                                    int pax = Integer.parseInt(etPax.getText().toString());
                                                    double gstSrvChg = 1;

                                                    if (tbtnServiceCharge.isChecked() == true) {
                                                        gstSrvChg = gstSrvChg * 1.10;
                                                    }

                                                    if (tbtnGST.isChecked() == true) {
                                                        gstSrvChg = gstSrvChg * 1.07;
                                                    }

                                                    int discAmt = Integer.parseInt(etDiscount.getText().toString());
                                                    double addDisc = 0.0;

                                                    if ((discAmt < 100) && (discAmt > 0)) {
                                                        addDisc = (100.0 - discAmt) / 100;
                                                    } else {
                                                        Toast.makeText(MainActivity.this, "Discount must be more than 0 and less than 100", Toast.LENGTH_SHORT).show();
                                                    }

                                                    if (pax > 1) {
                                                        if (etDiscount.getText().length() > 0) {
                                                            double totalBill = ((totalAmt * gstSrvChg) * addDisc);

                                                            double totalBillwPax = ((totalAmt * gstSrvChg) * addDisc) / pax;

                                                            String amt = String.format("%.2f", totalBill);
                                                            String amtwPax = String.format("%.2f", totalBillwPax);

                                                            tvTotalBill.setText("Total Amount: $" + amt + "\n Per Pax: $" + amtwPax);
                                                        }else {
                                                            double totalBill = (totalAmt * gstSrvChg);
                                                            double totalBillwPax = (totalAmt * gstSrvChg) / pax;

                                                            String amt = String.format("%.2f", totalBill);
                                                            String amtwPax = String.format("%.2f", totalBillwPax);

                                                            tvTotalBill.setText("Total Amount: $" + amt + "\n Per Pax: $" + amtwPax);
                                                        }
                                                    }else {
                                                        Toast.makeText(MainActivity.this, "Pax must be more than 1", Toast.LENGTH_SHORT).show();

                                                    }
                                                }else{
                                                    Toast.makeText(MainActivity.this, "Bill Amount and Pax is Empty", Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });


                btnReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etAmt.setText("");
                        etPax.setText("");
                        tvTotalBill.setText("");
                        etDiscount.setText("");
                        tbtnServiceCharge.setChecked(false);
                        tbtnGST.setChecked(false);

                    }
                });

    }
}

