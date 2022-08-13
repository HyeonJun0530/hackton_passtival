package com.passtival.passtival.domain;

import com.passtival.passtival.config.audit.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FestivalImage extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String originalImgName;

    @Column(nullable = false)
    private String storedImgName;

    @Column(length = 500, nullable = false)
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Festival festival;

    @Builder
    public FestivalImage(String originalImgName, String storedImgName, String imgUrl, Festival festival) {
        this.originalImgName = originalImgName;
        this.storedImgName = storedImgName;
        this.imgUrl = imgUrl;
        this.festival = festival;
    }
}
