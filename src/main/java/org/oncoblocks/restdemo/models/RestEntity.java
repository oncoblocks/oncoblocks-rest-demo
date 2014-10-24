package org.oncoblocks.restdemo.models;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.hateoas.ResourceSupport;

/**
 * Abstract superclass for domain models used in REST API.  Adds annotations and methods for more 
 *  precise serialization.
 * 
 * Created by woemler on 10/20/14.
 */
@JsonFilter("restEntityFilter")
public abstract class RestEntity extends ResourceSupport { }
