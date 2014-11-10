package org.oncoblocks.restdemo.services;

import java.util.ArrayList;
import java.util.List;

import org.oncoblocks.restdemo.models.Mutation;
import org.oncoblocks.restdemo.util.DummyDataGenerator;
import org.oncoblocks.restdemo.models.Sample;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dummy Mutation Service.
 */

@Service
@Transactional
public class MutationService {
	
	// Find Mutations;
	// Returns dummy data.
	public List<Mutation> findMutations() {
		ArrayList<Mutation> mutationList = new ArrayList<Mutation>();
		for (int i=0; i<10; i++) {
			Mutation mutation = findMutation();
			mutationList.add(mutation);
		}
		return mutationList;
	}

	//  Find Mutaiton;
	//  Returns dummy data.
	public Mutation findMutation() {
		Mutation mutation = new Mutation();
		DummyDataGenerator.createDummyData(mutation);
		return mutation;
	}
}
