package com.lazztech.thoughtlog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    Button updatebtn;

    DatabaseHelper myDb;

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
        updatebtn = (Button) findViewById(R.id.updateButton);

        id.setText(iddata);
        datetime.setText(datetimedata);
        situation.setText(situationdata);
        thoughts.setText(thoughtsdata);
        emotions.setText(emotionsdata);
        behavior.setText(behaviordata);
        distortions.setText(distortionsdata);
        altbehavior.setText(altbehaviordata);
        altthoughts.setText(altthoughtsdata);

        UpdateDate();
    }

    public void UpdateDate(){
        updatebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                }
        );
    }
}
