package org.oncoblocks.restdemo.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by woemler on 11/10/14.
 */

@XStreamAlias("tag")
@JsonRootName("tag")
public class Tag extends RestEntity{
	
	private Integer tagId;
	private String label;
	private String description;

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
