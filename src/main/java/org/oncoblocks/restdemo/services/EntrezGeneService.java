package org.oncoblocks.restdemo.services;

import java.util.List;

import org.oncoblocks.restdemo.models.EntrezGene;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by woemler on 9/26/14.
 */

@Service
@Transactional
public class EntrezGeneService {
	
//	@Autowired
//	private EntrezGeneDao entrezGeneDao;

	// Find all
	public List<EntrezGene> findAllEntrezGenes(Integer limit, Integer offset){
		//		return entrezGeneDao.findAllEntrezGenes(limit, offset);
		return null;
	}

	// Find by ID
	public EntrezGene findEntrezGeneById(Integer id){
		//return entrezGeneDao.findEntrezGeneById(id);
		return null;
	}

	// Find by attribute
	public List<EntrezGene> findEntrezGenesByGeneSymbol(String geneSymbol, Integer limit, Integer offset){
		//return entrezGeneDao.findEntrezGenesByGeneSymbol(geneSymbol, limit, offset);
		return null;
	}

	// Add gene
	public EntrezGene addEntrezGene(EntrezGene entrezGene){
		//Integer entrezGeneId = entrezGeneDao.addEntrezGene(entrezGene);
		
//		if (entrezGeneId != null && entrezGeneId > 0){
//			entrezGene = entrezGeneDao.findEntrezGeneById(entrezGeneId);
//		}
//		return entrezGene;
		return null;
	}

	// Update gene
	public Integer updateEntrezGene(EntrezGene entrezGene){
		//return entrezGeneDao.updateEntrezGene(entrezGene);
		return null;
	}

	// Delete gene
	public Integer deleteEntrezGene(Integer id){
		//return entrezGeneDao.deleteEntrezGene(id);
		return null;
	}
	
}
