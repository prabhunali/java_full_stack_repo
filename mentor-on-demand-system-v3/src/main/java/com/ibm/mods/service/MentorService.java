package com.ibm.mods.service;

import java.util.List;

import com.ibm.mods.mentor.model.Mentor;
import com.ibm.mods.mentor.model.MentorDto;
import com.ibm.mods.mentor.model.MentorSearchResult;

public interface MentorService {

	Mentor save(MentorDto user);
	List<Mentor> findAll();
	void delete(long id);
	Mentor findOne(String username);
	Mentor findById(long id);
	MentorDto update(MentorDto userDto);
	
}
