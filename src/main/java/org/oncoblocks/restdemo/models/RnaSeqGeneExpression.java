package org.oncoblocks.restdemo.models;

/**
 * Created by woemler on 10/2/14.
 */
public class RnaSeqGeneExpression {
	
	private Integer cellLineId;
	private Integer entrezGeneId;
	private String accession;
	private Double value;

	public Integer getCellLineId() {
		return cellLineId;
	}

	public void setCellLineId(Integer cellLineId) {
		this.cellLineId = cellLineId;
	}

	public Integer getEntrezGeneId() {
		return entrezGeneId;
	}

	public void setEntrezGeneId(Integer entrezGeneId) {
		this.entrezGeneId = entrezGeneId;
	}

	public String getAccession() {
		return accession;
	}

	public void setAccession(String accession) {
		this.accession = accession;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
