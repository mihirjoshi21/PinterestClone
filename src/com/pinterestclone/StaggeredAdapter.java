package com.pinterestclone;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class StaggeredAdapter extends BaseAdapter
{
	private ArrayList<StaggeredModel> adapterModels;
	private GradientDrawable bgShape;
	private ImageLoader imageLoader;
	private Context mContext;
	private DisplayImageOptions options;

	public StaggeredAdapter(Context paramContext, ImageLoader paramImageLoader)
	{
		this.mContext = paramContext;
		this.imageLoader = paramImageLoader;
		this.adapterModels = new Parser().getParsedData();
	}

	public int getCount()
	{
		return this.adapterModels.size();
	}

	public Object getItem(int paramInt)
	{
		return this.adapterModels.get(paramInt);
	}

	public long getItemId(int paramInt)
	{
		return paramInt;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if(convertView==null){
			holder = new ViewHolder();
			LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = li.inflate(R.layout.staggered_gridview_items, null);


			holder.iv 		 = (ImageView)convertView.findViewById(R.id.category_image);

			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}

		holder.iv.setScaleType(ScaleType.FIT_XY);

		bgShape = new GradientDrawable();
		bgShape.setColor(adapterModels.get(position).getColor());
		Log.i("color "+position, String.valueOf(adapterModels.get(position).getColor()));
		options = new DisplayImageOptions.Builder()

		.showStubImage(bgShape)
		.showImageForEmptyUri(bgShape)
		.showImageOnFail(bgShape)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
		.bitmapConfig(Bitmap.Config.ARGB_8888)
		.displayer(new FadeInBitmapDisplayer(300))
		.build();
		imageLoader.displayImage(adapterModels.get(position).getImg_url(), holder.iv, options);


		return convertView;
	}

	private static class ViewHolder {
		protected ImageView iv;
	}

	public void setMutation()
	{

	}
}