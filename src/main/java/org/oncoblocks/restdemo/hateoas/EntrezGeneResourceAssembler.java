package org.oncoblocks.restdemo.hateoas;

import org.oncoblocks.restdemo.controllers.EntrezGeneController;
import org.oncoblocks.restdemo.models.EntrezGene;
import org.springframework.hateoas.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 10/7/14.
 */

@Component
public class EntrezGeneResourceAssembler implements ResourceAssembler<EntrezGene, Resource<EntrezGene>> {
	
	private Class<EntrezGeneController> controllerClass = EntrezGeneController.class;

	@Override public Resource<EntrezGene> toResource(EntrezGene entrezGene) {
		Integer entrezGeneId = entrezGene.getEntrezGeneId();
		Resource<EntrezGene> entrezGeneResource = new Resource<>(entrezGene);
		Link selfLink = linkTo(methodOn(controllerClass).findEntrezGeneById(entrezGeneId)).withSelfRel();
		entrezGeneResource.add(selfLink);
		return entrezGeneResource;
	}
}
