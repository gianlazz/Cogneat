package com.lazztech.thoughtlog;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import java.io.*;

public class LogHistoryActivity extends AppCompatActivity
{
    // GUI controls
    TextView thoughtLogView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.loghistory);
        // bind GUI elements with local controls

        thoughtLogView = (TextView) findViewById(R.id.logTextView);
        GetPhoneAddress();

        final TextView tvphone = (TextView) findViewById(R.id.logTextView);
        String saved_phone = GetPhoneAddress();
        if (saved_phone.length()>0) {
            tvphone.setText(saved_phone);
        }
    }

    private String GetPhoneAddress() {
        File directory = Environment.getExternalStorageDirectory();
        File myFile = new File(directory, "mythoughtlog.txt");
        //File file = new File(Environment.getExternalStorageDirectory() + "mythoughtlog.txt");
        if (!myFile.exists()){
            String line = "Need to add smth";
            return line;
        }
        String line = null;
        //Read text from file
        //StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(myFile));
            line = br.readLine();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
        return line;
    }

}
