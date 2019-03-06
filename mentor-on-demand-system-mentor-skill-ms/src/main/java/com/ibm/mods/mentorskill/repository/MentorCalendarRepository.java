package com.ibm.mods.mentorskill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.mods.mentorskill.model.MentorCalendar;

@Repository
public interface MentorCalendarRepository extends JpaRepository<MentorCalendar, Long> {
	
	@Query(nativeQuery=true,
		   value = "SELECT * FROM Mentor_Calendar mc WHERE mc.mentor_skill_id = :id AND mc.start_time <= :timeFrom AND mc.end_time >= :timeTo")
	public List<MentorCalendar> getMentorCalendars(@Param("id") long id
										  , @Param("timeFrom") String timeFrom
										  , @Param("timeTo") String timeTo);
	
	@Query(nativeQuery=true, value = "SELECT * FROM Mentor_Calendar mc WHERE mc.skill_id IN :skillIds AND mc.start_time <= :timeFrom AND mc.end_time >= :timeTo")
	public List<MentorCalendar> getMentorCalendars(@Param("skillIds") List<Long> skillIds
			  									 , @Param("timeFrom") String timeFrom
			  									 , @Param("timeTo") String timeTo);
	
	List<MentorCalendar> findByMentorId(long mentorId);
	List<MentorCalendar> findByMentorSkillId(long mentorSkillId);
}
