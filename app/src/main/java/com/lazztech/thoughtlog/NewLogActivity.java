package com.lazztech.thoughtlog;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.content.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.SearchView.*;

public class NewLogActivity extends AppCompatActivity
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
	CheckBox blackandwhitethinking;

	List<String> distortions = new ArrayList<String>();
	String fortuneTellingString = "Fortune-Telling";
	String mindReadingString = "Mind-Reading";
	String labelingString = "Labeling";
	String filteringString = "Filtering";
	String overestimatingString = "Overestimating";
	String catastrophizingString = "Catastrophizing";
	String overgeneralizingString = "Overgeneralizing";
	String blackandwhitethinkingString = "Black and White Thinking";

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

		final TextInputLayout nameWrapper = (TextInputLayout) findViewById(R.id.nameWrapper);
		nameWrapper.setHint("Name:");

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
		blackandwhitethinking = (CheckBox) findViewById(R.id.blackandwhitethinkingCheckBox8);

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
					distortions.add(fortuneTellingString);
				else
					distortions.remove(fortuneTellingString);
				break;
			case R.id.mindreadingCheckBox2:
				if (checked)
                 	distortions.add(mindReadingString);
				else
					distortions.remove(mindReadingString);
				break;
			case R.id.labelingCheckBox3:
				if (checked)
					distortions.add(labelingString);
				else
					distortions.remove(labelingString);
				break;
			case R.id.filteringCheckBox4:
				if (checked)
					distortions.add(filteringString);
				else
					distortions.remove(filteringString);
				break;
			case R.id.overestimatingCheckBox5:
				if (checked)
					distortions.add(overestimatingString);
				else
					distortions.remove(overestimatingString);
				break;
			case R.id.catastrophizingCheckBox6:
				if (checked)
					distortions.add(catastrophizingString);
				else
					distortions.remove(catastrophizingString);
				break;
			case R.id.overgeneralizingCheckBox7:
				if (checked)
					distortions.add(overgeneralizingString);
				else
					distortions.remove(overestimatingString);
				break;
			case R.id.blackandwhitethinkingCheckBox8:
				if (checked)
					distortions.add(blackandwhitethinkingString);
				else
					distortions.remove(blackandwhitethinkingString);
				break;
		}
		btnWriteSDFile.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					//Put up the Yes/No message box
					AlertDialog.Builder builder = new AlertDialog.Builder(NewLogActivity.this);
					builder
							.setTitle("Are you sure you're finished?")
							.setMessage("Touch \"YES\" to save.")
							//.setIcon(android.R.drawable.ic_dialog_alert)
							.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									// write on SD card file data in the text box
									try {
										File directory = Environment.getExternalStorageDirectory();
										File myFile = new File(directory, "mythoughtlog.txt");

										// Check if the file already exists so you don't keep creating
										if(!myFile.exists()) {
											//Log.i(TAG, "Creating the file as it doesn't exist already");
											myFile.createNewFile();
										}

										// Open the FileoutputStream
										FileOutputStream fOut = new FileOutputStream(myFile, true);

										// Open the printStream to allow for Strings to be written
										PrintStream printStream = new PrintStream(fOut);


										// Using a stringBuffer to append all the values to
										StringBuffer stringBuffer = new StringBuffer();
										stringBuffer.append("Name: " + txtData.getText());
										stringBuffer.append('\n');
										stringBuffer.append("Situation: " + situation.getText());
										stringBuffer.append('\n');
										stringBuffer.append("Thoughts: " + thoughts.getText());
										stringBuffer.append('\n');
										stringBuffer.append("Emotions: " + emotions.getText());
										stringBuffer.append('\n');
										stringBuffer.append("Behavior: " + behavior.getText());
										stringBuffer.append('\n');
										stringBuffer.append("Distortions: ");
										stringBuffer.append(distortions);
										stringBuffer.append('\n');
										stringBuffer.append("Alt Behavior: " + altbehavior.getText());
										stringBuffer.append('\n');
										stringBuffer.append("Alt Thoughts: " + altthoughts.getText());
										stringBuffer.append('\n');
										stringBuffer.append('\n');

										// Print the stringBuffer to the file
										printStream.print(stringBuffer.toString());

										// Close everything out
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
								}
							})
							.setNegativeButton("No", null)						//Do nothing on no
							.show();

			}//Save onClick
		});// btnWriteSDFile
	}
}// AndSDcard
