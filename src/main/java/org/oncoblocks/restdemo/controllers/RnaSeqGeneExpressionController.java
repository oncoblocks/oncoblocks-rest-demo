package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.exceptions.ResourceNotFoundException;
import org.oncoblocks.restdemo.models.CellLine;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.oncoblocks.restdemo.models.RnaSeqGeneExpression;
import org.oncoblocks.restdemo.services.CellLineService;
import org.oncoblocks.restdemo.services.EntrezGeneService;
import org.oncoblocks.restdemo.services.RnaSeqGeneExpressionService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 10/2/14.
 */

@Controller
@ExposesResourceFor(RnaSeqGeneExpression.class)
@RequestMapping(value = "/api/v1/expression/gene/rnaseq", produces = {"application/json", "application/xml", "text/plain", "text/csv"})
public class RnaSeqGeneExpressionController {
	
	@Autowired
	private RnaSeqGeneExpressionService rnaSeqGeneExpressionService;
	
	@Autowired
	private CellLineService cellLineService;
	
	@Autowired
	private EntrezGeneService entrezGeneService;

	//// Get instance
	
	/**
	 * Returns a single rnaseq gene expression data record
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<RnaSeqGeneExpression> findRnaSeqGeneExpressionById(
			@PathVariable("id") Integer id,
			@RequestParam(value = "fields", required = false) String fields){

		Set<String> fieldSet = new HashSet<>();
		if (fields != null && !fields.equals("")){
			for (String field: fields.split(",")){
				fieldSet.add(field);
			}
		}
		boolean showLinks = Boolean.valueOf(fields == null || fieldSet.contains("links"));
		
		RnaSeqGeneExpression rnaSeqGeneExpression = rnaSeqGeneExpressionService.findRnaSeqGeneExpressionById(id);
		
		if (rnaSeqGeneExpression == null){
			throw new ResourceNotFoundException(
					40401, 
					"The requested resource is not available.", 
					"No RnaSeqGeneExpression record found with ID: " + id, 
					"");
		}

		rnaSeqGeneExpression.setFieldSet(fieldSet);
		if (showLinks) {
			rnaSeqGeneExpression.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
					.findRnaSeqGeneExpressionById(rnaSeqGeneExpression.getRnaSeqGeneExpressionId(), fields))
					.withSelfRel());
			rnaSeqGeneExpression.add(linkTo(methodOn(EntrezGeneController.class)
					.findEntrezGeneById(rnaSeqGeneExpression.getEntrezGeneId(), null))
					.withRel("entrezGene"));
			rnaSeqGeneExpression.add(linkTo(methodOn(CellLineController.class)
					.findCellLineById(rnaSeqGeneExpression.getCellLineId(), null))
					.withRel("cellLine"));
		}
		
		return new ResponseEntity<>(rnaSeqGeneExpression, HttpStatus.OK);
		
	}
	
	//// Get collection
	
	/**
	 * Returns all RnaSeqGeneExpression records ... probably not advisable
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<Resources<RnaSeqGeneExpression>> findAllRnaSeqGeneExpression(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields){

		Set<String> fieldSet = new HashSet<>();
		if (fields != null && !fields.equals("")){
			for (String field: fields.split(",")){
				fieldSet.add(field);
			}
		}
		boolean showLinks = Boolean.valueOf(fields == null || fieldSet.contains("links"));

		List<RnaSeqGeneExpression> rnaSeqGeneExpressionList = new ArrayList<>();
		
		for (RnaSeqGeneExpression rnaSeqGeneExpression: rnaSeqGeneExpressionService.findAllRnaSeqGeneExpression(limit, offset)){
			rnaSeqGeneExpression.setFieldSet(fieldSet);
			if (showLinks) {
				rnaSeqGeneExpression.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
						.findRnaSeqGeneExpressionById(rnaSeqGeneExpression.getRnaSeqGeneExpressionId(), fields))
						.withSelfRel());
				rnaSeqGeneExpression.add(linkTo(methodOn(EntrezGeneController.class)
						.findEntrezGeneById(rnaSeqGeneExpression.getEntrezGeneId(), null))
						.withRel("entrezGene"));
				rnaSeqGeneExpression.add(linkTo(methodOn(CellLineController.class)
						.findCellLineById(rnaSeqGeneExpression.getCellLineId(), null))
						.withRel("cellLine"));
			}
			rnaSeqGeneExpressionList.add(rnaSeqGeneExpression);
		}
		
		Resources<RnaSeqGeneExpression> resources = new Resources<>(rnaSeqGeneExpressionList);
		if (showLinks){
			resources.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
					.findAllRnaSeqGeneExpression(limit, offset, fields))
					.withSelfRel());
		}
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}

	// Find by attributes
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"cellLineId"})
	public HttpEntity<Resources<RnaSeqGeneExpression>> findRnaSeqGeneExpressionByCellLine(
			@RequestParam("cellLineId") Integer cellLineId,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields){

		Set<String> fieldSet = new HashSet<>();
		if (fields != null && !fields.equals("")){
			for (String field: fields.split(",")){
				fieldSet.add(field);
			}
		}
		boolean showLinks = Boolean.valueOf(fields == null || fieldSet.contains("links"));

		List<RnaSeqGeneExpression> rnaSeqGeneExpressionList = new ArrayList<>();

		for (RnaSeqGeneExpression rnaSeqGeneExpression: rnaSeqGeneExpressionService.findRnaSeqGeneExpressionByCellLine(cellLineId, limit, offset)){
			rnaSeqGeneExpression.setFieldSet(fieldSet);
			if (showLinks) {
				rnaSeqGeneExpression.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
						.findRnaSeqGeneExpressionById(rnaSeqGeneExpression.getRnaSeqGeneExpressionId(), fields))
						.withSelfRel());
				rnaSeqGeneExpression.add(linkTo(methodOn(EntrezGeneController.class)
						.findEntrezGeneById(rnaSeqGeneExpression.getEntrezGeneId(), null))
						.withRel("entrezGene"));
				rnaSeqGeneExpression.add(linkTo(methodOn(CellLineController.class)
						.findCellLineById(rnaSeqGeneExpression.getCellLineId(), null))
						.withRel("cellLine"));
			}
			rnaSeqGeneExpressionList.add(rnaSeqGeneExpression);
		}

		Resources<RnaSeqGeneExpression> resources = new Resources<>(rnaSeqGeneExpressionList);
		if (showLinks){
			resources.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
					.findAllRnaSeqGeneExpression(limit, offset, fields))
					.withSelfRel());
		}

		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}

	@RequestMapping(value = "", method = RequestMethod.GET, params = {"entrezGeneId"})
	public HttpEntity<Resources<RnaSeqGeneExpression>> findRnaSeqGeneExpressionByGene(
			@RequestParam("entrezGeneId") Integer entrezGeneId,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields){

		Set<String> fieldSet = new HashSet<>();
		if (fields != null && !fields.equals("")){
			for (String field: fields.split(",")){
				fieldSet.add(field);
			}
		}
		boolean showLinks = Boolean.valueOf(fields == null || fieldSet.contains("links"));

		List<RnaSeqGeneExpression> rnaSeqGeneExpressionList = new ArrayList<>();

		for (RnaSeqGeneExpression rnaSeqGeneExpression: rnaSeqGeneExpressionService.findRnaSeqGeneExpressionByGene(entrezGeneId, limit, offset)){
			rnaSeqGeneExpression.setFieldSet(fieldSet);
			if (showLinks) {
				rnaSeqGeneExpression.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
						.findRnaSeqGeneExpressionById(rnaSeqGeneExpression.getRnaSeqGeneExpressionId(), fields))
						.withSelfRel());
				rnaSeqGeneExpression.add(linkTo(methodOn(EntrezGeneController.class)
						.findEntrezGeneById(rnaSeqGeneExpression.getEntrezGeneId(), null))
						.withRel("entrezGene"));
				rnaSeqGeneExpression.add(linkTo(methodOn(CellLineController.class)
						.findCellLineById(rnaSeqGeneExpression.getCellLineId(), null))
						.withRel("cellLine"));
			}
			rnaSeqGeneExpressionList.add(rnaSeqGeneExpression);
		}

		Resources<RnaSeqGeneExpression> resources = new Resources<>(rnaSeqGeneExpressionList);
		if (showLinks){
			resources.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
					.findAllRnaSeqGeneExpression(limit, offset, fields))
					.withSelfRel());
		}

		return new ResponseEntity<>(resources, HttpStatus.OK);

	}
	
	//// Relationships
	
	@RequestMapping(value = "/{id}/celllines/{cellLineId}", method = RequestMethod.GET)
	public HttpEntity<CellLine> findRnaSeqGeneExpressionCellLine(
			@PathVariable("id") Integer rnaSeqGeneExpressionId, 
			@PathVariable("cellLineId") Integer cellLineId,
			@RequestParam(value = "fields", required = false) String fields){

		Set<String> fieldSet = new HashSet<>();
		if (fields != null && !fields.equals("")){
			for (String field: fields.split(",")){
				fieldSet.add(field);
			}
		}
		boolean showLinks = Boolean.valueOf(fields == null || fieldSet.contains("links"));
		
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

		cellLine.setFieldSet(fieldSet);
		if (showLinks){
			cellLine.add(linkTo(methodOn(CellLineController.class)
					.findCellLineById(cellLine.getCellLineId(),fields))
					.withSelfRel());
			cellLine.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
					.findRnaSeqGeneExpressionById(rnaSeqGeneExpressionId, null))
					.withRel("rnaSeqGeneExpression"));
		}
		
		return new ResponseEntity<>(cellLine, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}/celllines", method = RequestMethod.GET)
	public HttpEntity<CellLine> findRNaSeqGeneExpressionCellLines(
			@PathVariable("id") Integer id,
			@RequestParam(value = "fields", required = false) String fields){
		
		RnaSeqGeneExpression rnaSeqGeneExpression = rnaSeqGeneExpressionService.findRnaSeqGeneExpressionById(id);
		
		if (rnaSeqGeneExpression == null){
			throw new ResourceNotFoundException(40401,
					"The requested resource is not available.",
					"No RnaSeqGeneExpression record found with ID: " + id,
					"");
		}
		
		return findRnaSeqGeneExpressionCellLine(id, rnaSeqGeneExpression.getCellLineId(), fields);
		
	}
	
	@RequestMapping(value = "/{id}/genes/{entrezGeneId}", method = RequestMethod.GET)
	public HttpEntity<EntrezGene> findRnaSeqGeneExpressionEntrezGene(
			@PathVariable("id") Integer rnaSeqGeneExpressionId, 
			@PathVariable("entrezGeneId") Integer entrezGeneId,
			@RequestParam(value = "fields", required = false) String fields){

		Set<String> fieldSet = new HashSet<>();
		if (fields != null && !fields.equals("")){
			for (String field: fields.split(",")){
				fieldSet.add(field);
			}
		}
		boolean showLinks = Boolean.valueOf(fields == null || fieldSet.contains("links"));
		
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
		
		entrezGene.setFieldSet(fieldSet);
		if (showLinks){
			entrezGene.add(linkTo(methodOn(EntrezGeneController.class)
					.findEntrezGeneById(entrezGene.getEntrezGeneId(), fields))
					.withSelfRel());
			entrezGene.add(linkTo(methodOn(RnaSeqGeneExpressionController.class)
					.findRnaSeqGeneExpressionById(rnaSeqGeneExpressionId, null))
					.withRel("rnaSeqGeneExpression"));
		}
		
		
		return new ResponseEntity<>(entrezGene, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}/genes", method = RequestMethod.GET)
	public HttpEntity<EntrezGene> findRnaSeqGeneExpressionEntrezGenes(
			@PathVariable("id") Integer id,
			@RequestParam(value = "fields", required = false) String fields){
		
		RnaSeqGeneExpression rnaSeqGeneExpression = rnaSeqGeneExpressionService.findRnaSeqGeneExpressionById(id);
		
		if (rnaSeqGeneExpression == null){
			throw new ResourceNotFoundException(40401,
					"The requested resource is not available.",
					"No RnaSeqGeneExpression record found with ID: " + id,
					"");
		}
		
		return findRnaSeqGeneExpressionEntrezGene(id, rnaSeqGeneExpression.getEntrezGeneId(), fields);
		
	}

	
}
