package com.technical_test.musical_style.service;

import com.technical_test.musical_style.entity.MusicalStyle;
import com.technical_test.musical_style.entity.Survey;
import com.technical_test.musical_style.entity.User;
import com.technical_test.musical_style.repository.MusicalStyleRepository;
import com.technical_test.musical_style.repository.SurveyRepository;
import com.technical_test.musical_style.repository.UserRepository;
import com.technical_test.musical_style.service.dto.SurveyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SurveyService {

    private final Logger log = LoggerFactory.getLogger(SurveyService.class);

    private final SurveyRepository surveyRepository;

    private final UserRepository userRepository;

    private final MusicalStyleRepository musicalStyleRepository;

    public SurveyService(SurveyRepository surveyRepository, UserRepository userRepository, MusicalStyleRepository musicalStyleRepository) {
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
        this.musicalStyleRepository = musicalStyleRepository;
    }

    /**
     * Save a survey.
     *
     * @param surveyDTO the entity to save
     * @return the persisted entity
     */
    public SurveyDTO save(SurveyDTO surveyDTO) {
        log.debug("Request to save Survey : {}", surveyDTO);
        Survey survey = new Survey();
        survey.setId(surveyDTO.getId());
        survey.setCreateDate(new Date().toInstant());
        Optional<User> optionalUser = userRepository.findOneByEmailIgnoreCase(surveyDTO.getEmail());
        if(!optionalUser.isPresent()){
            User newUser = new User();
            newUser.setEmail(surveyDTO.getEmail());
            survey.setUser(userRepository.save(newUser));
        } else {
            survey.setUser(optionalUser.get());
        }
        survey.setMusicalStyles(surveyDTO.getMusicalStyles());
        survey = surveyRepository.save(survey);
        return new SurveyDTO(survey);
    }

    public Map<String, Integer> getTotalByMusicalStyles(){
        Map<String, Integer> totalByMusicalStyles = new HashMap<>();
        List<Survey> surveys = surveyRepository.findAll();
        for(Survey survey: surveys){
            for (MusicalStyle musicalStyle: survey.getMusicalStyles()){
                if(!totalByMusicalStyles.containsKey(musicalStyle.getName())){
                    totalByMusicalStyles.put(musicalStyle.getName(), 1);
                } else{
                    totalByMusicalStyles.replace(musicalStyle.getName(), totalByMusicalStyles.get(musicalStyle.getName()) + 1);
                }
            }
        }
        return totalByMusicalStyles;
    }

}
