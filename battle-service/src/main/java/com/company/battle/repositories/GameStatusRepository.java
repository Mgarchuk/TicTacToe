package com.company.battle.repositories;

import com.company.common.models.GameStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatusRepository extends JpaRepository<GameStatusEntity, Integer> {
}
