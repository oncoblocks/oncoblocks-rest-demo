package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.models.Sample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by woemler on 10/2/14.
 */

@Service
@Transactional
public class SampleService {
	
//	@Autowired
//	private SampleDao sampleDao;

	// Find all
	public List<Sample> findAllSamples(Integer limit, Integer offset){
		return null;
		//return sampleDao.findAllSamples(limit, offset);
	}

	// Find by ID
	public Sample findSampleById(Integer id){
		return null;
		//return sampleDao.findSampleById(id);
	}

	// Find by attributes
	public List<Sample> findSamplesBySampleId(String sampleId, Integer limit, Integer offset){
		return null;
		//return sampleDao.findSampleByCcleName(ccleName, limit, offset);
	}

	// Add cell line
	public Sample addSample(Sample sample){
//		Integer sampleId = sampleDao.addSample(sample);
//		if (sampleId != null && sampleId > 0){
//			sample = sampleDao.findSampleById(sampleId);
//		}
//		return sample;
		return null;
	}

	// Update cell line
	public Integer updateSample(Sample sample){
		//return sampleDao.updateSample(sample);
		return null;
	}

	// Delete cell line
	public Integer deleteSample(Integer id){
		//return sampleDao.deleteSample(id);
		return null;
	}
	
}
