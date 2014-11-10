package org.oncoblocks.restdemo.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by woemler on 10/2/14.
 */

@XStreamAlias("sample")
@JsonRootName("sample")
public class Sample extends RestEntity {
	
	private Integer sId;
	private String sampleId;
	private String study;
	private Integer cancerTypeId;
	private String source;

	public Integer getSid() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public Integer getCancerTypeId() {
		return cancerTypeId;
	}

	public void setCancerTypeId(Integer cancerTypeId) {
		this.cancerTypeId = cancerTypeId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
