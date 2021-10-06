package com.technical_test.musical_style.controller;

import com.technical_test.musical_style.entity.Survey;
import com.technical_test.musical_style.repository.SurveyRepository;
import com.technical_test.musical_style.service.SurveyService;
import com.technical_test.musical_style.service.dto.SurveyDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
@RestController
@RequestMapping("/api")
public class SurveyController {

    private final Logger log = LoggerFactory.getLogger(SurveyController.class);

    private static final String ENTITY_NAME = "survey";

    private final SurveyService surveyService;

    private final SurveyRepository surveyRepository;

    public SurveyController(SurveyService surveyService, SurveyRepository surveyRepository) {
        this.surveyService = surveyService;
        this.surveyRepository = surveyRepository;
    }

    @PostMapping("/surveys")
    public SurveyDTO createSurvey(@RequestBody SurveyDTO surveyDTO) throws Exception {
        log.debug("REST request to save Survey : {}", surveyDTO);
        if (surveyDTO.getId() != null) {
            throw new Exception("A new survey cannot already have an ID");
        }
       return surveyService.save(surveyDTO);
    }

    /**
     * GET  /surveys : get all the surveys.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of surveys in body
     */
    @GetMapping("/surveys")
    public List<Survey> getAllSurveys() {
        log.debug("REST request to get all Surveys");
        return surveyRepository.findAll();
    }

    @GetMapping("/total-by-musical-styles")
    public Map<String, Integer> getTotalByMusicalStyles() {
        log.debug("REST request to get all Surveys");
        return surveyService.getTotalByMusicalStyles();
    }

}
