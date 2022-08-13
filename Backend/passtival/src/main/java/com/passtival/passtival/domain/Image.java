package com.passtival.passtival.domain;

import com.passtival.passtival.config.audit.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Image extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String originalTitle;

    @Column(nullable = false)
    private String storedTitle;

    @Column(length = 500, nullable = false)
    private String imgUrl;

    //유,무료
    @Column(nullable = false)
    private Boolean isFree;

    @ManyToOne(fetch = FetchType.LAZY)
    private Festival festivalId;

}
