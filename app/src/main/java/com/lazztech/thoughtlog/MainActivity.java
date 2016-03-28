package com.lazztech.thoughtlog;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.io.*;

public class MainActivity extends Activity
{
	// GUI controls
	TextView thoughtLogView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		//Set MainActivity.xml as user interface layout
        setContentView(R.layout.main);
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
		File file = new File(Environment.getExternalStorageDirectory() + "mythoughtlog.txt");
		if (!file.exists()){
			String line = "Need to add smth";
			return line;
		}
		String line = null;
		//Read text from file
		//StringBuilder text = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			line = br.readLine();
		}
		catch (IOException e) {
			//You'll need to add proper error handling here
		}
		return line;
	}


	public void onNewLogButtonClick(View view)
	{
		Intent intent = new Intent(this, NewLogActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate main_menu.xml 
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.mainMenuAbout:
				Toast.makeText(this, "This is my app!!!", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.mainMenuExit:
				finish();
				return true;

		}
		return super.onOptionsItemSelected(item);
	}
	
}
