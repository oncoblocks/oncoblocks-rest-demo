package org.oncoblocks.restdemo.models;

/**
 * Created by woemler on 10/2/14.
 */
public class CellLine {
	
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
}
