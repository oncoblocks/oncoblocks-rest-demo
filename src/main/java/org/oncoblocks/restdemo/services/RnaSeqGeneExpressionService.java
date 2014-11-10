package org.oncoblocks.restdemo.services;

import java.util.List;

import org.oncoblocks.restdemo.models.RnaSeqGeneExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by woemler on 10/2/14.
 */

@Service
@Transactional
public class RnaSeqGeneExpressionService {
	
//	@Autowired
//	private RnaSeqGeneExpressionDao rnaSeqGeneExpressionDao;
	
	public RnaSeqGeneExpression findRnaSeqGeneExpressionById(Integer id){
//		return rnaSeqGeneExpressionDao.findRnaSeqGeneExpressionById(id);
		return null;
	}
	
	public List<RnaSeqGeneExpression> findAllRnaSeqGeneExpression(Integer limit, Integer offset){
//		return rnaSeqGeneExpressionDao.findAllRnaSeqGeneExpression(limit, offset);
		return null;
	}
	
	public List<RnaSeqGeneExpression> findRnaSeqGeneExpressionByCellLine(Integer cellLineId, Integer limit, Integer offset){
		//return rnaSeqGeneExpressionDao.findRnaSeqGeneExpressionByCellLine(cellLineId, limit, offset);
		return null;
	}
	
	public List<RnaSeqGeneExpression> findRnaSeqGeneExpressionByGene(Integer entrezGeneId, Integer limit, Integer offset){
		//return rnaSeqGeneExpressionDao.findRnaSeqGeneExpressionByGene(entrezGeneId, limit, offset);
		return null;
	}
	
	public Integer addRnaSeqGeneExpression(RnaSeqGeneExpression rnaSeqGeneExpression){
		//return rnaSeqGeneExpressionDao.addRnaSeqGeneExpression(rnaSeqGeneExpression);
		return null;
	}
	
}
