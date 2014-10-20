package org.oncoblocks.restdemo.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by woemler on 9/23/14.
 */

@XStreamAlias("entrezGene")
public class EntrezGene implements RestEntity {
	
	private Integer entrezGeneId;
	private Integer taxId;
	private String locusTag;
	private String primaryGeneSymbol;
	private String chromosome;
	private String chromosomeLocation;
	private String geneType;
	private String description;
	private Map<String,Object> databaseCrossReferences;
	private Set<String> geneSymbolAliases;
	private String kinase;
	private String cgcGene;
	
	public EntrezGene(){ }
	
	public Integer getEntrezGeneId() {
		return entrezGeneId;
	}

	public void setEntrezGeneId(Integer entrezGeneId) {
		this.entrezGeneId = entrezGeneId;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public String getLocusTag() {
		return locusTag;
	}

	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}

	public String getPrimaryGeneSymbol() {
		return primaryGeneSymbol;
	}

	public void setPrimaryGeneSymbol(String primaryGeneSymbol) {
		this.primaryGeneSymbol = primaryGeneSymbol;
	}

	public String getChromosome() {
		return chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getChromosomeLocation() {
		return chromosomeLocation;
	}

	public void setChromosomeLocation(String chromosomeLocation) {
		this.chromosomeLocation = chromosomeLocation;
	}

	public String getGeneType() {
		return geneType;
	}

	public void setGeneType(String geneType) {
		this.geneType = geneType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Object> getDatabaseCrossReferences() {
		return databaseCrossReferences;
	}

	public void setDatabaseCrossReferences(Map<String, Object> databaseCrossReferences) {
		this.databaseCrossReferences = databaseCrossReferences;
	}

	public Set<String> getGeneSymbolAliases() {
		return geneSymbolAliases;
	}

	public void setGeneSymbolAliases(Set<String> geneSymbolAliases) {
		this.geneSymbolAliases = geneSymbolAliases;
	}

	public String isKinase() {
		return kinase;
	}

	public void setIsKinase(String isKinase) {
		this.kinase = isKinase;
	}

	public String isCgcGene() {
		return cgcGene;
	}

	public void setIsCgcGene(String isCgcGene) {
		this.cgcGene = isCgcGene;
	}

	@Override 
	public LinkedHashMap<String, Object> getAttributes() {
		LinkedHashMap<String,Object> attributes = new LinkedHashMap<>();
		attributes.put("entrezGeneId", this.entrezGeneId);
		attributes.put("taxId", this.taxId);
		attributes.put("locusTag", this.locusTag);
		attributes.put("primaryGeneSymbol", this.primaryGeneSymbol);
		attributes.put("chromosome", this.chromosome);
		attributes.put("chromosomeLocation", this.chromosomeLocation);
		attributes.put("geneType", this.geneType);
		attributes.put("description", this.description);
		attributes.put("databaseCrossReferences", this.databaseCrossReferences);
		attributes.put("geneSymbolAliases", this.geneSymbolAliases);
		attributes.put("kinase", this.kinase);
		attributes.put("cgcGene", this.cgcGene);
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
