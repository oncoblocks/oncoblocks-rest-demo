package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.models.Mutation;
import org.oncoblocks.restdemo.models.RestEnvelope;
import org.oncoblocks.restdemo.services.MutationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 10/2/14.
 */

@Controller
@ExposesResourceFor(Mutation.class)
@RequestMapping(value = "/api/v1/mutations", produces = {"application/json", "application/xml", "text/plain", "text/csv"})
public class MutationController {
	
	@Autowired
	private MutationService mutationService;

	//// Read
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<Resources<Mutation>>> findMutations(
			@RequestParam(value = "geneSymbol", required = false) String geneSymbol,
			@RequestParam(value = "entrezGeneId", required = false) Integer entrezGeneId,
			@RequestParam(value = "sampleId", required = false) String sampleId,
			@RequestParam(value = "variantType", required = false) String variantType,
			@RequestParam(value = "aaChange", required = false) String aaChange,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	) {

		List<Mutation> mutationList = new ArrayList<Mutation>();
		for (Mutation mutation : mutationService.findMutations()) {
			mutation.add(linkTo(methodOn(MutationController.class)
					.findMutation(mutation.getMutationId(), null))
					.withSelfRel());
			mutation.add(linkTo(methodOn(EntrezGeneController.class)
					.findEntrezGeneById(mutation.getEntrezGeneId(), fields))
					.withRel("gene"));
			mutation.add(linkTo(methodOn(SampleController.class)
					.findSamplesBySampleId(mutation.getSampleId(), null, null, fields))
					.withRel("gene"));
			mutationList.add(mutation);
		}

		Resources<Mutation> resources = new Resources<Mutation>(mutationList);
		resources.add(linkTo(methodOn(MutationController.class)
				.findMutations(null, null, null, null, null, null, null, null))
				.withSelfRel());

		RestEnvelope<Resources<Mutation>> restEnvelope =
				new RestEnvelope<Resources<Mutation>>(resources);
		restEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<Mutation>>>(restEnvelope, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<Mutation>> findMutation(
			@PathVariable("id") Integer mutationId,
			@RequestParam(value = "fields", required = false) String fields
	) {

		Mutation mutation = mutationService.findMutation(mutationId);
		mutation.add(linkTo(methodOn(MutationController.class)
			.findMutation(mutation.getMutationId(), null))
			.withSelfRel());
		
		RestEnvelope<Mutation> restEnvelope =
				new RestEnvelope<Mutation>(mutation);
		restEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Mutation>>(restEnvelope, HttpStatus.OK);
	}
}
