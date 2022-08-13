package com.passtival.passtival.domain;

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
public class Image {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long festival_id;

    @Column(length = 200, nullable = false)
    private String original_title;

    @Column(length = 255, nullable = false)
    private String stored_title;

    @Column(length = 500, nullable = false)
    private String img_url;

    //유,무료
    @Column(nullable = false)
    private boolean is_free;




}
