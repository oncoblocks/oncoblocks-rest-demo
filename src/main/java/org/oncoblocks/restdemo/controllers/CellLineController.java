package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.exceptions.MalformedEntityException;
import org.oncoblocks.restdemo.exceptions.RequestFailureException;
import org.oncoblocks.restdemo.exceptions.ResourceNotFoundException;
import org.oncoblocks.restdemo.hateoas.CellLineResourceAssembler;
import org.oncoblocks.restdemo.models.CellLine;
import org.oncoblocks.restdemo.services.CellLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 10/2/14.
 */

@Controller
@ExposesResourceFor(CellLine.class)
@RequestMapping(value = "/api/v1/celllines", produces = {"application/json", "application/xml", "text/plain", "text/csv"})
public class CellLineController {
	
	@Autowired
	private CellLineService cellLineService;
	
	@Autowired
	private CellLineResourceAssembler cellLineResourceAssembler;

	/**
	 * Returns all CellLine records.
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<CellLine>>> findAllCellLines(){

		Collection<Resource<CellLine>> resourceCollection = new ArrayList<>();
		
		for (CellLine cellLine: cellLineService.findAllCellLines()){
			resourceCollection.add(cellLineResourceAssembler.toResource(cellLine));
		}
		
		Resources<Resource<CellLine>> resources = new Resources<>(resourceCollection);
		resources.add(linkTo(methodOn(CellLineController.class).findAllCellLines()).withSelfRel());
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}


	/**
	 * Fetch a single CellLine record by primary key
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<Resource<CellLine>> findCellLineById(@PathVariable("id") Integer id){
		
		CellLine cellLine = cellLineService.findCellLineById(id);
		
		if (cellLine == null){
			throw new ResourceNotFoundException(40401, "The requested resource is not available.", "No CellLine record found with ID: " + id, "");
		}
		
		return new ResponseEntity<>(cellLineResourceAssembler.toResource(cellLine), HttpStatus.OK);
		
	}

	/**
	 * Fetch CellLine records by CCLE name
	 * @param ccleName
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"ccleName"})
	public HttpEntity<Resources<Resource<CellLine>>> findCellLinesByCcleName(
			@RequestParam("ccleName") String ccleName){

		Collection<Resource<CellLine>> resourceCollection = new ArrayList<>();

		for (CellLine cellLine: cellLineService.findCellLinesByCcleName(ccleName)){
			resourceCollection.add(cellLineResourceAssembler.toResource(cellLine));
		}

		Resources<Resource<CellLine>> resources = new Resources<>(resourceCollection);
		resources.add(linkTo(methodOn(CellLineController.class).findAllCellLines()).withSelfRel());

		return new ResponseEntity<>(resources, HttpStatus.OK);
		
	}

	/**
	 * Creates a new CellLine record and returns it.
	 * @param cellLine
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public HttpEntity<Resource<CellLine>> addCellLine(@RequestBody CellLine cellLine){
		
		cellLine = cellLineService.addCellLine(cellLine);
		Resource<CellLine> resource = cellLineResourceAssembler.toResource(cellLine);
		
		if (cellLine.getCellLineId() != null && cellLine.getCellLineId() > 0){
			return new ResponseEntity<>(resource, HttpStatus.CREATED);
		} else {
			throw new RequestFailureException(
					40001,
					"The provided entity could not be created.",
					"The provided CellLine entity could not be created. ",
					"");
		}
	}

	/**
	 * Updates and returns a CellLine record
	 * @param cellLine
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Resource<CellLine>> updateCellLine(
			@RequestBody CellLine cellLine,
			@PathVariable("id") Integer id){
		
		Integer rowCount = cellLineService.updateCellLine(cellLine);
		Resource<CellLine> resource = cellLineResourceAssembler.toResource(cellLine);
		
		if (rowCount == 0) {
			throw new ResourceNotFoundException(40401, "The requested resource is not available.", "No CellLine record found with ID: " + id, "");
		} else if (rowCount > 0){
			return new ResponseEntity<>(resource, HttpStatus.CREATED);
		} else {
			throw new MalformedEntityException(
					40601,
					"The provided entity is malformed or incomplete and could not be updated.",
					"The provided EntrezGene entity is malformed or incomplete and could not be updated.",
					"");
		}
		
	}

	/**
	 * Deletes a cell line
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpEntity<Resource<CellLine>> deleteCellLine(@PathVariable("id") Integer id){
		
		Integer rowCount = cellLineService.deleteCellLine(id);
		
		if (rowCount == null || rowCount < 1){
			throw new ResourceNotFoundException(40401, "The requested resource is not available.", "No CellLine record found with ID: " + id, "");
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
	}
	
}
