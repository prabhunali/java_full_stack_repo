package com.ibm.mods.mentorskill.service;

import java.util.List;

import com.ibm.mods.mentorskill.model.Mentor;
import com.ibm.mods.mentorskill.model.MentorDto;

public interface MentorService {

	Mentor save(MentorDto user);
	List<Mentor> findAll();
	void delete(long id);
	Mentor findOne(String username);
	Mentor findById(long id);
	Mentor findByUserId(long id);
	MentorDto update(MentorDto userDto);
	
}
