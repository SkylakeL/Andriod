package skylake.calculatormr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Stack;

public class Home extends AppCompatActivity {

    Stack<String> sign = new Stack<>();
    Stack<Double> digits = new Stack<>();
    Double op1 = 0.0;
    Double op2 = 0.0;
    String buffer = "";
    Double res = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Button zero = (Button) findViewById(R.id.button16);
        final Button one = (Button) findViewById(R.id.button);
        final Button two = (Button) findViewById(R.id.button3);
        final Button three = (Button) findViewById(R.id.button5);
        final Button four = (Button) findViewById(R.id.button2);
        final Button five = (Button) findViewById(R.id.button4);
        final Button six = (Button) findViewById(R.id.button6);
        final Button seven = (Button) findViewById(R.id.button7);
        final Button eight = (Button) findViewById(R.id.button8);
        final Button nine = (Button) findViewById(R.id.button9);
        final Button plus = (Button) findViewById(R.id.button10);
        final Button minus = (Button) findViewById(R.id.button11);
        final Button times = (Button) findViewById(R.id.button12);
        final Button divide = (Button) findViewById(R.id.button14);
        final Button negtive = (Button) findViewById(R.id.button15);
        final Button dot = (Button) findViewById(R.id.button17);
        final Button equal = (Button) findViewById(R.id.button18);
        final Button cancle = (Button) findViewById(R.id.button20);
        final Button clear = (Button) findViewById(R.id.button19);
        final EditText result = (EditText) findViewById(R.id.editText3);
        final EditText screen = (EditText) findViewById(R.id.editText);

