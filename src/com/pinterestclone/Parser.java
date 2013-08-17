package com.pinterestclone;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser
{
	public ArrayList<StaggeredModel> getParsedData()
	{
		ArrayList<StaggeredModel> arrayList = new ArrayList<StaggeredModel>();
		try
		{
			JSONArray jsonArray = new JSONObject(ConstantData.jsonData).getJSONArray("items");
			for (int i = 0; i < jsonArray.length(); i++)
			{
				StaggeredModel staggeredModel = new StaggeredModel();
				staggeredModel.setColor(jsonArray.getJSONObject(i).getInt("color"));
				staggeredModel.setImg_url(jsonArray.getJSONObject(i).getString("url"));
				arrayList.add(staggeredModel);
			}
		}
		catch (JSONException exception)
		{
			exception.printStackTrace();
		}
		return arrayList;
	}
}