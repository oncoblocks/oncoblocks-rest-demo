package org.oncoblocks.restdemo.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by woemler on 10/2/14.
 */

@XStreamAlias("rnaSeqGeneExpression")
public class RnaSeqGeneExpression implements RestEntity {
	
	private Integer rnaSeqGeneExpressionId;
	private Integer cellLineId;
	private Integer entrezGeneId;
	private String accession;
	private Double value;

	public Integer getRnaSeqGeneExpressionId() {
		return rnaSeqGeneExpressionId;
	}

	public void setRnaSeqGeneExpressionId(Integer rnaSeqGeneExpressionId) {
		this.rnaSeqGeneExpressionId = rnaSeqGeneExpressionId;
	}

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

	@Override public LinkedHashMap<String, Object> getAttributes() {
		LinkedHashMap<String,Object> attributes = new LinkedHashMap<>();
		attributes.put("rnaSeqGeneExpressionId", this.rnaSeqGeneExpressionId);
		attributes.put("cellLineId", this.cellLineId);
		attributes.put("entrezGeneId", this.entrezGeneId);
		attributes.put("accession", this.accession);
		attributes.put("value", this.value);
		return attributes;
	}

	@Override public String toText(String delimiter, boolean showHeader) {
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
