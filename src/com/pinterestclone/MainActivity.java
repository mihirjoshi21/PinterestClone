package com.pinterestclone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.origamilabs.library.views.StaggeredGridView;

public class MainActivity extends Activity
{
	private StaggeredGridView gridView = null;

	private void openAlert()
	{
		new AlertDialog.Builder(this).setTitle("About").setMessage(Html.fromHtml(getString(R.string.copyright))).setCancelable(true).setPositiveButton("ok", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialogInterface, int which)
			{
				dialogInterface.dismiss();
			}
		}).create().show();
	}

	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		gridView.setColumnCount(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE ? 3:2);

	}

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);;
		setContentView(R.layout.activity_main);
		gridView = ((StaggeredGridView)findViewById(R.id.staggered_grid));
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
		gridView.setAdapter(new StaggeredAdapter(getApplicationContext(), imageLoader));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem menuItem)
	{
		switch (menuItem.getItemId()) {
		case R.id.action_settings:
			openAlert();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(menuItem);
	}
}