package com.passtival.passtival.domain;

import com.passtival.passtival.config.audit.BaseTimeEntity;
import com.passtival.passtival.constant.FestivalCity;
import com.passtival.passtival.constant.FestivalMonth;
import com.passtival.passtival.constant.FestivalStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Festival extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String host;

    @Column(length = 150, nullable = false)
    private String date;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FestivalStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FestivalMonth month;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FestivalCity city;

    @Column(nullable = false)
    private Boolean isFree;

    @Builder
    public Festival(String title, String host, String date, String content, FestivalStatus status, FestivalMonth month, FestivalCity city, Boolean isFree) {
        this.title = title;
        this.host = host;
        this.date = date;
        this.content = content;
        this.status = status;
        this.month = month;
        this.city = city;
        this.isFree = isFree;
    }
}
