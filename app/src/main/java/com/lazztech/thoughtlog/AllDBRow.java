package com.lazztech.thoughtlog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by gianlazzarini on 8/13/16.
 */

public class AllDBRow extends AppCompatActivity {
    /** Called when the activity is first created. */

    TextView id;
    TextView datetime;
    EditText situation;
    EditText thoughts;
    EditText emotions;
    EditText behavior;
    EditText distortions;
    EditText altbehavior;
    EditText altthoughts;

    DatabaseHelper myDb;

    private KeyListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.alldbrow);
        // bind GUI elements with local controls

        myDb = new DatabaseHelper(this);
        String iddata = getIntent().getExtras().getString("id");
        String datetimedata = getIntent().getExtras().getString("datetime");
        String situationdata = getIntent().getExtras().getString("situation");
        String thoughtsdata = getIntent().getExtras().getString("thoughts");
        String emotionsdata = getIntent().getExtras().getString("emotions");
        String behaviordata = getIntent().getExtras().getString("behaviors");
        String distortionsdata = getIntent().getExtras().getString("distortions");
        String altbehaviordata = getIntent().getExtras().getString("altbehaviors");
        String altthoughtsdata = getIntent().getExtras().getString("altthoughts");

        id = (TextView) findViewById(R.id.dbidTextView);
        datetime = (TextView) findViewById(R.id.datetimeTextView);
        situation = (EditText) findViewById(R.id.situationTextView);
        thoughts = (EditText) findViewById(R.id.thoughtsTextView);
        emotions = (EditText) findViewById(R.id.emotionsTextView);
        behavior = (EditText) findViewById(R.id.behaviorTextView);
        distortions = (EditText) findViewById(R.id.distortionsTextView);
        altbehavior = (EditText) findViewById(R.id.altbehaviorTextView);
        altthoughts = (EditText) findViewById(R.id.altthoughsTextView);

        listener = situation.getKeyListener();
        listener = thoughts.getKeyListener();
        listener = emotions.getKeyListener();

        id.setText(iddata);
        datetime.setText(datetimedata);
        situation.setText(situationdata);
        thoughts.setText(thoughtsdata);
        emotions.setText(emotionsdata);
        behavior.setText(behaviordata);
        distortions.setText(distortionsdata);
        altbehavior.setText(altbehaviordata);
        altthoughts.setText(altthoughtsdata);

        NotEditable();
    }

    public void Editable(){
        situation.setKeyListener(listener);
        thoughts.setKeyListener(listener);
        emotions.setKeyListener(listener);
        behavior.setKeyListener(listener);
        altbehavior.setKeyListener(listener);
        altthoughts.setKeyListener(listener);
    }

    public void NotEditable(){
        situation.setKeyListener(null);
        thoughts.setKeyListener(null);
        emotions.setKeyListener(null);
        behavior.setKeyListener(null);
        distortions.setKeyListener(null);
        altbehavior.setKeyListener(null);
        altthoughts.setKeyListener(null);
    }

    public void DeleteData(){
                        Integer deletedRows = myDb.deleteData(id.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(AllDBRow.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AllDBRow.this, "Data Not Deleted!", Toast.LENGTH_LONG).show();
                    }

    public void AreYouSureDelete(){
        //Put up the Yes/No message box
        AlertDialog.Builder builder = new AlertDialog.Builder(AllDBRow.this);
        builder
                .setTitle("Warning!")
                .setMessage("Are you sure you want to save your changes?")
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteData();
                        Intent intent = new Intent(AllDBRow.this, LogHistoryActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)	//Do nothing on no
                .show();
    }

    public void AreYouSureUpdate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AllDBRow.this);
        builder
                .setTitle("Warning!")
                .setMessage("Are you sure you want to save your changes?")
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        UpdateData();
                        Intent intent = new Intent(AllDBRow.this, LogHistoryActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)	//Do nothing on no
                .show();
    }

    public void UpdateData(){
                        boolean isUpdated = myDb.updateData(id.getText().toString(),
                                datetime.getText().toString(),
                                situation.getText().toString(),
                                thoughts.getText().toString(),
                                emotions.getText().toString(),
                                behavior.getText().toString(),
                                distortions.getText().toString(),
                                altbehavior.getText().toString(),
                                altthoughts.getText().toString());
                        if(isUpdated == true)
                            Toast.makeText(AllDBRow.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AllDBRow.this, "Data Not Updated!", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate main_menu.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alldbrow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuDelete:
                AreYouSureDelete();
                return true;
            case R.id.menuEdit:
                Editable();
                Toast.makeText(AllDBRow.this, "Don't forget to save your changes!", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuSave:
                AreYouSureUpdate();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
