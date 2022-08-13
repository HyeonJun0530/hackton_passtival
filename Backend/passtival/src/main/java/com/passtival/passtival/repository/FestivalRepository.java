package com.passtival.passtival.repository;

import com.passtival.passtival.constant.FestivalStatus;
import com.passtival.passtival.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface FestivalRepository extends JpaRepository<Festival, Long>,
        QuerydslPredicateExecutor<Festival>, FestivalRepositoryCustom {

    List<Festival> findAllByStatus(FestivalStatus status);
}
