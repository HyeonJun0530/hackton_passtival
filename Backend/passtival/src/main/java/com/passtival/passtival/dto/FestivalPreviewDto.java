package com.passtival.passtival.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FestivalPreviewDto {
    private Long festivalId;

    private String festivalName;

    private String imgUrl;

    @Builder
    public FestivalPreviewDto(Long festivalId, String festivalName, String imgUrl) {
        this.festivalId = festivalId;
        this.festivalName = festivalName;
        this.imgUrl = imgUrl;
    }
}
