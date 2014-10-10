package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.hateoas.RnaSeqGeneExpressionResourceAssembler;
import org.oncoblocks.restdemo.models.RnaSeqGeneExpression;
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
	private RnaSeqGeneExpressionResourceAssembler rnaSeqGeneExpressionResourceAssembler;

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
