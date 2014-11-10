package org.oncoblocks.restdemo.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by woemler on 11/10/14.
 */

@XStreamAlias("cancerType")
@JsonRootName("cancerType")
public class CancerType extends RestEntity {
	
	private Integer cancerTypeId;
	private Integer parentId;
	private String name;
	private String idPath;

	public Integer getCancerTypeId() {
		return cancerTypeId;
	}

	public void setCancerTypeId(Integer cancerTypeId) {
		this.cancerTypeId = cancerTypeId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}
}
