package com.passtival.passtival.repository;

import com.passtival.passtival.constant.FestivalCity;
import com.passtival.passtival.constant.FestivalMonth;
import com.passtival.passtival.constant.FestivalStatus;
import com.passtival.passtival.domain.QFestival;
import com.passtival.passtival.dto.FestivalMainPageDto;
import com.passtival.passtival.dto.FestivalSearchDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class FestivalRepositoryCustomImpl implements FestivalRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public FestivalRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 행사 진행 상태로 검색
     */
    private BooleanExpression searchFestivalStatusEq(FestivalStatus festivalStatus) {
        return festivalStatus == null ? null : QFestival.festival.status.eq(festivalStatus);
    }

    /**
     * 행사 주최 월 별로 검색
     */
    private BooleanExpression searchFestivalMonthEq(FestivalMonth festivalMonth) {
        return festivalMonth == null ? null : QFestival.festival.month.eq(festivalMonth);
    }

    /**
     * 행사 주최 구 별로 검색
     */
    private BooleanExpression searchFestivalCityEq(FestivalCity festivalCity) {
        return festivalCity == null ? null : QFestival.festival.city.eq(festivalCity);
    }

    @Override
    public Page<FestivalMainPageDto> getFestivalMainPage(FestivalSearchDto festivalSearchDto, Pageable pageable) {

//        QFestival festival = QFestival.festival;
//        QFestivalImage festivalImage = QFestivalImage.festivalImage;
//
//        queryFactory.select(
//                new QFestivalMainPageDto(
//                        festival.id,
//                        festival.title,
//                        festivalImage.imgUrl)
//        )
//                        .from(festivalImage)
//                .join(festivalImage.festival, festival)
//                .where()


        return null;
    }
}
