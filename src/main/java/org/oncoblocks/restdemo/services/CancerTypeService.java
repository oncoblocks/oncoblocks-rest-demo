package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.models.CancerType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by woemler on 11/10/14.
 */

@Service
@Transactional
public class CancerTypeService {
	
	public List<CancerType> findAllCancerTypes(Integer limit, Integer offset){
		return null;
	}
	
	public CancerType findCancerTypeById(Integer id){
		return null;
	}
	
	public List<CancerType> findCancerTypesByParentId(Integer id, Integer limit, Integer offset){
		return null;
	}
	
	public List<CancerType> findCancerTypesByName(String name, Integer limit, Integer offset){
		return null;
	}
	
	public CancerType addCancerType(CancerType cancerType){
		return null;
	}
	
	public Integer updateCancerType(CancerType cancerType){
		return null;
	}
	
	public Integer deleteCancerType(Integer id){
		return null;
	}
	
}
