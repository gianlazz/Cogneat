package com.lazztech.thoughtlog;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import android.content.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.SearchView.*;

public class NewLogActivity extends Activity
{
	// GUI controls
	EditText txtData;
	EditText situation;
	EditText thoughts;
	EditText emotions;
	EditText behavior;
	CheckBox fortunetelling;
	CheckBox mindreading;
	CheckBox labeling;
	CheckBox filtering;
	CheckBox overestimating;
	CheckBox catastrophizing;
	CheckBox overgeneralizing;
	StringBuffer distortions;
	EditText altbehavior;
	EditText altthoughts;
	Button btnWriteSDFile;
	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//Set NewLogActivity.xml as user interface layout
		setContentView(R.layout.newlog);
		// bind GUI elements with local controls
		txtData = (EditText) findViewById(R.id.txtData);
		situation = (EditText) findViewById(R.id.situation);
		thoughts = (EditText) findViewById(R.id.thoughts);
		emotions = (EditText) findViewById(R.id.emotions);
		behavior = (EditText) findViewById(R.id.behavior);
		
		fortunetelling = (CheckBox) findViewById(R.id.fortunetellingCheckBox1);
		mindreading = (CheckBox) findViewById(R.id.mindreadingCheckBox2);
		labeling = (CheckBox) findViewById(R.id.labelingCheckBox3);
		filtering = (CheckBox) findViewById(R.id.filteringCheckBox4);
		overestimating = (CheckBox) findViewById(R.id.overestimatingCheckBox5);
		catastrophizing = (CheckBox) findViewById(R.id.catastrophizingCheckBox6);
		overgeneralizing = (CheckBox) findViewById(R.id.overgeneralizingCheckBox7);
		
		altbehavior = (EditText) findViewById(R.id.altbehaviors);
		altthoughts = (EditText) findViewById(R.id.altthoughts);

		btnWriteSDFile = (Button) findViewById(R.id.btnWriteSDFile);
	}
		
		//Checkbox onclick actions
	public void onCheckboxClicked(View view) 
	{

		// Is the view now checked?
		boolean checked = ((CheckBox) view).isChecked();

		// Check which checkbox was clicked
		switch(view.getId()) {
			case R.id.fortunetellingCheckBox1:
				if (checked)
				distortions.append("Fortune-Telling").toString();
				break;
			//case R.id.mindreadingCheckBox2:
				//if (checked)
                // Append "Mind-Reading" to String distortions
				//break;
				// TODO: Veggie sandwi
		}
		btnWriteSDFile.setOnClickListener(new OnClickListener() {
			
				public void onClick(View v) {
					// write on SD card file data in the text box
					try {
						File myFile = new File("/sdcard/mythoughtlog.txt");
						myFile.createNewFile();
						FileOutputStream fOut = new FileOutputStream(myFile, true);
						
						//Open the printStream to allow for Strings to be written
						PrintStream printStream = new PrintStream(fOut);
						
						
						StringBuffer stringBuffer = new StringBuffer();
						stringBuffer.append(txtData.getText());
						stringBuffer.append('\n');
						stringBuffer.append(situation.getText());
						stringBuffer.append('\n');
						stringBuffer.append(thoughts.getText());
						stringBuffer.append('\n');
						stringBuffer.append(emotions.getText());
						stringBuffer.append('\n');
						stringBuffer.append(behavior.getText());
						stringBuffer.append('\n');
						stringBuffer.append("Distortions: ");
						stringBuffer.append(distortions);
						stringBuffer.append('\n');
						stringBuffer.append(altbehavior.getText());
						stringBuffer.append('\n');
						stringBuffer.append(altthoughts.getText());
						stringBuffer.append('\n');
						printStream.print(stringBuffer.toString());
						printStream.close();
						fOut.close();
						txtData.setText("Name: "); //ClearScreen
						situation.setText("Situation: ");
						thoughts.setText("Thoughts: ");
						emotions.setText("Emotions: ");
						behavior.setText("Behavior: ");
						altbehavior.setText("Alternative Behavior: ");
						altthoughts.setText("Alternative Thoughts: ");
						Toast.makeText(getBaseContext(),
									   "Saved to 'mythoughtlog.txt'",
									   Toast.LENGTH_SHORT).show();
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getBaseContext(), e.getMessage(),
									   Toast.LENGTH_SHORT).show();
					}
					
					
			}//Save onClick
		});// btnWriteSDFile
	}
}// AndSDcard
	
		
		
		


	
		

		
