package com.feevx.ravan.newssampleapp.api.response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ApiResponse{

	@SerializedName("data")
	private List<NewsItem> data;

	public List<NewsItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ApiResponse{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}