package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.exceptions.MalformedEntityException;
import org.oncoblocks.restdemo.exceptions.RequestFailureException;
import org.oncoblocks.restdemo.exceptions.ResourceNotFoundException;
import org.oncoblocks.restdemo.models.CellLine;
import org.oncoblocks.restdemo.models.RestEnvelope;
import org.oncoblocks.restdemo.services.CellLineService;
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
 * Created by woemler on 10/2/14.
 */

@Controller
@ExposesResourceFor(CellLine.class)
@RequestMapping(value = "/api/v1/celllines", produces = {"application/json", "application/xml", "text/plain", "text/csv"})
public class CellLineController {
	
	@Autowired
	private CellLineService cellLineService;

	//// Read
	
	/**
	 * Returns all CellLine records.  Supports pagination and field filtering.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled CellLine objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<Resources<CellLine>>> findAllCellLines(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<CellLine> cellLineList = new ArrayList<CellLine>();
		for (CellLine cellLine: cellLineService.findAllCellLines(limit, offset)){
			cellLine.add(linkTo(methodOn(CellLineController.class)
					.findCellLineById(cellLine.getCellLineId(),fields))
					.withSelfRel());
			cellLineList.add(cellLine);
		}
		
		Resources<CellLine> resources = new Resources<CellLine>(cellLineList);
		resources.add(linkTo(methodOn(CellLineController.class)
				.findAllCellLines(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<CellLine>> responseEnvelope = new RestEnvelope<Resources<CellLine>>(resources);
		responseEnvelope.setFields(fields);
		
		return new ResponseEntity<RestEnvelope<Resources<CellLine>>>(responseEnvelope, HttpStatus.OK);
		
	}


	/**
	 * Fetch a single CellLine record by primary key ID.  Supports field filtering.
	 * @param id Primary key ID for the target cell line.
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with a single embedded HATEOAS-enabled CellLine object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<CellLine>> findCellLineById(
			@PathVariable("id") Integer id, 
			@RequestParam(value = "fields", required = false) String fields
	){

		CellLine cellLine = cellLineService.findCellLineById(id);
		
		if (cellLine == null){
			throw new ResourceNotFoundException(40401, "The requested resource is not available.", "No CellLine record found with ID: " + id, "");
		}

		cellLine.add(linkTo(methodOn(CellLineController.class)
				.findCellLineById(cellLine.getCellLineId(),fields))
				.withSelfRel());

		RestEnvelope<CellLine> responseEnvelope = new RestEnvelope<CellLine>(cellLine);
		responseEnvelope.setFields(fields);
		
		return new ResponseEntity<RestEnvelope<CellLine>>(responseEnvelope, HttpStatus.OK);
		
	}

	/**
	 * Fetch CellLine records by CCLE name.  Supports pagination and field filtering.
	 * @param ccleName CCLE name of the target cell line.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled CellLine objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"ccleName"})
	public HttpEntity<RestEnvelope<Resources<CellLine>>> findCellLinesByCcleName(
			@RequestParam("ccleName") String ccleName,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<CellLine> cellLineList = new ArrayList<CellLine>();
		for (CellLine cellLine: cellLineService.findCellLinesByCcleName(ccleName, limit, offset)){
			cellLine.add(linkTo(methodOn(CellLineController.class)
					.findCellLineById(cellLine.getCellLineId(),fields))
					.withSelfRel());
			cellLineList.add(cellLine);
		}

		Resources<CellLine> resources = new Resources<CellLine>(cellLineList);
		resources.add(linkTo(methodOn(CellLineController.class)
				.findAllCellLines(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<CellLine>> responseEnvelope = new RestEnvelope<Resources<CellLine>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<CellLine>>>(responseEnvelope, HttpStatus.OK);
		
	}

	//// Create
	
	/**
	 * Creates a new CellLine record and returns it.
	 * @param cellLine
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public HttpEntity<CellLine> addCellLine(@RequestBody CellLine cellLine){
		
		cellLine = cellLineService.addCellLine(cellLine);
		
		if (cellLine.getCellLineId() != null && cellLine.getCellLineId() > 0){
			
			cellLine.add(linkTo(methodOn(CellLineController.class)
					.findCellLineById(cellLine.getCellLineId(),null))
					.withSelfRel());
			return new ResponseEntity<CellLine>(cellLine, HttpStatus.CREATED);
			
		} else {
			
			throw new RequestFailureException(
					40001,
					"The provided entity could not be created.",
					"The provided CellLine entity could not be created. ",
					"");
			
		}
	}

	//// Update
	
	/**
	 * Updates and returns a CellLine record
	 * @param cellLine
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<CellLine> updateCellLine(
			@RequestBody CellLine cellLine,
			@PathVariable("id") Integer id
	){
		
		Integer rowCount = cellLineService.updateCellLine(cellLine);
		
		if (rowCount == 0) {
			
			throw new ResourceNotFoundException(
					40401, 
					"The requested resource is not available.", 
					"No CellLine record found with ID: " + id, 
					"");
			
		} else if (rowCount > 0){

			cellLine.add(linkTo(methodOn(CellLineController.class)
					.findCellLineById(cellLine.getCellLineId(),null))
					.withSelfRel());
			return new ResponseEntity<CellLine>(cellLine, HttpStatus.CREATED);
			
		} else {
			
			throw new MalformedEntityException(
					40601,
					"The provided entity is malformed or incomplete and could not be updated.",
					"The provided EntrezGene entity is malformed or incomplete and could not be updated.",
					"");
			
		}
		
	}

	//// Delete
	
	
	/**
	 * Deletes a cell line
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpEntity<CellLine> deleteCellLine(@PathVariable("id") Integer id){
		
		Integer rowCount = cellLineService.deleteCellLine(id);
		
		if (rowCount == null || rowCount < 1){
			
			throw new ResourceNotFoundException(
					40401, 
					"The requested resource is not available.", 
					"No CellLine record found with ID: " + id, 
					"");
			
		} else {
			
			return new ResponseEntity<CellLine>(HttpStatus.OK);
			
		}
		
	}
	
}
