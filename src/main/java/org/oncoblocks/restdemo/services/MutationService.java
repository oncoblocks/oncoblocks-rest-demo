package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.models.Mutation;
import org.oncoblocks.restdemo.util.DummyDataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
			Mutation mutation = findMutation(null);
			mutationList.add(mutation);
		}
		return mutationList;
	}

	//  Find Mutaiton;
	//  Returns dummy data.
	public Mutation findMutation(Integer id) {
		Mutation mutation = new Mutation();
		DummyDataGenerator.createDummyData(mutation);
		return mutation;
	}
}
