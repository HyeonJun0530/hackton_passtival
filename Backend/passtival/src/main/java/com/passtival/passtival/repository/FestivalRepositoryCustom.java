package com.passtival.passtival.repository;

import com.passtival.passtival.domain.Festival;
import com.passtival.passtival.dto.FestivalMainPageDto;
import com.passtival.passtival.dto.FestivalSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FestivalRepositoryCustom {

    Page<FestivalMainPageDto> getFestivalMainPage(FestivalSearchDto festivalSearchDto,
                                                  Pageable pageable);
}
