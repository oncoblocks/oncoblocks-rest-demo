package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.dao.CellLineDao;
import org.oncoblocks.restdemo.models.CellLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by woemler on 10/2/14.
 */

@Service
@Transactional
public class CellLineService {
	
	@Autowired
	private CellLineDao cellLineDao;

	// Find all
	public List<CellLine> findAllCellLines(){
		return cellLineDao.findAllCellLines();
	}

	// Find by ID
	public CellLine findCellLineById(Integer id){
		return cellLineDao.findCellLineById(id);
	}

	// Find by attributes
	public List<CellLine> findCellLinesByCcleName(String ccleName){
		return cellLineDao.findCellLineByCcleName(ccleName);
	}

	// Add cell line
	public CellLine addCellLine(CellLine cellLine){
		Integer cellLineId = cellLineDao.addCellLine(cellLine);
		if (cellLineId != null && cellLineId > 0){
			cellLine = cellLineDao.findCellLineById(cellLineId);
		}
		return cellLine;
	}

	// Update cell line
	public Integer updateCellLine(CellLine cellLine){
		return cellLineDao.updateCellLine(cellLine);
	}

	// Delete cell line
	public Integer deleteCellLine(Integer id){
		return cellLineDao.deleteCellLine(id);
	}
	
}
