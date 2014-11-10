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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
	public HttpEntity<RestEnvelope<Resources<Mutation>>> getMutations(
			@RequestParam(value = "geneSymbol", required = false) String geneSymbol,
			@RequestParam(value = "entrezGeneId", required = false) Integer entrezGeneId,
			@RequestParam(value = "sampleId", required = false) String sampleId,
			@RequestParam(value = "variantType", required = false) String variantType,
			@RequestParam(value = "aaChange", required = false) String aaChange
	){

		List<Mutation> mutationList = new ArrayList<Mutation>();
		Resources<Mutation> resources = new Resources<Mutation>(mutationList);
		RestEnvelope<Resources<Mutation>> responseEnvelope = new RestEnvelope<Resources<Mutation>>(resources);
		return new ResponseEntity<RestEnvelope<Resources<Mutation>>>(responseEnvelope, HttpStatus.OK);
	}
}
