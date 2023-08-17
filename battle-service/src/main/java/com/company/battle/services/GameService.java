package com.company.battle.services;

import com.company.battle.repositories.GameRepository;
import com.company.battle.utils.services.GameValidationService;
import com.company.common.dtos.SearchGameRequestDto;
import com.company.common.models.GameEntity;
import com.company.common.models.SettingsEntity;
import com.company.common.models.UserEntity;
import com.company.common.models.enums.GameStatus;
import com.company.common.models.enums.GameVisibility;
import com.company.common.models.enums.PreferableSide;
import com.company.common.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GameService {

    @Autowired
    private final GameRepository gameRepository;

    @Autowired
    private final UserRepository userRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public GameEntity getById(UUID id) {
        return gameRepository.findById(id).orElse(null);
    }

    public List<GameEntity> findAll(final Specification<GameEntity> specification) {
        return gameRepository.findAll(specification);
    }

    public List<GameEntity> getPublicGames() {
        return gameRepository.findByVisibility(GameVisibility.PUBLIC);
    }

    public List<GameEntity> findGame(SearchGameRequestDto requestDto) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GameEntity> criteriaQuery = criteriaBuilder.createQuery(GameEntity.class);
        Root<GameEntity> root = criteriaQuery.from(GameEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("status"), GameStatus.PENDING));
        predicates.add(criteriaBuilder.equal(root.get("visibility"), GameVisibility.PUBLIC));

        if (requestDto.getSquareSize() != null) {
            predicates.add(criteriaBuilder.equal(root.get("settings").get("squareSize"), requestDto.getSquareSize()));
        }
        if (requestDto.getLinesCountForWin() != null) {
            predicates.add(criteriaBuilder.equal(root.get("settings").get("linesCountForWin"), requestDto.getLinesCountForWin()));
        }
        if (requestDto.getMoveTimeLimit() != null) {
            predicates.add(criteriaBuilder.equal(root.get("settings").get("moveTimeLimit"), requestDto.getMoveTimeLimit()));
        }

        if (requestDto.getPreferableSide() == PreferableSide.O) {
            predicates.add(criteriaBuilder.isNull(root.get("settings").get("oPlayerId")));
        } else if (requestDto.getPreferableSide() == PreferableSide.X) {
            predicates.add(criteriaBuilder.isNull(root.get("settings").get("xPlayerId")));
        } else if (requestDto.getPreferableSide() == PreferableSide.ANY) {
            predicates.add(criteriaBuilder.or(root.get("settings").get("oPlayerId").isNull(), root.get("settings").get("xPlayerId").isNull()));
        }

        CriteriaQuery<GameEntity> select = criteriaQuery.select(root).where(predicates.toArray(new Predicate[0])).orderBy(criteriaBuilder.asc(root.get("creationDate")));
        TypedQuery<GameEntity> typedQuery = entityManager.createQuery(select).setMaxResults(requestDto.getGameCountLimit());

        entityManager.close();
        return typedQuery.getResultList();

    }

    public GameEntity create(GameEntity gameEntity, UUID userId) {
        if (!GameValidationService.isValidGameToCreate(gameEntity)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game to create");
        }

        gameEntity.setStatus(GameStatus.PENDING);
        gameEntity.setCreationDate(LocalDateTime.now());
        if (gameEntity.getSettings().getXPlayerId() == null && gameEntity.getSettings().getOPlayerId() == null) {
            setRandomRole(gameEntity.getSettings(), userId);
        }

        return gameRepository.save(gameEntity);
    }

    public GameEntity joinGame(GameEntity gameEntity, UserEntity currentUser) {
        gameEntity.setStatus(GameStatus.ACTIVE);
        UUID xPlayerId = gameEntity.getSettings().getXPlayerId();
        UUID oPlayerId = gameEntity.getSettings().getOPlayerId();
        if (xPlayerId != null && oPlayerId != null || xPlayerId == null && oPlayerId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Нou can't connect to the game because there must already be 1 member in the game");
        }

        if (xPlayerId == null) {
            gameEntity.getSettings().setXPlayerId(currentUser.getId());
        } else {
            gameEntity.getSettings().setOPlayerId(currentUser.getId());
        }
        return gameRepository.save(gameEntity);
    }

    public GameEntity leave(UUID gameId, UserEntity currentUser) {
        GameEntity gameEntity = gameRepository.findById(gameId).orElse(null);

        if (gameEntity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no game with this id");
        } else if (!gameEntity.getSettings().getXPlayerId().equals(currentUser.getId()) && !gameEntity.getSettings().getOPlayerId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not a member of this game");
        }

        if (gameEntity.getStatus() == GameStatus.ACTIVE) {
            UUID winnerId = currentUser.getId().equals(gameEntity.getSettings().getXPlayerId()) ? gameEntity.getSettings().getOPlayerId() : currentUser.getId();
            Optional<UserEntity> winner = userRepository.findById(winnerId);

            gameEntity.setWinner(winner.orElse(null));
        }

        gameEntity.setStatus(GameStatus.FINISHED);

        return gameRepository.save(gameEntity);
    }

    private void setRandomRole(SettingsEntity settingsEntity, UUID userId) {
        Random random = new Random();
        boolean isXPlayer = random.nextBoolean();
        if (isXPlayer) {
            settingsEntity.setXPlayerId(userId);
        } else {
            settingsEntity.setOPlayerId(userId);
        }
    }
}
