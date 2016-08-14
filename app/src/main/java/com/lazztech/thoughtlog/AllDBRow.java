package com.lazztech.thoughtlog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by gianlazzarini on 8/13/16.
 */

public class AllDBRow extends AppCompatActivity {
    /** Called when the activity is first created. */

    TextView id;
    TextView situation;
    TextView thoughts;
    TextView emotions;
    TextView behavior;
    TextView distortions;
    TextView altbehavior;
    TextView altthoughts;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //Set MainActivity.xml as user interface layout
        setContentView(R.layout.alldbrow);
        // bind GUI elements with local controls
        DatabaseHelper myDb;

        String iddata = getIntent().getExtras().getString("id");
        String situationdata = getIntent().getExtras().getString("situation");
        String thoughtsdata = getIntent().getExtras().getString("thoughts");
        String emotionsdata = getIntent().getExtras().getString("emotions");
        String behaviordata = getIntent().getExtras().getString("behaviors");
        String distortionsdata = getIntent().getExtras().getString("distortions");
        String altbehaviordata = getIntent().getExtras().getString("altbehaviors");
        String altthoughtsdata = getIntent().getExtras().getString("altthoughts");

        id = (TextView) findViewById(R.id.dbidTextView);
        situation = (TextView) findViewById(R.id.situationTextView);
        thoughts = (TextView) findViewById(R.id.thoughtsTextView);
        emotions = (TextView) findViewById(R.id.emotionsTextView);
        behavior = (TextView) findViewById(R.id.behaviorTextView);
        distortions = (TextView) findViewById(R.id.distortionsTextView);
        altbehavior = (TextView) findViewById(R.id.altbehaviorTextView);
        altthoughts = (TextView) findViewById(R.id.altthoughsTextView);

        id.setText("ID: " + iddata);
        situation.setText("Situation: " + situationdata);
        thoughts.setText("Thoughts: " + thoughtsdata);
        emotions.setText("Emotions: " + emotionsdata);
        behavior.setText("Behavior: " + behaviordata);
        distortions.setText("Distortions: " + distortionsdata);
        altbehavior.setText("Alt Behavior: " + altbehaviordata);
        altthoughts.setText("Alt Thoughts: " +  altthoughtsdata);

    }
}
