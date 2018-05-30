package com.feevx.ravan.newssampleapp.api.response;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class NewsItem {

	@SerializedName("schedule")
	private String schedule;

	@SerializedName("short_text")
	private String shortText;

	@SerializedName("full_text")
	private String fullText;

	@SerializedName("coefficient")
	private String coefficient;

	@SerializedName("publicated_at")
	private String publicatedAt;

	@SerializedName("title")
	private String title;

	@SerializedName("type")
	private String type;

	@SerializedName("picture")
	private String picture;

	public String getSchedule(){
		return schedule;
	}

	public String getShortText(){
		return shortText;
	}

	public String getFullText(){
		return fullText;
	}

	public String getCoefficient(){
		return coefficient;
	}

	public String getPublicatedAt(){
		return publicatedAt;
	}

	public String getTitle(){
		return title;
	}

	public String getType(){
		return type;
	}

	public String getPicture(){
		return picture;
	}

	@Override
 	public String toString(){
		return 
			"NewsItem{" +
			"schedule = '" + schedule + '\'' + 
			",short_text = '" + shortText + '\'' + 
			",full_text = '" + fullText + '\'' + 
			",coefficient = '" + coefficient + '\'' + 
			",publicated_at = '" + publicatedAt + '\'' + 
			",title = '" + title + '\'' + 
			",type = '" + type + '\'' + 
			",picture = '" + picture + '\'' + 
			"}";
		}
}