package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.exceptions.MalformedEntityException;
import org.oncoblocks.restdemo.exceptions.RequestFailureException;
import org.oncoblocks.restdemo.exceptions.ResourceNotFoundException;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.oncoblocks.restdemo.models.RestEnvelope;
import org.oncoblocks.restdemo.services.EntrezGeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 9/26/14.
 */

@Controller
@ExposesResourceFor(EntrezGene.class)
@RequestMapping(value = "/api/v1/genes", produces = {"application/json", "application/xml", "text/plain", "text/csv"})
public class EntrezGeneController {
	
	@Autowired
	private EntrezGeneService entrezGeneService;
	
	// Find all
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<Resources<EntrezGene>>> findAllEntrezGenes(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<EntrezGene> entrezGeneList = new ArrayList<EntrezGene>();
		for (EntrezGene entrezGene: entrezGeneService.findAllEntrezGenes(limit, offset)){
			entrezGene.add(linkTo(methodOn(EntrezGeneController.class)
					.findEntrezGeneById(entrezGene.getEntrezGeneId(), fields))
					.withSelfRel());
			entrezGeneList.add(entrezGene);
		}
		
		Resources<EntrezGene> resources = new Resources<EntrezGene>(entrezGeneList);
		resources.add(linkTo(methodOn(EntrezGeneController.class)
				.findAllEntrezGenes(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<EntrezGene>> envelope = new RestEnvelope<Resources<EntrezGene>>(resources);
		envelope.setFields(fields);
		
		return new ResponseEntity<RestEnvelope<Resources<EntrezGene>>>(envelope, HttpStatus.OK);
		
	}
	
	// Find by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<EntrezGene>> findEntrezGeneById(
			@PathVariable("id") Integer id,
			@RequestParam(value = "fields", required = false) String fields
	){

		EntrezGene entrezGene = entrezGeneService.findEntrezGeneById(id);
		
		if (entrezGene == null){
			throw new ResourceNotFoundException(
					40401, 
					"The requested resource is not available.", 
					"No EntrezGene record found with ID: " + id, 
					"");
		}

		entrezGene.add(linkTo(methodOn(EntrezGeneController.class)
				.findEntrezGeneById(entrezGene.getEntrezGeneId(), fields))
				.withSelfRel());
		
		RestEnvelope<EntrezGene> envelope = new RestEnvelope<EntrezGene>(entrezGene);
		envelope.setFields(fields);
		
		return new ResponseEntity<RestEnvelope<EntrezGene>>(envelope, HttpStatus.OK);
		
	}
	
	// Find by attribute
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"geneSymbol"})
	public HttpEntity<RestEnvelope<Resources<EntrezGene>>> findEntrezGenesByGeneSymbol(
			@RequestParam("geneSymbol") String geneSymbol,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<EntrezGene> entrezGeneList = new ArrayList<EntrezGene>();
		for (EntrezGene entrezGene: entrezGeneService.findEntrezGenesByGeneSymbol(geneSymbol, limit, offset)){
			entrezGene.add(linkTo(methodOn(EntrezGeneController.class)
					.findEntrezGeneById(entrezGene.getEntrezGeneId(), fields))
					.withSelfRel());
			entrezGeneList.add(entrezGene);
		}

		Resources<EntrezGene> resources = new Resources<EntrezGene>(entrezGeneList);
		resources.add(linkTo(methodOn(EntrezGeneController.class)
				.findAllEntrezGenes(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<EntrezGene>> envelope = new RestEnvelope<Resources<EntrezGene>>(resources);
		envelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<EntrezGene>>>(envelope, HttpStatus.OK);
		
	}
	
	// Add gene
	@RequestMapping(value = "", method = RequestMethod.POST)
	public HttpEntity<EntrezGene> addEntrezGene(@RequestBody EntrezGene entrezGene){
		
		entrezGene = entrezGeneService.addEntrezGene(entrezGene);
		
		if (entrezGene.getEntrezGeneId() != null && entrezGene.getEntrezGeneId() > 0){
			
			entrezGene.add(linkTo(methodOn(EntrezGeneController.class)
					.findEntrezGeneById(entrezGene.getEntrezGeneId(), null))
					.withSelfRel());
			return new ResponseEntity<EntrezGene>(entrezGene, HttpStatus.CREATED);
			
		} else {
			
			throw new RequestFailureException(
					40001, 
					"The provided entity could not be created.", 
					"The provided EntrezGene entity could not be created. ",
					"");
			
		}
		
	}
	
	// Update gene
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<EntrezGene> updateEntrezGene(
			@RequestBody EntrezGene entrezGene,
			@PathVariable("id") Integer id){
		
		Integer rowCount = entrezGeneService.updateEntrezGene(entrezGene);
		entrezGene.add(linkTo(methodOn(EntrezGeneController.class)
				.findEntrezGeneById(entrezGene.getEntrezGeneId(), null))
				.withSelfRel());
		
		if (rowCount == 0) {
			
			throw new ResourceNotFoundException(
					40401, 
					"The requested resource is not available.", 
					"No EntrezGene record found with ID: " + id, 
					"");
			
		} else if (rowCount > 0){
			
			return new ResponseEntity<EntrezGene>(entrezGene, HttpStatus.CREATED);
			
		} else {
			
			throw new MalformedEntityException(
					40601, 
					"The provided entity is malformed or incomplete and could not be updated.",
					"The provided EntrezGene entity is malformed or incomplete and could not be updated.",
					"");
			
		}
		
	}
	
	// Delete gene
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpEntity<EntrezGene> deleteEntrezGene(@PathVariable("id") Integer id){
		
		Integer rowCount = entrezGeneService.deleteEntrezGene(id);
		
		if (rowCount == null || rowCount < 1){
			throw new ResourceNotFoundException(
					40401, 
					"The requested resource is not available.", 
					"No EntrezGene record found with ID: " + id, 
					"");
		} else {
			return new ResponseEntity<EntrezGene>(HttpStatus.OK);
		}
		
	}
	
}
