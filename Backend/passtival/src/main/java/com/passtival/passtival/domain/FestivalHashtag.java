package com.passtival.passtival.domain;

import com.passtival.passtival.config.audit.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FestivalHashtag")
@Entity
public class FestivalHashtag extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Festival festivalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Hashtag hashtagId;

}
