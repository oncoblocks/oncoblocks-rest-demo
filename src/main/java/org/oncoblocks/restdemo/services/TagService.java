package org.oncoblocks.restdemo.services;

import org.oncoblocks.restdemo.models.Sample;
import org.oncoblocks.restdemo.models.Tag;
import org.oncoblocks.restdemo.util.DummyDataGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
	
	private List<Tag> getDummySamples() {
		List<Tag> tagList = new ArrayList<Tag>();
		for (int i=0; i<20; i++) {
			Tag tag = createDummyTag();
			tagList.add(tag);
		}
		return tagList;
	}
	
	private Tag createDummyTag() {
		Tag tag = new Tag();
		DummyDataGenerator.createDummyData(tag);
		return tag;
	}	
	
}
