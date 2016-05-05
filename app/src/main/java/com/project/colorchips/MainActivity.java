package com.project.colorchips;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity implements OnClickListener {

    static  int i=0;

    public String getInputstring() {
        return inputstring;
    }
    public void setInputstring(String inputstring) {
        this.inputstring = inputstring;
    }
    private String inputstring ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button RedButton=(Button)findViewById(R.id.button1);
        RedButton.setOnClickListener(this); // calling onClick() method

        Button yellowButton=(Button)findViewById(R.id.button2);
        yellowButton.setOnClickListener(this); // calling onClick() method

        Button orangeButton=(Button)findViewById(R.id.button3);
        orangeButton.setOnClickListener(this); // calling onClick() method

        Button blueButton=(Button)findViewById(R.id.button4);
        blueButton.setOnClickListener(this); // calling onClick() method

        Button greenButton=(Button)findViewById(R.id.button5);
        greenButton.setOnClickListener(this); // calling onClick() method

        Button checkButton=(Button)findViewById(R.id.submit);
        checkButton.setOnClickListener(this); // calling onClick() method

        Button clearButton=(Button)findViewById(R.id.clear);
        clearButton.setOnClickListener(this); // calling onClick() method
    }

    @Override
    public void onClick(View v) {
        final TextView display=(TextView)findViewById(R.id.displayText);
        final TextView resultdisplay=(TextView)findViewById(R.id.resultText);
        Button b = (Button)v;

        switch (v.getId()) {

            case R.id.button1:
                btnInput(b.getText().toString());
                break;

            case R.id.button2:
                 btnInput(b.getText().toString());
                break;

            case R.id.button3:
                 btnInput(b.getText().toString());
                break;
            case R.id.button4:
                 btnInput(b.getText().toString());
                break;
            case R.id.button5:
                 btnInput(b.getText().toString());
                break;

            case R.id.submit:

                if (i % 2 != 0) {

                    display.setText("Please click the other color of the chip then click the Check");


                } else {
                    Unlockchips unlockchips = new Unlockchips();

                    ChipsInput input = buildInput(inputstring .split(","));
                    unlockchips.unlock(input);
                    String Result=unlockchips.getResult().toString();
                    if(Result.equals("Cannot unlock master panel"))
                        display.setText(unlockchips.getResult().toString());

                    else
                    {
                        resultdisplay.setText(Result);

                        display.setText("Unlocked the Panel   ");

                    }
                }
                break;
            case R.id.clear:
                display.setText("Cleared ! Enter the Starting Color of the MasterPanel");
                i=0;
                inputstring="";

                TextView myEditText = ((TextView) findViewById(R.id.resultText));
                myEditText.setText("");
                break;
            default:
                break;
        }

    }

    private void btnInput(String btnColor){
        final TextView display=(TextView)findViewById(R.id.displayText);
        if (i == 0) {
            inputstring = btnColor;
            display.setText("Enter the End Color of the MasterPanel");
            i++;

        } else if (i == 1) {

            inputstring += ","+btnColor;

            display.setText("Now start entering the Chips for the Panel Once Done Click Check");
            i++;
        } else {
            inputstring += ","+btnColor;
            i++;
        }
    }

    private static ChipsInput buildInput(String... colors) {
        ChipsInput input = new ChipsInput();
        input.parse(buildPairs(colors));
        return input;
    }

    private static  String buildPairs(String... colors) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < colors.length; i++){
            sb.append(colors[i]);
            // separating chips based on position
            if(i % 2 == 0){
                sb.append(",");
            } else {
                sb.append("\n");
            }
        }
        return sb.toString().trim();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
