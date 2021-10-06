package com.technical_test.musical_style.repository;

import com.technical_test.musical_style.entity.MusicalStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicalStyleRepository extends JpaRepository<MusicalStyle, Long> {

}
