package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.exceptions.ResourceNotFoundException;
import org.oncoblocks.restdemo.hateoas.CellLineResourceAssembler;
import org.oncoblocks.restdemo.hateoas.EntrezGeneResourceAssembler;
import org.oncoblocks.restdemo.hateoas.RnaSeqGeneExpressionResourceAssembler;
import org.oncoblocks.restdemo.models.CellLine;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.oncoblocks.restdemo.models.RnaSeqGeneExpression;
import org.oncoblocks.restdemo.services.CellLineService;
import org.oncoblocks.restdemo.services.EntrezGeneService;
import org.oncoblocks.restdemo.services.RnaSeqGeneExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 10/2/14.
 */

@Controller
@ExposesResourceFor(RnaSeqGeneExpression.class)
@RequestMapping(value = "/api/expression/gene/rnaseq", produces = MediaType.APPLICATION_JSON_VALUE)
public class RnaSeqGeneExpressionController {
	
	@Autowired
	private RnaSeqGeneExpressionService rnaSeqGeneExpressionService;
	
	@Autowired
	private CellLineService cellLineService;
	
	@Autowired
	private EntrezGeneService entrezGeneService;
	
	@Autowired
	private RnaSeqGeneExpressionResourceAssembler rnaSeqGeneExpressionResourceAssembler;
	
	@Autowired
	private CellLineResourceAssembler cellLineResourceAssembler;
	
	@Autowired
	private EntrezGeneResourceAssembler entrezGeneResourceAssembler;

	//// Get instance
	
