package com.technical_test.musical_style.repository;

import com.technical_test.musical_style.entity.Survey;
import com.technical_test.musical_style.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
