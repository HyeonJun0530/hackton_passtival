package com.passtival.passtival.repository;

import com.passtival.passtival.domain.Festival;
import com.passtival.passtival.dto.FestivalSearchDto;

import java.util.List;

public interface FestivalRepositoryCustom {

    List<Festival> getSearchPage(FestivalSearchDto festivalSearch);
}
