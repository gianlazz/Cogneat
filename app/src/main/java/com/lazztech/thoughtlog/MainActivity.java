package com.lazztech.thoughtlog;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{
	private ListView mDrawerList;
	private ArrayAdapter<String> mAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		mDrawerList = (ListView)findViewById(R.id.navList);

		addDrawerItems();
	}

	private void addDrawerItems() {
		String[] osArray = { "Log History", "New Log", "Analytics", "Settings"};
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
		mDrawerList.setAdapter(mAdapter);
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
