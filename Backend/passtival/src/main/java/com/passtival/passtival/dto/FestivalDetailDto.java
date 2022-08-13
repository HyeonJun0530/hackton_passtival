package com.passtival.passtival.dto;

import com.passtival.passtival.constant.FestivalCity;
import com.passtival.passtival.constant.FestivalMonth;
import com.passtival.passtival.constant.FestivalStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FestivalDetailDto {
    private Long festivalId;

    private String title;

    private String host;

    private String location;

    private String date;

    private String content;

    private FestivalStatus status;

    private FestivalMonth month;

    private FestivalCity city;

    private Boolean isFree;

    @Builder
    public FestivalDetailDto(Long festivalId, String title, String host, String location, String date, String content, FestivalStatus status, FestivalMonth month, FestivalCity city, Boolean isFree) {
        this.festivalId = festivalId;
        this.title = title;
        this.host = host;
        this.location = location;
        this.date = date;
        this.content = content;
        this.status = status;
        this.month = month;
        this.city = city;
        this.isFree = isFree;
    }
}
