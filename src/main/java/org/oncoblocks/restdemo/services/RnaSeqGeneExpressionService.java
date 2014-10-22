package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.dao.RnaSeqGeneExpressionDao;
import org.oncoblocks.restdemo.models.RnaSeqGeneExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by woemler on 10/2/14.
 */

@Service
@Transactional
public class RnaSeqGeneExpressionService {
	
	@Autowired
	private RnaSeqGeneExpressionDao rnaSeqGeneExpressionDao;
	
	public RnaSeqGeneExpression findRnaSeqGeneExpressionById(Integer id){
		return rnaSeqGeneExpressionDao.findRnaSeqGeneExpressionById(id);
	}
	
	public List<RnaSeqGeneExpression> findAllRnaSeqGeneExpression(Integer limit, Integer offset){
		return rnaSeqGeneExpressionDao.findAllRnaSeqGeneExpression(limit, offset);
	}
	
	public List<RnaSeqGeneExpression> findRnaSeqGeneExpressionByCellLine(Integer cellLineId, Integer limit, Integer offset){
		return rnaSeqGeneExpressionDao.findRnaSeqGeneExpressionByCellLine(cellLineId, limit, offset);
	}
	
	public List<RnaSeqGeneExpression> findRnaSeqGeneExpressionByGene(Integer entrezGeneId, Integer limit, Integer offset){
		return rnaSeqGeneExpressionDao.findRnaSeqGeneExpressionByGene(entrezGeneId, limit, offset);
	}
	
	public Integer addRnaSeqGeneExpression(RnaSeqGeneExpression rnaSeqGeneExpression){
		return rnaSeqGeneExpressionDao.addRnaSeqGeneExpression(rnaSeqGeneExpression);
	}
	
}
