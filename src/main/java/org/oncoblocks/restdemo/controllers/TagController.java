package org.oncoblocks.restdemo.controllers;

import org.oncoblocks.restdemo.exceptions.MalformedEntityException;
import org.oncoblocks.restdemo.exceptions.RequestFailureException;
import org.oncoblocks.restdemo.exceptions.ResourceNotFoundException;
import org.oncoblocks.restdemo.models.RestEnvelope;
import org.oncoblocks.restdemo.models.Tag;
import org.oncoblocks.restdemo.services.TagService;
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
 * Created by woemler on 11/10/14.
 */

@Controller
@ExposesResourceFor(Tag.class)
@RequestMapping(value = "/api/v1/tags", produces = {"application/json", "application/xml", "text/plain", "text/csv"})
public class TagController {

	@Autowired
	private TagService tagService;

	//// Read

	/**
	 * Returns all Tag records.  Supports pagination and field filtering.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled Tag objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<Resources<Tag>>> findAllTags(
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<Tag> tagList = new ArrayList<Tag>();
		for (Tag tag: tagService.findAllTags(limit, offset)){
			tag.add(linkTo(methodOn(TagController.class)
					.findTagById(tag.getTagId(),fields))
					.withSelfRel());
			tagList.add(tag);
		}

		Resources<Tag> resources = new Resources<Tag>(tagList);
		resources.add(linkTo(methodOn(TagController.class)
				.findAllTags(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<Tag>> responseEnvelope = new RestEnvelope<Resources<Tag>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<Tag>>>(responseEnvelope, HttpStatus.OK);

	}


	/**
	 * Fetch a single Tag record by primary key ID.  Supports field filtering.
	 * @param id Primary key ID for the target cell line.
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with a single embedded HATEOAS-enabled Tag object.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<RestEnvelope<Tag>> findTagById(
			@PathVariable("id") Integer id,
			@RequestParam(value = "fields", required = false) String fields
	){

		Tag tag = tagService.findTagById(id);

		if (tag == null){
			throw new ResourceNotFoundException(40401, "The requested resource is not available.", "No Tag record found with ID: " + id, "");
		}

		tag.add(linkTo(methodOn(TagController.class)
				.findTagById(tag.getTagId(),fields))
				.withSelfRel());

		RestEnvelope<Tag> responseEnvelope = new RestEnvelope<Tag>(tag);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Tag>>(responseEnvelope, HttpStatus.OK);

	}

	/**
	 * Fetch Tag by label.  Supports pagination and field filtering.
	 * @param label Name of the target tag.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled Tag objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"label"})
	public HttpEntity<RestEnvelope<Resources<Tag>>> findTagsByLabel(
			@RequestParam("label") String label,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<Tag> tagList = new ArrayList<Tag>();
		for (Tag tag: tagService.findTagsByLabel(label, limit, offset)){
			tag.add(linkTo(methodOn(TagController.class)
					.findTagById(tag.getTagId(),fields))
					.withSelfRel());
			tagList.add(tag);
		}

		Resources<Tag> resources = new Resources<Tag>(tagList);
		resources.add(linkTo(methodOn(TagController.class)
				.findAllTags(limit, offset, fields))
				.withSelfRel());

		RestEnvelope<Resources<Tag>> responseEnvelope = new RestEnvelope<Resources<Tag>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<Tag>>>(responseEnvelope, HttpStatus.OK);

	}

	/**
	 * Fetch Tag by sampleId.  Supports pagination and field filtering.
	 * @param sId Name of the target tag.
	 * @param limit Maximum number of records to return, defaults to all records.
	 * @param offset Record index from which to start, for use in pagination.  Defaults to 0 (first record).
	 * @param fields Comma-delimited list of fields to be included in the response.  Only listed fields are returned, including hypermedia links.
	 * @return HttpResponse entity with embedded HATEOAS-enabled Tag objects.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, params = {"sId"})
	public HttpEntity<RestEnvelope<Resources<Tag>>> findTagsBySample(
			@RequestParam("sId") Integer sId,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "fields", required = false) String fields
	){

		List<Tag> tagList = new ArrayList<Tag>();
		for (Tag tag: tagService.findTagsBySample(sId, limit, offset)){
			tag.add(linkTo(methodOn(TagController.class)
					.findTagById(tag.getTagId(),fields))
					.withSelfRel());
			tagList.add(tag);
		}

		Resources<Tag> resources = new Resources<Tag>(tagList);
		resources.add(linkTo(methodOn(TagController.class)
				.findAllTags(limit, offset, fields))
				.withSelfRel());
		resources.add(linkTo(methodOn(SampleController.class)
				.findSampleById(sId, fields))
				.withRel("sample"));

		RestEnvelope<Resources<Tag>> responseEnvelope = new RestEnvelope<Resources<Tag>>(resources);
		responseEnvelope.setFields(fields);

		return new ResponseEntity<RestEnvelope<Resources<Tag>>>(responseEnvelope, HttpStatus.OK);

	}

	//// Create

	/**
	 * Creates a new Tag record and returns it.
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public HttpEntity<Tag> addTag(@RequestBody Tag tag){

		tag = tagService.addTag(tag);

		if (tag.getTagId() != null && tag.getTagId() > 0){

			tag.add(linkTo(methodOn(TagController.class)
					.findTagById(tag.getTagId(),null))
					.withSelfRel());
			return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);

		} else {

			throw new RequestFailureException(
					40001,
					"The provided entity could not be created.",
					"The provided Tag entity could not be created. ",
					"");

		}
	}

	//// Update

	/**
	 * Updates and returns a Tag record
	 * @param tag
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Tag> updateTag(
			@RequestBody Tag tag,
			@PathVariable("id") Integer id
	){

		Integer rowCount = tagService.updateTag(tag);

		if (rowCount == 0) {

			throw new ResourceNotFoundException(
					40401,
					"The requested resource is not available.",
					"No Tag record found with ID: " + id,
					"");

		} else if (rowCount > 0){

			tag.add(linkTo(methodOn(TagController.class)
					.findTagById(tag.getTagId(),null))
					.withSelfRel());
			return new ResponseEntity<Tag>(tag, HttpStatus.CREATED);

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
	public HttpEntity<Tag> deleteTag(@PathVariable("id") Integer id){

		Integer rowCount = tagService.deleteTag(id);

		if (rowCount == null || rowCount < 1){

			throw new ResourceNotFoundException(
					40401,
					"The requested resource is not available.",
					"No Tag record found with ID: " + id,
					"");

		} else {

			return new ResponseEntity<Tag>(HttpStatus.OK);

		}

	}
	
}
