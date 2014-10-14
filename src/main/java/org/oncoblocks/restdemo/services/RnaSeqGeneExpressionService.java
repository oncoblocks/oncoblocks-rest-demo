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
	
	public List<RnaSeqGeneExpression> findAllRnaSeqGeneExpression(){
		return rnaSeqGeneExpressionDao.findAllRnaSeqGeneExpression();
	}
	
	public List<RnaSeqGeneExpression> findRnaSeqGeneExpressionByCellLine(Integer cellLineId){
		return rnaSeqGeneExpressionDao.findRnaSeqGeneExpressionByCellLine(cellLineId);
	}
	
	public List<RnaSeqGeneExpression> findRnaSeqGeneExpressionByGene(Integer entrezGeneId){
		return rnaSeqGeneExpressionDao.findRnaSeqGeneExpressionByGene(entrezGeneId);
	}
	
	public Integer addRnaSeqGeneExpression(RnaSeqGeneExpression rnaSeqGeneExpression){
		return rnaSeqGeneExpressionDao.addRnaSeqGeneExpression(rnaSeqGeneExpression);
	}
	
}
