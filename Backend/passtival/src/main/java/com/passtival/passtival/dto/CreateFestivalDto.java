package com.passtival.passtival.dto;

import com.passtival.passtival.constant.FestivalCity;
import com.passtival.passtival.constant.FestivalMonth;
import com.passtival.passtival.constant.FestivalStatus;
import com.sun.istack.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateFestivalDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        @NotNull
        private String title;

        @NotNull
        private String host;

        @NotNull
        private String location;

        @NotNull
        private String date;

        @NotNull
        private String content;

        @NotNull
        private FestivalStatus status;

        @NotNull
        private FestivalMonth month;

        @NotNull
        private FestivalCity city;

        @NotNull
        private Boolean isFree;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long festivalId;

        public static Response of(Long festivalId) {
            return Response.builder()
                    .festivalId(festivalId)
                    .build();
        }
    }

}
