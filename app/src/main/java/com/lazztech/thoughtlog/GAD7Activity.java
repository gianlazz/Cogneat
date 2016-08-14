package com.lazztech.thoughtlog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gianlazzarini on 8/4/16.
 */
public class GAD7Activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar seekBar1;
    int seekBar1value;        //The SeekBar value output
    SeekBar seekBar2;
    int seekBar2value;        //The SeekBar value output
    SeekBar seekBar3;
    int seekBar3value;        //The SeekBar value output
    SeekBar seekBar4;
    int seekBar4value;        //The SeekBar value output
    SeekBar seekBar5;
    int seekBar5value;        //The SeekBar value output
    SeekBar seekBar6;
    int seekBar6value;        //The SeekBar value output
    SeekBar seekBar7;
    int seekBar7value;        //The SeekBar value output
    SeekBar seekBar8;
    int seekBar8value;        //The SeekBar value output
    SeekBar seekBar9;
    int seekBar9value;        //The SeekBar value output

    EditText situation;

    int totalScoreInt;

    String Date = "Date: ";

    TextView result;
    TextView diagnosis;
    Button SaveScore;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.gad7);
        // bind GUI elements with local controls
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar)findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar)findViewById(R.id.seekBar5);
        seekBar6 = (SeekBar)findViewById(R.id.seekBar6);
        seekBar7 = (SeekBar)findViewById(R.id.seekBar7);

        //set change listener
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
        seekBar4.setOnSeekBarChangeListener(this);
        seekBar5.setOnSeekBarChangeListener(this);
        seekBar6.setOnSeekBarChangeListener(this);
        seekBar7.setOnSeekBarChangeListener(this);

        situation = (EditText) findViewById(R.id.situation);

        result = (TextView)findViewById(R.id.totalScore);
        diagnosis = (TextView)findViewById(R.id.diagnosis);
        SaveScore = (Button)findViewById(R.id.SaveDepressionScore);

        View.OnClickListener saveListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Put up the Yes/No message box
                AlertDialog.Builder builder = new AlertDialog.Builder(GAD7Activity.this);
                builder
                        .setTitle("Are you sure you're finished?")
                        .setMessage("Touch \"YES\" to save.")
                                //.setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // write on SD card file data in the text box

                                Intent intent = new Intent(GAD7Activity.this, MainActivity.class);
                                startActivity(intent);

                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mma");
                                    String currentDateandTime = sdf.format(new Date());
                                    Date += " " + currentDateandTime;



                            }
                        })
                        .setNegativeButton("No", null)                        //Do nothing on no
                        .show();

                //Close onClick
            }
            // End OnClickListener
        };

        //setOnClickListener(saveListener) to button variable connected to R.id.SaveDepressionScore in layout xml
        SaveScore.setOnClickListener(saveListener);

        //Close onCreate
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        //Gets the progress of all of the SeekBars and stores it into the integer variable totalScoreInt
        totalScoreInt = seekBar1.getProgress()
                + seekBar2.getProgress()
                + seekBar3.getProgress()
                + seekBar4.getProgress()
                + seekBar5.getProgress()
                + seekBar6.getProgress()
                + seekBar7.getProgress();

        //Converts totalScoreInt integer into string and sets it to "result"
        result.setText(String.valueOf("Score: " +totalScoreInt));

        if (totalScoreInt >= 4){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: "));
        }
        if (totalScoreInt >= 5 && totalScoreInt <= 9 ){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: " + "Mild"));
        }
        if (totalScoreInt >= 10 && totalScoreInt <= 14){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: " + "Moderate"));
        }
        if (totalScoreInt >= 15){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: " + "Severe"));
        }
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
    }
}
