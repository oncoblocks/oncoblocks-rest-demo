package org.oncoblocks.restdemo.services;

import java.util.ArrayList;
import java.util.List;

import org.oncoblocks.restdemo.models.EntrezGene;
import org.oncoblocks.restdemo.util.DummyDataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by woemler on 9/26/14.
 */

@Service
@Transactional
public class EntrezGeneService {
	
	// Find all
	public List<EntrezGene> findAllEntrezGenes(Integer limit, Integer offset){
		return getDummyGenes();
	}

	// Find by ID
	public EntrezGene findEntrezGeneById(Integer id){
		return createDummyGene();
	}

	// Find by attribute
	public List<EntrezGene> findEntrezGenesByGeneSymbol(String geneSymbol, Integer limit, Integer offset){
		return getDummyGenes();
	}

	// Add gene
	public EntrezGene addEntrezGene(EntrezGene entrezGene){
		return createDummyGene();
	}

	// Update gene
	public Integer updateEntrezGene(EntrezGene entrezGene){
		return 1;
	}

	// Delete gene
	public Integer deleteEntrezGene(Integer id){
		return 1;
	}
	
	private List<EntrezGene> getDummyGenes() {
		List<EntrezGene> geneList = new ArrayList<EntrezGene>();
		for (int i=0; i<20; i++) {
			EntrezGene gene = createDummyGene();
			geneList.add(gene);
		}
		return geneList;
	}
	
	private EntrezGene createDummyGene() {
		EntrezGene gene = new EntrezGene();
		DummyDataGenerator.createDummyData(gene);
		return gene;
	}		
}
