package com.example.amannegi.calculator20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtCalc, txtOp, txtSign, txtCheck;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, dot, add, subtract, multiply, divide, delete, equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtOp = (TextView) findViewById(R.id.txtOp);
        txtSign = (TextView) findViewById(R.id.txtSign);
        txtCalc = (TextView) findViewById(R.id.txtcalc);
        txtCheck = (TextView) findViewById(R.id.txtCheck);
        btn0 = (Button) findViewById(R.id.b0);
        btn1 = (Button) findViewById(R.id.b1);
        btn2 = (Button) findViewById(R.id.b2);
        btn3 = (Button) findViewById(R.id.b3);
        btn4 = (Button) findViewById(R.id.b4);
        btn5 = (Button) findViewById(R.id.b5);
        btn6 = (Button) findViewById(R.id.b6);
        btn7 = (Button) findViewById(R.id.b7);
        btn8 = (Button) findViewById(R.id.b8);
        btn9 = (Button) findViewById(R.id.b9);
        dot = (Button) findViewById(R.id.dot);
        add = (Button) findViewById(R.id.add);
        subtract = (Button) findViewById(R.id.sub);
        multiply = (Button) findViewById(R.id.multiply);
        divide = (Button) findViewById(R.id.divide);
        delete = (Button) findViewById(R.id.del);
        equal = (Button) findViewById(R.id.equal);

        txtCalc.setText("0");

        //Number keys-------------------------------------------------------------------------------
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("9");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberKeyAction("0");
            }
        });

        //dot button--------------------------------------------------------------------------------
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtCalc.getText().toString().contains(".")) {
                    String value = txtCalc.getText().toString();
                    String result = value.concat(".");
                    txtCalc.setText(result);
                }
            }
        });

        //Delete key--------------------------------------------------------------------------------
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder str = new StringBuilder(txtCalc.getText().toString());
                if (str.length() > 1) {
                    str.deleteCharAt(txtCalc.getText().length() - 1);
                    txtCalc.setText(str);
                } else if (str.length() == 1) {
                    if (txtCalc.getText().toString().equals("0") && !txtSign.getText().toString().isEmpty()) {
                        txtSign.setText("");
                        txtCalc.setText(txtOp.getText().toString());
                        txtOp.setText("");
                    } else {
                        txtCalc.setText("0");
                    }
                }
            }
        });

        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txtCalc.setText("0");
                txtOp.setText(null);
                txtSign.setText(null);
                txtCheck.setText(null);
                return true;
            }
        });

        //operation buttons-------------------------------------------------------------------------
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signKeyAction("+");
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signKeyAction("-");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signKeyAction("*");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signKeyAction("/");
            }
        });


        //equal button------------------------------------------------------------------------------
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtSign.getText().toString().equals("")) {
                    solveEquation(txtOp.getText().toString().trim(), txtCalc.getText().toString().trim());
                }
            }
        });

    }

    //Custom methods--------------------------------------------------------------------------------
    public void numberKeyAction(String s) {
        if (txtCalc.getText().toString().equals("0")) {
            txtCalc.setText((s));
        } else {
            String value = txtCalc.getText().toString();
            String result = value.concat(s);
            txtCalc.setText(result);
        }
    }

    public void signKeyAction(String s) {
        if (txtSign.getText().toString().equals("") && txtOp.getText().toString().equals("")) {
            txtSign.setText(s);
            txtOp.setText(txtCalc.getText().toString());
            txtCalc.setText("0");
        } else if (!txtSign.getText().toString().equals("") && !txtOp.getText().toString().equals("")) {
            if (txtCalc.getText().toString().equals("0")) {
                txtSign.setText(s);
            } else {
                solveEquation(txtOp.getText().toString().trim(), txtCalc.getText().toString().trim());
                txtOp.setText(txtCalc.getText().toString());
                txtSign.setText(s);
                txtCalc.setText("0");
            }
        }
    }

    public void solveEquation(String operand1, String operand2) {
        double op1 = Double.parseDouble(operand1);
        double op2 = Double.parseDouble(operand2);
        double result;
        String sign = txtSign.getText().toString();
        switch (sign) {
            case "+":
                result = (op1 + op2);
                break;
            case "-":
                result = (op1 - op2);
                break;
            case "*":
                result = (op1 * op2);
                break;
            case "/":
                result = (op1 / op2);
                break;
            default:
                result = 0;
        }
        String sResult = String.valueOf(result);
        txtCalc.setText(sResult);
        txtOp.setText("");
        txtSign.setText("");
        txtCheck.setText(operand1 + sign + operand2 + " = " + sResult);
    }
}
