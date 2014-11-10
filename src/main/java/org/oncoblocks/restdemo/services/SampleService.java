package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.models.EntrezGene;
import org.oncoblocks.restdemo.models.Sample;
import org.oncoblocks.restdemo.util.DummyDataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by woemler on 10/2/14.
 */

@Service
@Transactional
public class SampleService {
	
	// Find all
	public List<Sample> findAllSamples(Integer limit, Integer offset){
		return getDummySamples();
	}

	// Find by ID
	public Sample findSampleById(Integer id){
		return createDummySample();
	}

	// Find by attributes
	public List<Sample> findSamplesBySampleId(String sampleId, Integer limit, Integer offset){
		return getDummySamples();
	}

	// Add a sample
	public Sample addSample(Sample sample){
		return createDummySample();
	}

	// Update sample
	public Integer updateSample(Sample sample){
		return 1;
	}

	// Delete cell line
	public Integer deleteSample(Integer id){
		return 1;
	}
	
	private List<Sample> getDummySamples() {
		List<Sample> sampleList = new ArrayList<Sample>();
		for (int i=0; i<20; i++) {
			Sample sample = createDummySample();
			sampleList.add(sample);
		}
		return sampleList;
	}
	
	private Sample createDummySample() {
		Sample sample = new Sample();
		DummyDataGenerator.createDummyData(sample);
		return sample;
	}
}