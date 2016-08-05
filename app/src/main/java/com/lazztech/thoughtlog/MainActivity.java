package com.lazztech.thoughtlog;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;

public class MainActivity extends AppCompatActivity

{
	private ListView mDrawerList;
	private ArrayAdapter<String> mAdapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private String mActivityTitle;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		mDrawerList = (ListView)findViewById(R.id.navList);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mActivityTitle = getTitle().toString();

		addDrawerItems();
		setupDrawer();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	private void addDrawerItems() {
		String[] osArray = { "Log History", "New Log", "Depression Test", "Anxiety Test", "Analytics", "Settings"};
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
		mDrawerList.setAdapter(mAdapter);

		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
					case 0: //Log History
						Intent loghistoryactivity = new Intent(MainActivity.this, LogHistoryActivity.class);
						startActivity(loghistoryactivity);
						break;
					case 1: //New Log
						Intent newlogactivity = new Intent(MainActivity.this, NewLogActivity.class);
						startActivity(newlogactivity);
						break;
					case 2: //Depression Test
						Intent phq9activity = new Intent(MainActivity.this, PHQ9Activity.class);
						startActivity(phq9activity);
						break;
					case 3: //Anxiety Test
						Intent gad7activity = new Intent(MainActivity.this, GAD7Activity.class);
						startActivity(gad7activity);
						break;
					case 4: //Analytics
						Toast.makeText(MainActivity.this, "In development.", Toast.LENGTH_SHORT).show();
						break;
					case 5: //Settings
						Toast.makeText(MainActivity.this, "In development.", Toast.LENGTH_SHORT).show();
						break;
					//default:
					//	throw new InvalidArgumentException("wtf, unknown position");
				}
			}
		});
	}



	private void setupDrawer() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.string.drawer_open, R.string.drawer_close) {

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle("Navigation");
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(mActivityTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};

		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
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
				Toast.makeText(this, "Developed by Gian Lazzarini.", Toast.LENGTH_LONG).show();
				return true;
			case R.id.mainMenuExit:
				finish();
				return true;

		}

		// Activate the navigation drawer toggle
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
