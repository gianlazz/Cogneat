package com.lazztech.thoughtlog;

import android.app.*;
import android.content.*;
import android.database.Cursor;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.widget.SimpleCursorAdapter;
import java.io.*;

public class LogHistoryActivity extends AppCompatActivity
{
    // GUI controls
    TextView thoughtLogView;
    String line;
    ListView dblist;


    DatabaseHelper myDb;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.loghistory);
        // bind GUI elements with local controls

        dblist = (ListView) findViewById(R.id.listViewTasks);

        myDb = new DatabaseHelper(this);

        viewAll();

        populateListView();
        listViewItemClick();
    }

    public void viewAll() {
    Cursor res= myDb.getAllData();
    if (res.getCount() == 0) {
        //Show no data message
        showMessage("Error", "Nothing found");
    }

}

    private void populateListView(){
        Cursor cursor = myDb.getAllData();
        String[] fromFieldNames = new String[] {DatabaseHelper.COL_1,DatabaseHelper.COL_2};
        int[] toViewIDs = new int[] {R.id.textViewItemNumber, R.id.textViewItemSituation,};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.item_layout, cursor, fromFieldNames, toViewIDs, 0);
        ListView myList = (ListView) findViewById(R.id.listViewTasks);
        myList.setAdapter(myCursorAdapter);
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void listViewItemClick(){
        dblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) dblist.getItemAtPosition(position);
                String getid = cursor.getString(0);
                String getsituation = cursor.getString(1);
                String getthoughts = cursor.getString(2);
                String getemotions = cursor.getString(3);
                String getbehaviors = cursor.getString(4);
                String getdistortions = cursor.getString(5);
                String getaltbehaviors = cursor.getString(6);
                String getaltthoughts = cursor.getString(7);

                Intent intent = new Intent(LogHistoryActivity.this, AllDBRow.class);
                intent.putExtra("id", getid);
                intent.putExtra("situation", getsituation);
                intent.putExtra("thoughts", getthoughts);
                intent.putExtra("emotions", getemotions);
                intent.putExtra("behaviors", getbehaviors);
                intent.putExtra("distortions", getdistortions);
                intent.putExtra("altbehaviors", getaltbehaviors);
                intent.putExtra("altthoughts", getaltthoughts);
                startActivity(intent);
            }
        });
    }

}
