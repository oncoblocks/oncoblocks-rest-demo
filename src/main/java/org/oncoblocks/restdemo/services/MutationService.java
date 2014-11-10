package org.oncoblocks.restdemo.services;

import java.util.List;

import org.oncoblocks.restdemo.models.Sample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by woemler on 10/2/14.
 */

@Service
@Transactional
public class MutationService {
	
	// Find all
	public List<Sample> findMutations(){
		return null;
	}
}
