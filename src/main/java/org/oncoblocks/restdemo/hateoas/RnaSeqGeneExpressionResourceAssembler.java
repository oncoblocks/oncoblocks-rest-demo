package org.oncoblocks.restdemo.hateoas;

import org.oncoblocks.restdemo.controllers.CellLineController;
import org.oncoblocks.restdemo.controllers.EntrezGeneController;
import org.oncoblocks.restdemo.controllers.RnaSeqGeneExpressionController;
import org.oncoblocks.restdemo.models.RnaSeqGeneExpression;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by woemler on 10/8/14.
 */

@Component
public class RnaSeqGeneExpressionResourceAssembler 
		implements ResourceAssembler<RnaSeqGeneExpression, Resource<RnaSeqGeneExpression>> {
	
	private Class<EntrezGeneController> entrezGeneControllerClass = EntrezGeneController.class;
	private Class<CellLineController> cellLineControllerClass = CellLineController.class;

	@Override public Resource<RnaSeqGeneExpression> toResource(
			RnaSeqGeneExpression rnaSeqGeneExpression) {
		
		Resource<RnaSeqGeneExpression> rnaSeqGeneExpressionResource = new Resource<>(rnaSeqGeneExpression);
		Link selfLink = linkTo(methodOn(RnaSeqGeneExpressionController.class)
				.findRnaSeqGeneExpressionById(rnaSeqGeneExpression.getRnaSeqGeneExpressionId()))
				.withSelfRel();
		Link entrezGeneLink = linkTo(methodOn(entrezGeneControllerClass)
				.findEntrezGeneById(rnaSeqGeneExpression.getEntrezGeneId()))
				.withRel("entrezGene");
		Link cellLineLink = linkTo(methodOn(cellLineControllerClass)
				.findCellLineById(rnaSeqGeneExpression.getCellLineId()))
				.withRel("cellLine");
		rnaSeqGeneExpressionResource.add(selfLink);
		rnaSeqGeneExpressionResource.add(entrezGeneLink);
		rnaSeqGeneExpressionResource.add(cellLineLink);
		
		return rnaSeqGeneExpressionResource;
		
	}
}