	/**
	 * Returns a single rnaseq gene expression data record
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<Resource<RnaSeqGeneExpression>> findRnaSeqGeneExpressionById(@PathVariable("id") Integer id){
		
		RnaSeqGeneExpression rnaSeqGeneExpression = rnaSeqGeneExpressionService.findRnaSeqGeneExpressionById(id);
		
		if (rnaSeqGeneExpression == null){
			throw new ResourceNotFoundException(40401, "The requested resource is not available.", "No RnaSeqGeneExpression record found with ID: " + id, "");
		}
		
		Resource<RnaSeqGeneExpression> resource = rnaSeqGeneExpressionResourceAssembler.toResource(rnaSeqGeneExpression);
		return new ResponseEntity<>(resource, HttpStatus.OK);
		
	}
	
	//// Get collection
	
	/**
	 * Returns all RnaSeqGeneExpression records ... probably not advisable
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<RnaSeqGeneExpression>>> findAllRnaSeqGeneExpression(){

		Collection<Resource<RnaSeqGeneExpression>> resourceCollection = new ArrayList<>();
		
		for (RnaSeqGeneExpression rnaSeqGeneExpression: rnaSeqGeneExpressionService.findAllRnaSeqGeneExpression()){
			resourceCollection.add(rnaSeqGeneExpressionResourceAssembler.toResource(rnaSeqGeneExpression));
		}
		
		Resources<Resource<RnaSeqGeneExpression>> resources = new Resources<>(resourceCollection);
		resources.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
				.findAllRnaSeqGeneExpression())
				.withSelfRel());
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}

	// Find by attributes
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"cellLineId"})
	public HttpEntity<Resources<Resource<RnaSeqGeneExpression>>> findRnaSeqGeneExpressionByCellLine(@RequestParam("cellLineId") Integer cellLineId){

		Collection<Resource<RnaSeqGeneExpression>> resourceCollection = new ArrayList<>();

		for (RnaSeqGeneExpression rnaSeqGeneExpression: rnaSeqGeneExpressionService.findRnaSeqGeneExpressionByCellLine(
				cellLineId)){
			resourceCollection.add(rnaSeqGeneExpressionResourceAssembler.toResource(rnaSeqGeneExpression));
		}

		Resources<Resource<RnaSeqGeneExpression>> resources = new Resources<>(resourceCollection);
		resources.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
				.findAllRnaSeqGeneExpression())
				.withSelfRel());

		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}

	@RequestMapping(value = "", method = RequestMethod.GET, params = {"entrezGeneId"})
	public HttpEntity<Resources<Resource<RnaSeqGeneExpression>>> findRnaSeqGeneExpressionByGene(@RequestParam("entrezGeneId") Integer entrezGeneId){

		Collection<Resource<RnaSeqGeneExpression>> resourceCollection = new ArrayList<>();

		for (RnaSeqGeneExpression rnaSeqGeneExpression: rnaSeqGeneExpressionService.findRnaSeqGeneExpressionByGene(
				entrezGeneId)){
			resourceCollection.add(rnaSeqGeneExpressionResourceAssembler.toResource(rnaSeqGeneExpression));
		}

		Resources<Resource<RnaSeqGeneExpression>> resources = new Resources<>(resourceCollection);
		resources.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
				.findAllRnaSeqGeneExpression())
				.withSelfRel());

		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}
	
	//// Relationships
	
	@RequestMapping(value = "/{id}/celllines/{cellLineId}", method = RequestMethod.GET)
	public HttpEntity<Resource<CellLine>> findRnaSeqGeneExpressionCellLine(
			@PathVariable("id") Integer rnaSeqGeneExpressionId, 
			@PathVariable("cellLineId") Integer cellLineId){
		
		RnaSeqGeneExpression rnaSeqGeneExpression 
				= rnaSeqGeneExpressionService.findRnaSeqGeneExpressionById(rnaSeqGeneExpressionId);
		
		if (rnaSeqGeneExpression == null){
			throw new ResourceNotFoundException(40401, 
					"The requested resource is not available.", 
					"No RnaSeqGeneExpression record found with ID: " + rnaSeqGeneExpressionId, 
					"");
		}
		
		CellLine cellLine = cellLineService.findCellLineById(cellLineId);
		
		if (cellLine == null){
			throw new ResourceNotFoundException(40402, 
					"The requested resource is not available.", 
					"No CellLine record with ID: " + cellLineId + " is associated with RnaSeqGeneExpression record: " + rnaSeqGeneExpressionId, 
					"");
		}
		
		Resource<CellLine> resource = cellLineResourceAssembler.toResource(cellLine);
		resource.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
				.findRnaSeqGeneExpressionById(rnaSeqGeneExpressionId))
				.withRel("rnaSeqGeneExpression"));
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}/celllines", method = RequestMethod.GET)
	public HttpEntity<Resource<CellLine>> findRNaSeqGeneExpressionCellLines(@PathVariable("id") Integer id){
		
		RnaSeqGeneExpression rnaSeqGeneExpression = rnaSeqGeneExpressionService.findRnaSeqGeneExpressionById(id);
		
		if (rnaSeqGeneExpression == null){
			throw new ResourceNotFoundException(40401,
					"The requested resource is not available.",
					"No RnaSeqGeneExpression record found with ID: " + id,
					"");
		}
		
		return findRnaSeqGeneExpressionCellLine(id, rnaSeqGeneExpression.getCellLineId());
		
	}
	
	@RequestMapping(value = "/{id}/genes/{entrezGeneId}", method = RequestMethod.GET)
	public HttpEntity<Resource<EntrezGene>> findRnaSeqGeneExpressionEntrezGene(
			@PathVariable("id") Integer rnaSeqGeneExpressionId, 
			@PathVariable("entrezGeneId") Integer entrezGeneId){
		
		RnaSeqGeneExpression rnaSeqGeneExpression 
				= rnaSeqGeneExpressionService.findRnaSeqGeneExpressionById(rnaSeqGeneExpressionId);
		
		if (rnaSeqGeneExpression == null){
			throw new ResourceNotFoundException(40401,
					"The requested resource is not available.",
					"No RnaSeqGeneExpression record found with ID: " + rnaSeqGeneExpressionId,
					"");
		}
		
		EntrezGene entrezGene = entrezGeneService.findEntrezGeneById(entrezGeneId);
		
		if (entrezGene == null){
			throw new ResourceNotFoundException(40402,
					"The requested resource is not available.",
					"No EntrezGene record with ID: " + entrezGeneId + " is associated with RnaSeqGeneExpression record: " + rnaSeqGeneExpressionId,
					"");
		}
		
		Resource<EntrezGene> resource = entrezGeneResourceAssembler.toResource(entrezGene);
		resource.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
				.findRnaSeqGeneExpressionById(rnaSeqGeneExpressionId))
				.withRel("rnaSeqGeneExpression"));
		
		return new ResponseEntity<>(resource, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}/genes", method = RequestMethod.GET)
	public HttpEntity<Resource<EntrezGene>> findRnaSeqGeneExpressionEntrezGenes(@PathVariable("id") Integer id){
		
		RnaSeqGeneExpression rnaSeqGeneExpression = rnaSeqGeneExpressionService.findRnaSeqGeneExpressionById(id);
		
		if (rnaSeqGeneExpression == null){
			throw new ResourceNotFoundException(40401,
					"The requested resource is not available.",
					"No RnaSeqGeneExpression record found with ID: " + id,
					"");
		}
		
		return findRnaSeqGeneExpressionEntrezGene(id, rnaSeqGeneExpression.getEntrezGeneId());
		
	}

	
	
	
	// Add rna seq gene expression
	/*
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<RnaSeqGeneExpression> addRnaSeqGeneExpression(@RequestBody RnaSeqGeneExpression rnaSeqGeneExpression){
		Integer rowCount = rnaSeqGeneExpressionService.addRnaSeqGeneExpression(rnaSeqGeneExpression);
		if (rowCount != null && rowCount > 0){
			return new ResponseEntity<RnaSeqGeneExpression>(rnaSeqGeneExpression, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<RnaSeqGeneExpression>(rnaSeqGeneExpression, HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
}
