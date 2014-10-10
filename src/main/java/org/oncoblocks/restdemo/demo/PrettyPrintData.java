package org.oncoblocks.restdemo.demo;

import org.oncoblocks.restdemo.models.EntrezGene;

import java.util.Map;

/**
 * Created by woemler on 9/23/14.
 */
public class PrettyPrintData {

	public static void printEntrezGene(EntrezGene entrezGene){
		System.out.println("");
		System.out.println("Entrez Gene ID: " + entrezGene.getEntrezGeneId());
		System.out.println("Tax ID: " + entrezGene.getTaxId());
		System.out.println("Locus Tag: " + entrezGene.getLocusTag());
		System.out.println("Primary Gene Symbol: " + entrezGene.getPrimaryGeneSymbol());
		System.out.println("Chromosome: " + entrezGene.getChromosome());
		System.out.println("Chromosome Location: " + entrezGene.getChromosomeLocation());
		System.out.println("Description: " + entrezGene.getDescription());
		System.out.println("Gene Type: " + entrezGene.getGeneType());
		for (Map.Entry entry: entrezGene.getDatabaseCrossReferences().entrySet()){
			System.out.println("Database Cross-reference: " + entry.getKey() + ": " + (String) entry.getValue());
		}
		for (String alias: entrezGene.getGeneSymbolAliases()){
			System.out.println("Gene Symbol Alias: " + alias);
		}
		System.out.println("Kinase: " + entrezGene.isKinase());
		System.out.println("Cancer Gene Census: " + entrezGene.isCgcGene());
	}
	
}
