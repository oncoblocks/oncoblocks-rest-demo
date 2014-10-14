package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.hateoas.EntrezGeneResourceAssembler;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.oncoblocks.restdemo.services.EntrezGeneService;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 9/26/14.
 */

@Controller
@ExposesResourceFor(EntrezGene.class)
@RequestMapping(value = "/api/genes", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntrezGeneController {
	
	@Autowired
	private EntrezGeneService entrezGeneService;
	
	@Autowired
	private EntrezGeneResourceAssembler entrezGeneResourceAssembler;
	
	// Find all
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<EntrezGene>>> findAllEntrezGenes(){
		
		Collection<Resource<EntrezGene>> resourceCollection = new ArrayList<>();
		
		for (EntrezGene entrezGene: entrezGeneService.findAllEntrezGenes()){
			resourceCollection.add(entrezGeneResourceAssembler.toResource(entrezGene));
		}
		
		Resources<Resource<EntrezGene>> resources = new Resources<>(resourceCollection);
		resources.add(linkTo(methodOn(EntrezGeneController.class).findAllEntrezGenes()).withSelfRel());
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}
	
	// Find by ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<Resource<EntrezGene>> findEntrezGeneById(@PathVariable("id") Integer id){
		
		EntrezGene entrezGene = entrezGeneService.findEntrezGeneById(id);
		
		if (entrezGene == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Resource<EntrezGene> entrezGeneResource = entrezGeneResourceAssembler.toResource(entrezGene);
		
		return new ResponseEntity<>(entrezGeneResource, HttpStatus.OK);
		
	}
	
	// Find by attribute
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"geneSymbol"})
	public HttpEntity<Resources<Resource<EntrezGene>>> findEntrezGenesByGeneSymbol(
			@RequestParam("geneSymbol") String geneSymbol){
		
		Collection<Resource<EntrezGene>> resourceCollection = new ArrayList<>();
		
		for (EntrezGene entrezGene: entrezGeneService.findEntrezGenesByGeneSymbol(geneSymbol)){
			resourceCollection.add(entrezGeneResourceAssembler.toResource(entrezGene));
		}
		
		Resources<Resource<EntrezGene>> resources = new Resources<>(resourceCollection);
		resources.add(linkTo(methodOn(EntrezGeneController.class).findAllEntrezGenes()).withSelfRel());
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}
	
	// Add gene
	@RequestMapping(value = "", method = RequestMethod.POST)
	public HttpEntity<Resource<EntrezGene>> addEntrezGene(@RequestBody EntrezGene entrezGene){
		
		entrezGene = entrezGeneService.addEntrezGene(entrezGene);
		
		if (entrezGene.getEntrezGeneId() != null && entrezGene.getEntrezGeneId() > 0){
			return new ResponseEntity<>(entrezGeneResourceAssembler.toResource(entrezGene), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(entrezGeneResourceAssembler.toResource(entrezGene), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	// Update gene
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Resource<EntrezGene>> updateEntrezGene(@RequestBody EntrezGene entrezGene){
		
		Integer rowCount = entrezGeneService.updateEntrezGene(entrezGene);
		Resource<EntrezGene> resource = entrezGeneResourceAssembler.toResource(entrezGene);
		
		if (rowCount == 0) {
			return new ResponseEntity<>(resource, HttpStatus.NOT_FOUND);
		} else if (rowCount > 0){
			return new ResponseEntity<>(resource, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(resource, HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	// Delete gene
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpEntity<Resource<EntrezGene>> deleteEntrezGene(@PathVariable("id") Integer id){
		
		Integer rowCount = entrezGeneService.deleteEntrezGene(id);
		
		if (rowCount == null || rowCount < 1){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
}
