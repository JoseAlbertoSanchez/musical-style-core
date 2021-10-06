package com.technical_test.musical_style.service.dto;

import com.technical_test.musical_style.entity.MusicalStyle;
import com.technical_test.musical_style.entity.Survey;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Survey entity.
 */
public class SurveyDTO implements Serializable {

    private Long id;

    private Instant createDate;

    private String email;

    private Set<MusicalStyle> musicalStyles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<MusicalStyle> getMusicalStyles() {
        return musicalStyles;
    }

    public void setMusicalStyles(Set<MusicalStyle> musicalStyles) {
        this.musicalStyles = musicalStyles;
    }

    public SurveyDTO(){

    }

    public SurveyDTO(Survey survey){
        this.id = survey.getId();
        this.setEmail(survey.getUser().getEmail());
        this.setCreateDate(survey.getCreateDate());
        this.setMusicalStyles(new HashSet<>(survey.getMusicalStyles()));
//        for (MusicalStyle musicalStyle: survey.getMusicalStyles()){
//            this.getMusicalStyles().add(musicalStyle.getId());
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SurveyDTO surveyDTO = (SurveyDTO) o;
        if (surveyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), surveyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SurveyDTO{" +
                "id=" + getId() +
                ", createDate='" + getCreateDate() + "'" +
                ", user=" + getEmail() + "'" +
                "}";
    }
}