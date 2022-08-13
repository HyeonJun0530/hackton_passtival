package com.passtival.passtival.dto;

import com.passtival.passtival.constant.FestivalCity;
import com.passtival.passtival.constant.FestivalMonth;
import com.passtival.passtival.constant.FestivalStatus;
import com.passtival.passtival.domain.Festival;
import lombok.Getter;

@Getter
public class FestivalSearchDto {

    private FestivalStatus status;

    private FestivalMonth month;

    private FestivalCity city;
}
