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
    SeekBar seekBar2;
    SeekBar seekBar3;
    SeekBar seekBar4;
    SeekBar seekBar5;
    SeekBar seekBar6;
    SeekBar seekBar7;
    SeekBar seekBar8;
    SeekBar seekBar9;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.phq9);
        // bind GUI elements with local controls
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar3 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar4 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar5 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar6 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar7 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar8 = (SeekBar)findViewById(R.id.seekBar1);
        seekBar9 = (SeekBar)findViewById(R.id.seekBar1);
    }
}
