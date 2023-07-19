package com.company.statistic.repositories;

import com.company.common.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatisticRepository extends JpaRepository<UserEntity, UUID> {
}
