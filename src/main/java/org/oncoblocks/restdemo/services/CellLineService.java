package org.oncoblocks.restdemo.services;

import java.util.List;

import org.oncoblocks.restdemo.models.CellLine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by woemler on 10/2/14.
 */

@Service
@Transactional
public class CellLineService {
	
//	@Autowired
//	private CellLineDao cellLineDao;

	// Find all
	public List<CellLine> findAllCellLines(Integer limit, Integer offset){
		return null;
		//return cellLineDao.findAllCellLines(limit, offset);
	}

	// Find by ID
	public CellLine findCellLineById(Integer id){
		return null;
		//return cellLineDao.findCellLineById(id);
	}

	// Find by attributes
	public List<CellLine> findCellLinesByCcleName(String ccleName, Integer limit, Integer offset){
		return null;
		//return cellLineDao.findCellLineByCcleName(ccleName, limit, offset);
	}

	// Add cell line
	public CellLine addCellLine(CellLine cellLine){
//		Integer cellLineId = cellLineDao.addCellLine(cellLine);
//		if (cellLineId != null && cellLineId > 0){
//			cellLine = cellLineDao.findCellLineById(cellLineId);
//		}
//		return cellLine;
		return null;
	}

	// Update cell line
	public Integer updateCellLine(CellLine cellLine){
		//return cellLineDao.updateCellLine(cellLine);
		return null;
	}

	// Delete cell line
	public Integer deleteCellLine(Integer id){
		//return cellLineDao.deleteCellLine(id);
		return null;
	}
	
}
