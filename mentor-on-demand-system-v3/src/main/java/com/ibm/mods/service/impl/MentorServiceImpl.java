package com.ibm.mods.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mods.mentor.model.Mentor;
import com.ibm.mods.mentor.model.MentorDto;
import com.ibm.mods.repository.MentorRepository;
import com.ibm.mods.service.MentorService;

@Service(value = "mentorService")
public class MentorServiceImpl implements MentorService {

	@Autowired
	private MentorRepository mentorDao;
	
	@Override
	public Mentor save(MentorDto mentor) {
		Mentor newMentor = new Mentor();
		newMentor.setUsername(mentor.getUsername());
		newMentor.setLinkedinUrl(mentor.getLinkedinUrl());
		newMentor.setYearsOfExperience(mentor.getYearsOfExperience());
		newMentor.setIntroduction(mentor.getIntroduction());
		return mentorDao.save(newMentor);
	}

	@Override
	public List<Mentor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public Mentor findOne(String username) {
		return mentorDao.findByUsername(username);
	}

	@Override
	public Mentor findById(long id) {
		return mentorDao.findById(id).get();
	}

	@Override
	public MentorDto update(MentorDto mentorDto) {
		Mentor mentor = findById(mentorDto.getId());
		mentor.setId(mentorDto.getId());
		mentor.setLinkedinUrl(mentorDto.getLinkedinUrl());
		mentor.setYearsOfExperience(mentorDto.getYearsOfExperience());
		mentor.setIntroduction(mentorDto.getIntroduction());
//		if(mentor != null) {
//			BeanUtils.copyProperties(mentorDto, mentor);
//			mentorDao.save(mentor);
//		}
		return mentorDto;
	}
	
}
