package org.oncoblocks.restdemo.hateoas;

import org.oncoblocks.restdemo.controllers.CellLineController;
import org.oncoblocks.restdemo.models.CellLine;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 10/8/14.
 */

@Component
public class CellLineResourceAssembler implements ResourceAssembler<CellLine, Resource<CellLine>> {
	
	private Class<CellLineController> controllerClass = CellLineController.class;

	@Override public Resource<CellLine> toResource(CellLine cellLine) {
		Integer cellLineId = cellLine.getCellLineId();
		Resource<CellLine> cellLineResource = new Resource<>(cellLine);
		Link selfLink = linkTo(methodOn(controllerClass).findCellLineById(cellLineId,null)).withSelfRel();
		cellLineResource.add(selfLink);
		return cellLineResource;
	}
}
