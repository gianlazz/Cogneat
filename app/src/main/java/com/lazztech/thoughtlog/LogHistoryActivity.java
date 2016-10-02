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
    Button thoughtLogButton;
    Button GAD7Button;
    Button PHQ9Button;



    DatabaseHelper myDb;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.loghistory);
        // bind GUI elements with local controls

        thoughtLogButton = (Button) findViewById(R.id.Thought_Log_button);
        GAD7Button = (Button) findViewById(R.id.GAD7_button);
        PHQ9Button = (Button) findViewById(R.id.PHQ_button);
        dblist = (ListView) findViewById(R.id.listViewTasks);

        myDb = new DatabaseHelper(this);

        viewAllThoughtLog();
        populateListViewThoughtLog();
        thoughtLogListViewItemClick();

        anxietyButton();
        thoughtLogButton();
        depressionButton();
    }

    public void viewAllThoughtLog() {
    Cursor res= myDb.getAllThoughtLogData();
    if (res.getCount() == 0) {
        //Show no data message
        showMessage("Error", "Nothing found");
    }
}

    public void viewAllGAD7Data() {
        Cursor res = myDb.getAllGAD7Data();
        if (res.getCount() == 0) {
            //Show no data message
            showMessage("Error", "Nothing found");
        }
    }

    public void viewAllPHQ9Data() {
        Cursor res = myDb.getAllPHQ9Data();
        if (res.getCount() == 0) {
            //Show no data message
            showMessage("Error", "Nothing found");
        }
    }

    private void populateListViewThoughtLog(){
        Cursor cursor = myDb.getAllThoughtLogData();
        String[] fromFieldNames = new String[] {DatabaseHelper.COL_1,DatabaseHelper.COL_2, DatabaseHelper.COL_3};
        int[] toViewIDs = new int[] {R.id.textViewItemNumber,R.id.textViewDatetime, R.id.textViewItemSituation,};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.item_layout, cursor, fromFieldNames, toViewIDs, 0);
        ListView myList = (ListView) findViewById(R.id.listViewTasks);
        myList.setAdapter(myCursorAdapter);
    }

    public void thoughtLogButton(){
        thoughtLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListViewThoughtLog();
                thoughtLogListViewItemClick();
            }
        });
    }

    public void anxietyButton(){
        GAD7Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllGAD7Data();
                populateListViewGAD7();
                anxietyListViewItemClick();
            }
        });
    }

    public void depressionButton(){
        PHQ9Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAllPHQ9Data();
                populateListViewPHQ9();
                depressionListViewItemClick();
            }
        });
    }

    private void populateListViewGAD7(){
        Cursor cursor = myDb.getAllGAD7Data();
        String[] fromFieldNames = new String[] {DatabaseHelper.GAD7_COL_2,DatabaseHelper.GAD7_COL_3, DatabaseHelper.GAD7_COL_4};
        int[] toViewIDs = new int[] {R.id.textViewItemNumber,R.id.textViewDatetime, R.id.textViewItemSituation,};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.item_layout, cursor, fromFieldNames, toViewIDs, 0);
        ListView myList = (ListView) findViewById(R.id.listViewTasks);
        myList.setAdapter(myCursorAdapter);
    }

    private void populateListViewPHQ9(){
        Cursor cursor = myDb.getAllPHQ9Data();
        String[] fromFieldNames = new String[] {DatabaseHelper.PHQ9_COL_2,DatabaseHelper.PHQ9_COL_3, DatabaseHelper.PHQ9_COL_4};
        int[] toViewIDs = new int[] {R.id.textViewItemNumber,R.id.textViewDatetime, R.id.textViewItemSituation,};
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

    public void thoughtLogListViewItemClick(){
        dblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) dblist.getItemAtPosition(position);
                String getid = cursor.getString(0);
                String getdatetime = cursor.getString(1);
                String getsituation = cursor.getString(2);
                String getthoughts = cursor.getString(3);
                String getemotions = cursor.getString(4);
                String getbehaviors = cursor.getString(5);
                String getdistortions = cursor.getString(6);
                String getaltbehaviors = cursor.getString(7);
                String getaltthoughts = cursor.getString(8);

                Intent intent = new Intent(LogHistoryActivity.this, AllDBRow.class);
                intent.putExtra("id", getid);
                intent.putExtra("datetime", getdatetime);
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

    public void anxietyListViewItemClick(){
        dblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    public void depressionListViewItemClick(){
        dblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate main_menu.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.loghistory_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.old_log:
                Toast.makeText(this, "Developed by Gian Lazzarini.", Toast.LENGTH_LONG).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}
