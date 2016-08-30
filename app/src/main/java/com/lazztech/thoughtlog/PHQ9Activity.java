package com.lazztech.thoughtlog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gianlazzarini on 7/31/16.
 */
public class PHQ9Activity extends AppCompatActivity implements OnSeekBarChangeListener
{

    SeekBar seekBar1;
    TextView seekBar1value;        //The SeekBar value output
    TextView seekBar1result;
    SeekBar seekBar2;
    TextView seekBar2value;        //The SeekBar value output
    TextView seekBar2result;
    SeekBar seekBar3;
    TextView seekBar3value;        //The SeekBar value output
    TextView seekBar3result;
    SeekBar seekBar4;
    TextView seekBar4value;        //The SeekBar value output
    TextView seekBar4result;
    SeekBar seekBar5;
    TextView seekBar5value;        //The SeekBar value output
    TextView seekBar5result;
    SeekBar seekBar6;
    TextView seekBar6value;        //The SeekBar value output
    TextView seekBar6result;
    SeekBar seekBar7;
    TextView seekBar7value;        //The SeekBar value output
    TextView seekBar7result;
    SeekBar seekBar8;
    TextView seekBar8value;        //The SeekBar value output
    TextView seekBar8result;
    SeekBar seekBar9;
    TextView seekBar9value;        //The SeekBar value output
    TextView seekBar9result;
    EditText situation;

    int totalScoreInt;

    String Zero = "Not At All";
    String One = "Several Days";
    String Two = "More Than Half the Days";
    String Three = "Nearly Every Day";

    String Date = "Date: ";

