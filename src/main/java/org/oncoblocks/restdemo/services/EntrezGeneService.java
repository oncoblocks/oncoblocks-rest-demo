package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.dao.EntrezGeneDao;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by woemler on 9/26/14.
 */

@Service
@Transactional
public class EntrezGeneService {
	
	@Autowired
	private EntrezGeneDao entrezGeneDao;

	// Find all
	public List<EntrezGene> findAllEntrezGenes(Integer limit, Integer offset){
		return entrezGeneDao.findAllEntrezGenes(limit, offset);
	}

	// Find by ID
	public EntrezGene findEntrezGeneById(Integer id){
		return entrezGeneDao.findEntrezGeneById(id);
	}

	// Find by attribute
	public List<EntrezGene> findEntrezGenesByGeneSymbol(String geneSymbol, Integer limit, Integer offset){
		return entrezGeneDao.findEntrezGenesByGeneSymbol(geneSymbol, limit, offset);
	}

	// Add gene
	public EntrezGene addEntrezGene(EntrezGene entrezGene){
		Integer entrezGeneId = entrezGeneDao.addEntrezGene(entrezGene);
		if (entrezGeneId != null && entrezGeneId > 0){
			entrezGene = entrezGeneDao.findEntrezGeneById(entrezGeneId);
		}
		return entrezGene;
	}

	// Update gene
	public Integer updateEntrezGene(EntrezGene entrezGene){
		return entrezGeneDao.updateEntrezGene(entrezGene);
	}

	// Delete gene
	public Integer deleteEntrezGene(Integer id){
		return entrezGeneDao.deleteEntrezGene(id);
	}
	
}
