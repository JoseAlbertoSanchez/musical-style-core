package com.technical_test.musical_style.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "create_date")
    private Instant createDate;

    @ManyToOne
    @JsonIgnoreProperties("surveys")
    private User user;

    @ManyToMany
    @JoinTable(name = "survey_musical_style",
            joinColumns = @JoinColumn(name = "survey_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "musical_style_id", referencedColumnName = "id"))
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MusicalStyle> getMusicalStyles() {
        return musicalStyles;
    }

    public void setMusicalStyles(Set<MusicalStyle> musicalStyles) {
        this.musicalStyles = musicalStyles;
    }
}
