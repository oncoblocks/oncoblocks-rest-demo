package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.models.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by woemler on 11/10/14.
 */

@Service
@Transactional
public class TagService {
	
	public List<Tag> findAllTags(Integer limit, Integer offset){
		return null;
	}
	
	public Tag findTagById(Integer id){
		return null;
	}
	
	public List<Tag> findTagsByLabel(String label, Integer limit, Integer offset){
		return null;
	}
	
	public List<Tag> findTagsBySample(Integer id, Integer limit, Integer offset){
		return null;
	}
	
	public Tag addTag(Tag tag){
		return null;
	}
	
	public Integer updateTag(Tag tag){
		return null;
	}
	
	public Integer deleteTag(Integer id){
		return null;
	}
	
}
