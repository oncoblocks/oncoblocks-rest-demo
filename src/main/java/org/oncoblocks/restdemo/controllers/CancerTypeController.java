package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.exceptions.MalformedEntityException;
import org.oncoblocks.restdemo.exceptions.RequestFailureException;
import org.oncoblocks.restdemo.exceptions.ResourceNotFoundException;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.oncoblocks.restdemo.models.RestEnvelope;
import org.oncoblocks.restdemo.models.CancerType;
import org.oncoblocks.restdemo.services.CancerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 11/10/14.
 */

@Controller
@ExposesResourceFor(CancerType.class)
@RequestMapping(value = "/api/v1/cancerTypes", produces = {"application/json", "application/xml", "text/plain", "text/csv"})
@Api(value="Cancer Types", description="Operations on cancer types")
public class CancerTypeController {

	@Autowired
	private CancerTypeService cancerTypeService;

	//// Read

	/**
	 * Returns all CancerType records.  Supports pagination and field filtering.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled CancerType objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "Gets all cancer types.", notes = "Gets all cancer types in the database", response = CancerType.class)	
	public HttpEntity<RestEnvelope<Resources<CancerType>>> findAllCancerTypes(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<CancerType> cancerTypeList = new ArrayList<CancerType>();
		for (CancerType cancerType: cancerTypeService.findAllCancerTypes(limit, offset)){
			cancerType.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(cancerType.getCancerTypeId(),fields))
					.withSelfRel());
			cancerTypeList.add(cancerType);
		}

		Resources<CancerType> resources = new Resources<CancerType>(cancerTypeList);
		resources.add(linkTo(methodOn(CancerTypeController.class)
				.findAllCancerTypes(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<CancerType>> responseEnvelope = new RestEnvelope<Resources<CancerType>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<CancerType>>>(responseEnvelope, HttpStatus.OK);

	}


	/**
	 * Fetch a single CancerType record by primary key ID.  Supports field filtering.
	 * @param id Primary key ID for the target cell line.
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with a single embedded HATEOAS-enabled CancerType object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Finds cancer type by ID.", notes = "Finds cancer type by ID.", response = CancerType.class)	
	public HttpEntity<RestEnvelope<CancerType>> findCancerTypeById(
			@PathVariable("id") Integer id,
			@RequestParam(value = "fields", required = false) String fields
	){

		CancerType cancerType = cancerTypeService.findCancerTypeById(id);

		if (cancerType == null){
			throw new ResourceNotFoundException(40401, "The requested resource is not available.", "No CancerType record found with ID: " + id, "");
		}

		cancerType.add(linkTo(methodOn(CancerTypeController.class)
				.findCancerTypeById(cancerType.getCancerTypeId(),fields))
				.withSelfRel());

		RestEnvelope<CancerType> responseEnvelope = new RestEnvelope<CancerType>(cancerType);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<CancerType>>(responseEnvelope, HttpStatus.OK);

	}

	/**
	 * Fetch CancerType by label.  Supports pagination and field filtering.
	 * @param name Name of the target cancerType.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled CancerType objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"name"})
	@ApiOperation(value = "Finds cancer type by label.", notes = "Finds cancer type by label.", response = CancerType.class)	
	public HttpEntity<RestEnvelope<Resources<CancerType>>> findCancerTypesByLabel(
			@RequestParam("name") String name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<CancerType> cancerTypeList = new ArrayList<CancerType>();
		for (CancerType cancerType: cancerTypeService.findCancerTypesByName(name, limit, offset)){
			cancerType.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(cancerType.getCancerTypeId(),fields))
					.withSelfRel());
			cancerTypeList.add(cancerType);
		}

		Resources<CancerType> resources = new Resources<CancerType>(cancerTypeList);
		resources.add(linkTo(methodOn(CancerTypeController.class)
				.findAllCancerTypes(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<CancerType>> responseEnvelope = new RestEnvelope<Resources<CancerType>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<CancerType>>>(responseEnvelope, HttpStatus.OK);

	}

	/**
	 * Fetch CancerType by parent ID.  Supports pagination and field filtering.
	 * @param parentId parentID the target cancerType.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled CancerType objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"parentId"})
	@ApiOperation(value = "Finds cancer type by parent ID.", notes = "Finds cancer type by parent ID.", response = CancerType.class)	
	public HttpEntity<RestEnvelope<Resources<CancerType>>> findCancerTypesByParentId(
			@RequestParam("parentId") Integer parentId,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<CancerType> cancerTypeList = new ArrayList<CancerType>();
		for (CancerType cancerType: cancerTypeService.findCancerTypesByParentId(parentId, limit, offset)){
			cancerType.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(cancerType.getCancerTypeId(),fields))
					.withSelfRel());
			cancerTypeList.add(cancerType);
		}

		Resources<CancerType> resources = new Resources<CancerType>(cancerTypeList);
		resources.add(linkTo(methodOn(CancerTypeController.class)
				.findAllCancerTypes(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<CancerType>> responseEnvelope = new RestEnvelope<Resources<CancerType>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<CancerType>>>(responseEnvelope, HttpStatus.OK);

	}

	//// Create

	/**
	 * Creates a new CancerType record and returns it.
	 * @param cancerType
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "Adds a new cancer type.", notes = "Adds a new cancer type.", response = CancerType.class)	
	public HttpEntity<CancerType> addCancerType(@RequestBody CancerType cancerType){

		cancerType = cancerTypeService.addCancerType(cancerType);

		if (cancerType.getCancerTypeId() != null && cancerType.getCancerTypeId() > 0){

			cancerType.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(cancerType.getCancerTypeId(),null))
					.withSelfRel());
			return new ResponseEntity<CancerType>(cancerType, HttpStatus.CREATED);

		} else {

			throw new RequestFailureException(
					40001,
					"The provided entity could not be created.",
					"The provided CancerType entity could not be created. ",
					"");

		}
	}

	//// Update

	/**
	 * Updates and returns a CancerType record
	 * @param cancerType
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Updates the specified cancer type.", notes = "Updates the specified cancer type.", response = CancerType.class)	
	public HttpEntity<CancerType> updateCancerType(
			@RequestBody CancerType cancerType,
			@PathVariable("id") Integer id
	){

		Integer rowCount = cancerTypeService.updateCancerType(cancerType);

		if (rowCount == 0) {

			throw new ResourceNotFoundException(
					40401,
					"The requested resource is not available.",
					"No CancerType record found with ID: " + id,
					"");

		} else if (rowCount > 0){

			cancerType.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(cancerType.getCancerTypeId(),null))
					.withSelfRel());
			return new ResponseEntity<CancerType>(cancerType, HttpStatus.CREATED);

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
	 * Deletes a cancer type
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Deletes the specified cancer type.", notes = "Deletes the specified cancer type.", response = CancerType.class)	
	public HttpEntity<CancerType> deleteCancerType(@PathVariable("id") Integer id){

		Integer rowCount = cancerTypeService.deleteCancerType(id);

		if (rowCount == null || rowCount < 1){

			throw new ResourceNotFoundException(
					40401,
					"The requested resource is not available.",
					"No CancerType record found with ID: " + id,
					"");

		} else {

			return new ResponseEntity<CancerType>(HttpStatus.OK);

		}

	}
	
}