        assert zero != null;
        assert one != null;
        assert two != null;
        assert three != null;
        assert four != null;
        assert five != null;
        assert six != null;
        assert seven != null;
        assert eight != null;
        assert nine != null;
        assert plus != null;
        assert minus != null;
        assert divide != null;
        assert negtive != null;
        assert dot != null;
        assert equal != null;
        assert result != null;
        assert screen != null;
        assert times != null;
        assert cancle != null;
        assert clear != null;

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screen.setText("_________________");
                result.setText("0");
                buffer = "";
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") != 0) {
                    if (checkSign(myScreen.charAt(myScreen.length() - 1))) {
                        screen.setText(myScreen.substring(0, myScreen.length() - 1));
                        sign.pop();
                    } else if (myScreen.charAt(myScreen.length() - 1) == '.') {
                        screen.setText(myScreen.substring(myScreen.length() - 1));
                        buffer = buffer.substring(0, buffer.length() - 1);
                    } else if (checkNeg(myScreen.substring(myScreen.length() - 3, myScreen.length())) && (myScreen.length() >= 3)) {
                        if (myScreen.length() == 3) {
                            screen.setText("_________________");
                        } else {
                            screen.setText(myScreen.substring(0, myScreen.length() - 3));
                            buffer = buffer.substring(0, buffer.length() - 1);
                        }
                    }
                    else {
                        if (myScreen.length() == 1) {
                            screen.setText("_________________");
                            if (buffer.length() == 1)
                                buffer = "";
                                else
                                buffer = buffer.substring(0, buffer.length() - 1);
                        }
                        else {
                            screen.setText(myScreen.substring(0, myScreen.length() - 1));
                            if (buffer.length() == 1)
                                buffer = "";
                            else
                                buffer = buffer.substring(0, buffer.length() - 1);
                        }
                    }
                }
            }
        });

        negtive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if(myScreen.compareTo("_________________") == 0) {
                    screen.setText("(-)");
                    buffer += "(-)";
                }
                if(!myScreen.contains("(-)") && myScreen.length() <= 20 && ((myScreen.charAt(myScreen.length()-1) == '+') || (myScreen.charAt(myScreen.length()-1) == '-') || (myScreen.charAt(myScreen.length()-1) == '*') || (myScreen.charAt(myScreen.length()-1) == '/'))){
                    screen.setText(myScreen + "(-)");
                    buffer += "(-)";
                }
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if(myScreen.compareTo("_________________") == 0) {
                    screen.setText("0");
                    buffer += "0";
                }
                if (myScreen.compareTo("0") != 0 && myScreen.length() <= 20) {
                    screen.setText(myScreen + "0");
                    buffer += "0";
                }
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if ((myScreen.compareTo("_________________") != 0)){
                    if(buffer.length() < 3){
                        if ((!buffer.contains(".")) && !checkSign(buffer.charAt(buffer.length() - 1))){
                            screen.setText(myScreen + ".");
                            buffer += ".";
                        }
                    }
                    else{
                        if (!checkNeg(buffer.substring(buffer.length() - 3, buffer.length()))){
                            if ((!buffer.contains(".")) && !checkSign(buffer.charAt(buffer.length() - 1))){
                                screen.setText(myScreen + ".");
                                buffer += ".";
                            }
                        }
                    }
                }
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("1");
                    buffer += "1";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("1");
                    buffer += "1";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "1");
                    buffer += "1";
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("2");
                    buffer += "2";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("2");
                    buffer += "2";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "2");
                    buffer += "2";
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("3");
                    buffer += "3";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("3");
                    buffer += "3";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "3");
                    buffer += "3";
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("4");
                    buffer += "4";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("4");
                    buffer += "4";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "4");
                    buffer += "4";
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("5");
                    buffer += "5";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("5");
                    buffer += "5";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "5");
                    buffer += "5";
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("6");
                    buffer += "6";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("6");
                    buffer += "6";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "6");
                    buffer += "6";
                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("7");
                    buffer += "7";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("7");
                    buffer += "7";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "7");
                    buffer += "7";
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("8");
                    buffer += "8";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("8");
                    buffer += "8";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "8");
                    buffer += "8";
                }
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (myScreen.compareTo("_________________") == 0) {
                    screen.setText("9");
                    buffer += "9";
                }
                else if (myScreen.compareTo("0") == 0) {
                    screen.setText("9");
                    buffer += "9";
                }
                else if (myScreen.length() <= 20) {
                    screen.setText(myScreen + "9");
                    buffer += "9";
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString(); // (-)1
                if (((myScreen.compareTo("_________________") != 0) && (myScreen.compareTo("0") != 0) && !checkSign(myScreen.charAt(myScreen.length()-1)))) {
                    if (myScreen.length() >= 3 && myScreen.substring(myScreen.length() - 2, myScreen.length()) == "(-)") {
                        ;
                    } else {
                        if (checkNeg(buffer)) {
                            buffer = buffer.substring(3, buffer.length());
                            digits.push((-1) * Double.parseDouble(buffer));
                        } else {
                            digits.push(Double.parseDouble(buffer));
                        }
                        //digits.push(Double.parseDouble(buffer));
                        buffer = "";
                        sign.push("+");
                        screen.setText(myScreen + "+");
                    }
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (((myScreen.compareTo("_________________") != 0) && (myScreen.compareTo("0") != 0) && !checkSign(myScreen.charAt(myScreen.length()-1)))) {
                    digits.push(Double.parseDouble(buffer));
                    buffer = "";
                    sign.push("-");
                    screen.setText(myScreen + "-");
                }
            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (((myScreen.compareTo("_________________") != 0) && (myScreen.compareTo("0") != 0) && !checkSign(myScreen.charAt(myScreen.length()-1)))) {
                    digits.push(Double.parseDouble(buffer));
                    buffer = "";
                    sign.push("*");
                    screen.setText(myScreen + "*");
                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myScreen = screen.getText().toString();
                if (((myScreen.compareTo("_________________") != 0) && (myScreen.compareTo("0") != 0) && !checkSign(myScreen.charAt(myScreen.length()-1)))) {
                    digits.push(Double.parseDouble(buffer));
                    buffer = "";
                    sign.push("/");
                    screen.setText(myScreen + "/");
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String myScreen = screen.getText().toString();
                if(checkNeg(buffer)){
                    buffer = buffer.substring(3, buffer.length());
                    digits.push((-1) * Double.parseDouble(buffer));
                }
                else {
                    digits.push(Double.parseDouble(buffer));
                }
                while (!sign.isEmpty()) {
                    op1 = digits.pop();
                    op2 = digits.pop();
                    String thisSign = sign.pop();
                    if (thisSign == "+") {
                        res = op1 + op2;
                        digits.push(res);
                    }
                    if (thisSign == "-") {
                        res = op2 - op1;
                        digits.push(res);
                    }
                    if (thisSign == "*") {
                        res = op2 * op1;
                        digits.push(res);
                    }
                    if (thisSign == "/") {
                        if (op1 != 0.0) {
                            res = op2 / op1;
                            digits.push(res);
                        }
                    }
                }
                if (Double.toString(res).length() >= 10) {
                    result.setText(Double.toString(res).substring(0, 10));
                    result.setText(Double.toString(res).substring(0, 10));
                } else {
                    result.setText(Double.toString(res));
                    screen.setText(Double.toString(res));
                }
                buffer = Double.toString(res);
            }
        });
    }

    public static boolean checkNeg(String buffer){
        if(buffer.contains("(-)")) return true;
        return false;
    }

    public static boolean checkSign(char ch){
        if(ch == '+') return true;
        if(ch == '-') return true;
        if(ch == '*') return true;
        if(ch == '/') return true;
        return false;
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