    TextView result;
    TextView diagnosis;
    Button SaveScore;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.phq9);
        // bind GUI elements with local controls
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar1value = (TextView)findViewById(R.id.seekbar1_value);
        seekBar1result = (TextView)findViewById(R.id.seekbar1_result);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBar2value = (TextView)findViewById(R.id.seekbar2_value);
        seekBar2result = (TextView)findViewById(R.id.seekbar2_result);
        seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
        seekBar3value = (TextView)findViewById(R.id.seekbar3_value);
        seekBar3result = (TextView)findViewById(R.id.seekbar3_result);
        seekBar4 = (SeekBar)findViewById(R.id.seekBar4);
        seekBar4value = (TextView)findViewById(R.id.seekbar4_value);
        seekBar4result = (TextView)findViewById(R.id.seekbar4_result);
        seekBar5 = (SeekBar)findViewById(R.id.seekBar5);
        seekBar5value = (TextView)findViewById(R.id.seekbar5_value);
        seekBar5result = (TextView)findViewById(R.id.seekbar5_result);
        seekBar6 = (SeekBar)findViewById(R.id.seekBar6);
        seekBar6value = (TextView)findViewById(R.id.seekbar6_value);
        seekBar6result = (TextView)findViewById(R.id.seekbar6_result);
        seekBar7 = (SeekBar)findViewById(R.id.seekBar7);
        seekBar7value = (TextView)findViewById(R.id.seekbar7_value);
        seekBar7result = (TextView)findViewById(R.id.seekbar7_result);
        seekBar8 = (SeekBar)findViewById(R.id.seekBar8);
        seekBar8value = (TextView)findViewById(R.id.seekbar8_value);
        seekBar8result = (TextView)findViewById(R.id.seekbar8_result);
        seekBar9 = (SeekBar)findViewById(R.id.seekBar9);
        seekBar9value = (TextView)findViewById(R.id.seekbar9_value);
        seekBar9result = (TextView)findViewById(R.id.seekbar9_result);

        //set change listener
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
        seekBar4.setOnSeekBarChangeListener(this);
        seekBar5.setOnSeekBarChangeListener(this);
        seekBar6.setOnSeekBarChangeListener(this);
        seekBar7.setOnSeekBarChangeListener(this);
        seekBar8.setOnSeekBarChangeListener(this);
        seekBar9.setOnSeekBarChangeListener(this);

        situation = (EditText) findViewById(R.id.situation);

        result = (TextView)findViewById(R.id.totalScore);
        diagnosis = (TextView)findViewById(R.id.diagnosis);
        SaveScore = (Button)findViewById(R.id.SaveDepressionScore);

        View.OnClickListener saveListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Put up the Yes/No message box
                AlertDialog.Builder builder = new AlertDialog.Builder(PHQ9Activity.this);
                builder
                        .setTitle("Are you sure you're finished?")
                        .setMessage("Touch \"YES\" to save.")
                                //.setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // write on SD card file data in the text box
                                try {
                                    File directory = Environment.getExternalStorageDirectory();
                                    File myFile = new File(directory, "mythoughtlog.txt");

                                    // Check if the file already exists so you don't keep creating
                                    if (!myFile.exists()) {
                                        //Log.i(TAG, "Creating the file as it doesn't exist already");
                                        myFile.createNewFile();
                                    }

                                    // Open the FileoutputStream
                                    FileOutputStream fOut = new FileOutputStream(myFile, true);

                                    // Open the printStream to allow for Strings to be written
                                    PrintStream printStream = new PrintStream(fOut);

                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mma");
                                    String currentDateandTime = sdf.format(new Date());
                                    Date += " " + currentDateandTime;


                                    // Using a stringBuffer to append all the values to
                                    StringBuffer stringBuffer = new StringBuffer();
                                    stringBuffer.append(Date);
                                    stringBuffer.append('\n');
                                    stringBuffer.append(String.valueOf("PHQ-9 Score: " +totalScoreInt));
                                    stringBuffer.append('\n');
                                    stringBuffer.append(diagnosis.getText());
                                    stringBuffer.append('\n');
                                    stringBuffer.append("Situation: " + situation.getText());
                                    stringBuffer.append('\n');
                                    stringBuffer.append('\n');


                                    // Print the stringBuffer to the file
                                    printStream.print(stringBuffer.toString());

                                    // Close everything out
                                    printStream.close();
                                    fOut.close();
                                    Toast.makeText(getBaseContext(),
                                            "Saved to 'mythoughtlog.txt'",
                                            Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getBaseContext(), e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
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

    public void questionScore(){
        if (seekBar1.getProgress() == 0){
            seekBar1result.setText(Zero);
        }
        if (seekBar1.getProgress() == 1){
            seekBar1result.setText(One);
        }
        if (seekBar1.getProgress() == 2){
            seekBar1result.setText(Two);
        }
        if (seekBar1.getProgress() == 3){
            seekBar1result.setText(Three);
        }

        if (seekBar2.getProgress() == 0){
            seekBar2result.setText(Zero);
        }
        if (seekBar2.getProgress() == 1){
            seekBar2result.setText(One);
        }
        if (seekBar2.getProgress() == 2){
            seekBar2result.setText(Two);
        }
        if (seekBar2.getProgress() == 3){
            seekBar2result.setText(Three);
        }

        if (seekBar3.getProgress() == 0){
            seekBar3result.setText(Zero);
        }
        if (seekBar3.getProgress() == 1){
            seekBar3result.setText(One);
        }
        if (seekBar3.getProgress() == 2){
            seekBar3result.setText(Two);
        }
        if (seekBar3.getProgress() == 3){
            seekBar3result.setText(Three);
        }

        if (seekBar4.getProgress() == 0){
            seekBar4result.setText(Zero);
        }
        if (seekBar4.getProgress() == 1){
            seekBar4result.setText(One);
        }
        if (seekBar4.getProgress() == 2){
            seekBar4result.setText(Two);
        }
        if (seekBar4.getProgress() == 3){
            seekBar4result.setText(Three);
        }

        if (seekBar5.getProgress() == 0){
            seekBar5result.setText(Zero);
        }
        if (seekBar5.getProgress() == 1){
            seekBar5result.setText(One);
        }
        if (seekBar5.getProgress() == 2){
            seekBar5result.setText(Two);
        }
        if (seekBar5.getProgress() == 3){
            seekBar5result.setText(Three);
        }

        if (seekBar6.getProgress() == 0){
            seekBar6result.setText(Zero);
        }
        if (seekBar6.getProgress() == 1){
            seekBar6result.setText(One);
        }
        if (seekBar6.getProgress() == 2){
            seekBar6result.setText(Two);
        }
        if (seekBar6.getProgress() == 3){
            seekBar6result.setText(Three);
        }

        if (seekBar7.getProgress() == 0){
            seekBar7result.setText(Zero);
        }
        if (seekBar7.getProgress() == 1){
            seekBar7result.setText(One);
        }
        if (seekBar7.getProgress() == 2){
            seekBar7result.setText(Two);
        }
        if (seekBar7.getProgress() == 3){
            seekBar7result.setText(Three);
        }

        if (seekBar8.getProgress() == 0){
            seekBar8result.setText(Zero);
        }
        if (seekBar8.getProgress() == 1){
            seekBar8result.setText(One);
        }
        if (seekBar8.getProgress() == 2){
            seekBar4result.setText(Two);
        }
        if (seekBar8.getProgress() == 3){
            seekBar8result.setText(Three);
        }

        if (seekBar9.getProgress() == 0){
            seekBar9result.setText(Zero);
        }
        if (seekBar9.getProgress() == 1){
            seekBar9result.setText(One);
        }
        if (seekBar9.getProgress() == 2){
            seekBar9result.setText(Two);
        }
        if (seekBar9.getProgress() == 3){
            seekBar9result.setText(Three);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        questionScore();

        seekBar1value.setText(Integer.toString(seekBar1.getProgress()));
        seekBar2value.setText(Integer.toString(seekBar2.getProgress()));
        seekBar3value.setText(Integer.toString(seekBar3.getProgress()));
        seekBar4value.setText(Integer.toString(seekBar4.getProgress()));
        seekBar5value.setText(Integer.toString(seekBar5.getProgress()));
        seekBar6value.setText(Integer.toString(seekBar6.getProgress()));
        seekBar7value.setText(Integer.toString(seekBar7.getProgress()));
        seekBar8value.setText(Integer.toString(seekBar8.getProgress()));
        seekBar9value.setText(Integer.toString(seekBar9.getProgress()));

        //Gets the progress of all of the SeekBars and stores it into the integer variable totalScoreInt
        totalScoreInt = seekBar1.getProgress()
                        + seekBar2.getProgress()
                        + seekBar3.getProgress()
                        + seekBar4.getProgress()
                        + seekBar5.getProgress()
                        + seekBar6.getProgress()
                        + seekBar7.getProgress()
                        + seekBar8.getProgress()
                        + seekBar9.getProgress();

        //Converts totalScoreInt integer into string and sets it to "result"
        result.setText(String.valueOf("Score: " +totalScoreInt));

        if (totalScoreInt >= 4){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: "));
        }
        if (totalScoreInt >= 5 && totalScoreInt <= 9 ){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: " + "Minimal Symptoms*"));
        }
        if (totalScoreInt >= 10 && totalScoreInt <= 14){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: " + "Minor depression ++ Dysthymia*\n" +
                    "Major Depression, mild"));
        }
        if (totalScoreInt >= 15 && totalScoreInt <= 19){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: " + "Major depression, moderately severe"));
        }
        if (totalScoreInt >=20){
            diagnosis.setText(String.valueOf("Provisional Diagnosis: " + "Major Depression, severe"));
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
