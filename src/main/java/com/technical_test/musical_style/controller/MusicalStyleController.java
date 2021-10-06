package com.technical_test.musical_style.controller;

import com.technical_test.musical_style.entity.MusicalStyle;
import com.technical_test.musical_style.repository.MusicalStyleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MusicalStyleController {

    private final Logger log = LoggerFactory.getLogger(MusicalStyleController.class);

    private static final String ENTITY_NAME = "musicalStyle";

    private final MusicalStyleRepository musicalStyleRepository;

    public MusicalStyleController(MusicalStyleRepository musicalStyleRepository) {
        this.musicalStyleRepository = musicalStyleRepository;
    }

    @PostMapping("/musical-styles")
    public MusicalStyle createMusicalStyle(@RequestParam(value = "name") String name) throws Exception {
        MusicalStyle musicalStyle = new MusicalStyle();
        musicalStyle.setName(name);
        log.debug("REST request to save MusicalStyle : {}", musicalStyle);
        return musicalStyleRepository.save(musicalStyle);
    }

    /**
     * GET  /musical-styles : get all the musicalStyles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of musicalStyles in body
     */
    @GetMapping("/musical-styles")
    public List<MusicalStyle> getAllMusicalStyles() {
        log.debug("REST request to get all MusicalStyles");
        return musicalStyleRepository.findAll();
    }


}
