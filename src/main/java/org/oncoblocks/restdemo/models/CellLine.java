package org.oncoblocks.restdemo.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by woemler on 10/2/14.
 */

@XStreamAlias("cellLine")
public class CellLine implements RestEntity {
	
	private Integer cellLineId;
	private String ccleName;
	private String primaryName;
	private String gender;
	private String primarySite;
	private String primaryHistology;
	private String histologySubtype;
	private String notes;
	private String source;

	public Integer getCellLineId() {
		return cellLineId;
	}

	public void setCellLineId(Integer cellLineId) {
		this.cellLineId = cellLineId;
	}

	public String getCcleName() {
		return ccleName;
	}

	public void setCcleName(String ccleName) {
		this.ccleName = ccleName;
	}

	public String getPrimaryName() {
		return primaryName;
	}

	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPrimarySite() {
		return primarySite;
	}

	public void setPrimarySite(String primarySite) {
		this.primarySite = primarySite;
	}

	public String getPrimaryHistology() {
		return primaryHistology;
	}

	public void setPrimaryHistology(String primaryHistology) {
		this.primaryHistology = primaryHistology;
	}

	public String getHistologySubtype() {
		return histologySubtype;
	}

	public void setHistologySubtype(String histologySubtype) {
		this.histologySubtype = histologySubtype;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override 
	public LinkedHashMap<String, Object> getAttributes() {
		LinkedHashMap<String,Object> attributes = new LinkedHashMap<>();
		attributes.put("cellLineId", this.cellLineId);
		attributes.put("ccleName", this.ccleName);
		attributes.put("primaryName", this.primaryName);
		attributes.put("gender", this.gender);
		attributes.put("primarySite", this.primarySite);
		attributes.put("primaryHistology", this.primaryHistology);
		attributes.put("histologySubtype", this.histologySubtype);
		attributes.put("notes", this.notes);
		attributes.put("source", this.source);
		return attributes;
	}

	@Override
	public String toText(String delimiter, boolean showHeader) {
		StringBuffer buffer = new StringBuffer();
		LinkedHashMap<String,Object> attributes = this.getAttributes();
		if (showHeader){
			for (Map.Entry entry: attributes.entrySet()){
				buffer.append(entry.getKey() + delimiter);
			}
			buffer.append("\n");
		}
		for (Map.Entry entry: attributes.entrySet()){
			buffer.append(entry.getValue() + delimiter);
		}
		buffer.append("\n");
		return buffer.toString();
	}
	
}
