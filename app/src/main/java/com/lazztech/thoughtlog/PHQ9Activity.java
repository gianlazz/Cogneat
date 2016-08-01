package com.lazztech.thoughtlog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

/**
 * Created by gianlazzarini on 7/31/16.
 */
public class PHQ9Activity extends AppCompatActivity
{

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

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.phq9);
        // bind GUI elements with local controls
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar)findViewById(R.id.seekBar3);
        seekBar4 = (SeekBar)findViewById(R.id.seekBar4);
        seekBar5 = (SeekBar)findViewById(R.id.seekBar5);
        seekBar6 = (SeekBar)findViewById(R.id.seekBar6);
        seekBar7 = (SeekBar)findViewById(R.id.seekBar7);
        seekBar8 = (SeekBar)findViewById(R.id.seekBar8);
        seekBar9 = (SeekBar)findViewById(R.id.seekBar9);
    }
}
