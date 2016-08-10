package com.lazztech.thoughtlog;

import android.app.*;
import android.content.*;
import android.database.Cursor;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import java.io.*;

public class LogHistoryActivity extends AppCompatActivity
{
    // GUI controls
    TextView thoughtLogView;
    String line;

    DatabaseHelper myDb;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.loghistory);
        // bind GUI elements with local controls

        myDb = new DatabaseHelper(this);

        thoughtLogView = (TextView) findViewById(R.id.logTextView);
        GetPhoneAddress();

        final TextView tvphone = (TextView) findViewById(R.id.logTextView);
        String saved_phone = GetPhoneAddress();
        if (saved_phone.length()>0) {
            tvphone.setText(saved_phone);
        }
    }

    private String GetPhoneAddress() {

        Cursor res= myDb.getAllData();
        if (res.getCount() == 0) {
            //Show no data message
            Toast.makeText(LogHistoryActivity.this, "No entries in database", Toast.LENGTH_LONG).show();
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID :" + res.getString(0)+"\n");
            buffer.append("Name :" + res.getString(1)+"\n");
            buffer.append("Surname :" + res.getString(2)+"\n");
            buffer.append("Marks :" + res.getString(3)+"\n\n");
        }

        // Show all data


        File directory = Environment.getExternalStorageDirectory();
        File myFile = new File(directory, "mythoughtlog.txt");
        //File file = new File(Environment.getExternalStorageDirectory() + "mythoughtlog.txt");
        if (!myFile.exists()){
            String line = "Complete a new log entry and they will shown here.";
            return line;
        }

        //Read text from file
        //StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(myFile))) {
            String line = br.readLine();
            if (line == null) {
                return null;
            }
            StringBuilder retVal = new StringBuilder(line);
            line = br.readLine();
            while (line != null) {
                retVal.append(System.lineSeparator()).append(line);
                line = br.readLine();
            }
            return retVal.toString();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
        return line;
    }

}
