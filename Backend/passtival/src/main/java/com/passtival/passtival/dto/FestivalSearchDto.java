package com.passtival.passtival.dto;

import com.passtival.passtival.constant.FestivalCity;
import com.passtival.passtival.constant.FestivalMonth;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FestivalSearchDto {

    private Boolean isFree;

    private FestivalMonth month;

    private FestivalCity city;

    @Builder
    public FestivalSearchDto(Boolean isFree, FestivalMonth month, FestivalCity city) {
        this.isFree = isFree;
        this.month = month;
        this.city = city;
    }
}
