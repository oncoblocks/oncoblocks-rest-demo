package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.models.CancerType;
import org.oncoblocks.restdemo.models.Mutation;
import org.oncoblocks.restdemo.util.DummyDataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woemler on 11/10/14.
 */

@Service
@Transactional
public class CancerTypeService {
	
	public List<CancerType> findAllCancerTypes(Integer limit, Integer offset){
		return getDummyCancerTypes();
	}
	
	public CancerType findCancerTypeById(Integer id){
		return createDummyCancerType();
	}
	
	public List<CancerType> findCancerTypesByParentId(Integer id, Integer limit, Integer offset){
		return getDummyCancerTypes();
	}
	
	public List<CancerType> findCancerTypesByName(String name, Integer limit, Integer offset){
		return getDummyCancerTypes();
	}
	
	public CancerType addCancerType(CancerType cancerType){
		return createDummyCancerType();
	}
	
	public Integer updateCancerType(CancerType cancerType){
		return 1;
	}
	
	public Integer deleteCancerType(Integer id){
		return 1;
	}
	
	private List<CancerType> getDummyCancerTypes() {
		List<CancerType> cancerTypeList = new ArrayList<CancerType>();
		for (int i=0; i<20; i++) {
			CancerType cancerType = createDummyCancerType();
			cancerTypeList.add(cancerType);
		}
		return cancerTypeList;
	}
	
	private CancerType createDummyCancerType() {
		CancerType cancerType = new CancerType();
		DummyDataGenerator.createDummyData(cancerType);
		return cancerType;
	}	
}
