package com.passtival.passtival.repository;

import com.passtival.passtival.domain.Festival;
import com.passtival.passtival.domain.FestivalImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalImageRepository extends JpaRepository<FestivalImage, Long> {
    FestivalImage findByFestival(Festival festival);

}
