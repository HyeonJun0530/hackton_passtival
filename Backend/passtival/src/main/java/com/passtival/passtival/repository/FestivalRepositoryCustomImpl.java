package com.passtival.passtival.repository;

import com.passtival.passtival.constant.FestivalCity;
import com.passtival.passtival.constant.FestivalMonth;
import com.passtival.passtival.domain.Festival;
import com.passtival.passtival.domain.QFestival;
import com.passtival.passtival.dto.FestivalSearchDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class FestivalRepositoryCustomImpl implements FestivalRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public FestivalRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 행사 진행 유/무료 구분
     */
    private BooleanExpression searchIsFreeEq(Boolean isFree) {
        return isFree == null ? null : QFestival.festival.isFree.eq(isFree);
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
    public List<Festival> getSearchPage(FestivalSearchDto festivalSearchDto) {
        List<Festival> results = queryFactory
                .selectFrom(QFestival.festival)
                .where(
                        searchIsFreeEq(festivalSearchDto.getIsFree()),
                        searchFestivalMonthEq(festivalSearchDto.getMonth()),
                        searchFestivalCityEq(festivalSearchDto.getCity())

                )
                .orderBy(QFestival.festival.id.desc())
                .fetch();

        return results;
    }

}
