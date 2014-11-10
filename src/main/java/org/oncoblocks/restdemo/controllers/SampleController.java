package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.exceptions.MalformedEntityException;
import org.oncoblocks.restdemo.exceptions.RequestFailureException;
import org.oncoblocks.restdemo.exceptions.ResourceNotFoundException;
import org.oncoblocks.restdemo.models.Sample;
import org.oncoblocks.restdemo.models.RestEnvelope;
import org.oncoblocks.restdemo.services.SampleService;
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
@ExposesResourceFor(Sample.class)
@RequestMapping(value = "/api/v1/samples", produces = {"application/json", "application/xml", "text/plain", "text/csv"})
public class SampleController {
	
	@Autowired
	private SampleService sampleService;

	//// Read
	
	/**
	 * Returns all Sample records.  Supports pagination and field filtering.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled Sample objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<Resources<Sample>>> findAllSamples(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<Sample> sampleList = new ArrayList<Sample>();
		for (Sample sample: sampleService.findAllSamples(limit, offset)){
			sample.add(linkTo(methodOn(SampleController.class)
					.findSampleById(sample.getSid(),fields))
					.withSelfRel());
			sample.add(linkTo(methodOn(TagController.class)
					.findTagsBySample(sample.getSid(), limit, offset, fields))
					.withRel("tags"));
			sample.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(sample.getCancerTypeId(), fields))
					.withRel("cancerType"));
			sampleList.add(sample);
		}
		
		Resources<Sample> resources = new Resources<Sample>(sampleList);
		resources.add(linkTo(methodOn(SampleController.class)
				.findAllSamples(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<Sample>> responseEnvelope = new RestEnvelope<Resources<Sample>>(resources);
		responseEnvelope.setFields(fields);
		
		return new ResponseEntity<RestEnvelope<Resources<Sample>>>(responseEnvelope, HttpStatus.OK);
		
	}


	/**
	 * Fetch a single Sample record by primary key ID.  Supports field filtering.
	 * @param id Primary key ID for the target cell line.
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with a single embedded HATEOAS-enabled Sample object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<Sample>> findSampleById(
			@PathVariable("id") Integer id, 
			@RequestParam(value = "fields", required = false) String fields
	){

		Sample sample = sampleService.findSampleById(id);
		
		if (sample == null){
			throw new ResourceNotFoundException(40401, "The requested resource is not available.", "No Sample record found with ID: " + id, "");
		}

		sample.add(linkTo(methodOn(SampleController.class)
				.findSampleById(sample.getSid(),fields))
				.withSelfRel());
		sample.add(linkTo(methodOn(TagController.class)
				.findTagsBySample(sample.getSid(), null, null, fields))
				.withRel("tags"));
		sample.add(linkTo(methodOn(CancerTypeController.class)
				.findCancerTypeById(sample.getCancerTypeId(), fields))
				.withRel("cancerType"));

		RestEnvelope<Sample> responseEnvelope = new RestEnvelope<Sample>(sample);
		responseEnvelope.setFields(fields);
		
		return new ResponseEntity<RestEnvelope<Sample>>(responseEnvelope, HttpStatus.OK);
		
	}

	/**
	 * Fetch Sample name.  Supports pagination and field filtering.
	 * @param sampleId Name of the target sample.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled Sample objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"sampleId"})
	public HttpEntity<RestEnvelope<Resources<Sample>>> findSamplesBySampleId(
			@RequestParam("sampleId") String sampleId,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<Sample> sampleList = new ArrayList<Sample>();
		for (Sample sample: sampleService.findSamplesBySampleId(sampleId, limit, offset)){
			sample.add(linkTo(methodOn(SampleController.class)
					.findSampleById(sample.getSid(),fields))
					.withSelfRel());
			sample.add(linkTo(methodOn(TagController.class)
					.findTagsBySample(sample.getSid(), limit, offset, fields))
					.withRel("tags"));
			sample.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(sample.getCancerTypeId(), fields))
					.withRel("cancerType"));
			sampleList.add(sample);
		}

		Resources<Sample> resources = new Resources<Sample>(sampleList);
		resources.add(linkTo(methodOn(SampleController.class)
				.findAllSamples(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<Sample>> responseEnvelope = new RestEnvelope<Resources<Sample>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<Sample>>>(responseEnvelope, HttpStatus.OK);
		
	}

	/**
	 * Fetch Sample name.  Supports pagination and field filtering.
	 * @param study Name of the target sample.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled Sample objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"study"})
	public HttpEntity<RestEnvelope<Resources<Sample>>> findSamplesByStudy(
			@RequestParam("study") String study,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<Sample> sampleList = new ArrayList<Sample>();
		for (Sample sample: sampleService.findSamplesByStudy(study, limit, offset)){
			sample.add(linkTo(methodOn(SampleController.class)
					.findSampleById(sample.getSid(),fields))
					.withSelfRel());
			sample.add(linkTo(methodOn(TagController.class)
					.findTagsBySample(sample.getSid(), limit, offset, fields))
					.withRel("tags"));
			sample.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(sample.getCancerTypeId(), fields))
					.withRel("cancerType"));
			sampleList.add(sample);
		}

		Resources<Sample> resources = new Resources<Sample>(sampleList);
		resources.add(linkTo(methodOn(SampleController.class)
				.findAllSamples(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<Sample>> responseEnvelope = new RestEnvelope<Resources<Sample>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<Sample>>>(responseEnvelope, HttpStatus.OK);

	}


	/**
	 * Fetch Sample name.  Supports pagination and field filtering.
	 * @param cancerTypeId Name of the target sample.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled Sample objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"cancerTypeId"})
	public HttpEntity<RestEnvelope<Resources<Sample>>> findSamplesByCancerType(
			@RequestParam("cancerTypeId") Integer cancerTypeId,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<Sample> sampleList = new ArrayList<Sample>();
		for (Sample sample: sampleService.findSamplesByCancerType(cancerTypeId, limit, offset)){
			sample.add(linkTo(methodOn(SampleController.class)
					.findSampleById(sample.getSid(),fields))
					.withSelfRel());
			sample.add(linkTo(methodOn(TagController.class)
					.findTagsBySample(sample.getSid(), limit, offset, fields))
					.withRel("tags"));
			sample.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(sample.getCancerTypeId(), fields))
					.withRel("cancerType"));
			sampleList.add(sample);
		}

		Resources<Sample> resources = new Resources<Sample>(sampleList);
		resources.add(linkTo(methodOn(SampleController.class)
				.findAllSamples(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<Sample>> responseEnvelope = new RestEnvelope<Resources<Sample>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<Sample>>>(responseEnvelope, HttpStatus.OK);

	}

	//// Create
	
	/**
	 * Creates a new Sample record and returns it.
	 * @param sample
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public HttpEntity<Sample> addSample(@RequestBody Sample sample){
		
		sample = sampleService.addSample(sample);
		
		if (sample.getSid() != null && sample.getSid() > 0){
			
			sample.add(linkTo(methodOn(SampleController.class)
					.findSampleById(sample.getSid(),null))
					.withSelfRel());
			sample.add(linkTo(methodOn(TagController.class)
					.findTagsBySample(sample.getSid(), null, null, null))
					.withRel("tags"));
			sample.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(sample.getCancerTypeId(), null))
					.withRel("cancerType"));
			return new ResponseEntity<Sample>(sample, HttpStatus.CREATED);
			
		} else {
			
			throw new RequestFailureException(
					40001,
					"The provided entity could not be created.",
					"The provided Sample entity could not be created. ",
					"");
			
		}
	}

	//// Update
	
	/**
	 * Updates and returns a Sample record
	 * @param sample
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Sample> updateSample(
			@RequestBody Sample sample,
			@PathVariable("id") Integer id
	){
		
		Integer rowCount = sampleService.updateSample(sample);
		
		if (rowCount == 0) {
			
			throw new ResourceNotFoundException(
					40401, 
					"The requested resource is not available.", 
					"No Sample record found with ID: " + id, 
					"");
			
		} else if (rowCount > 0){

			sample.add(linkTo(methodOn(SampleController.class)
					.findSampleById(sample.getSid(),null))
					.withSelfRel());
			sample.add(linkTo(methodOn(TagController.class)
					.findTagsBySample(sample.getSid(), null, null, null))
					.withRel("tags"));
			sample.add(linkTo(methodOn(CancerTypeController.class)
					.findCancerTypeById(sample.getCancerTypeId(), null))
					.withRel("cancerType"));
			return new ResponseEntity<Sample>(sample, HttpStatus.CREATED);
			
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
	public HttpEntity<Sample> deleteSample(@PathVariable("id") Integer id){
		
		Integer rowCount = sampleService.deleteSample(id);
		
		if (rowCount == null || rowCount < 1){
			
			throw new ResourceNotFoundException(
					40401, 
					"The requested resource is not available.", 
					"No Sample record found with ID: " + id, 
					"");
			
		} else {
			
			return new ResponseEntity<Sample>(HttpStatus.OK);
			
		}
		
	}
	
}
