package com.company.battle.utils.specifications;

import com.company.common.models.GameEntity;
import com.company.common.models.enums.PreferableSide;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SearchGameSpecification {
    public static Specification<GameEntity> of(int squareSize, int linesCountForWin, int moveTimeLimit, PreferableSide preferableSide) {

        List<Specification<GameEntity>> specifications = new ArrayList<>();

        SpecificationUtils.putIfNotNull(specifications, squareSize, hasSquareSize(squareSize));
        SpecificationUtils.putIfNotNull(specifications, linesCountForWin, hasLinesCountForWin(linesCountForWin));
        SpecificationUtils.putIfNotNull(specifications, moveTimeLimit, hasMoveTimeLimit(moveTimeLimit));
        SpecificationUtils.putIfNotNull(specifications, preferableSide, hasPreferableSide(preferableSide));

        return SpecificationUtils.createFilterAnd(specifications);
    }

    private static Specification<GameEntity> hasSquareSize(int squareSize) {
        return (companyRoot, cq, cb) -> cb.equal(companyRoot.get("settings").get("squareSize"), squareSize);
    }

    private static Specification<GameEntity> hasLinesCountForWin(int linesCountForWin) {
        return (companyRoot, cq, cb) -> cb.equal(companyRoot.get("settings").get("linesCountForWin"), linesCountForWin);
    }

    private static Specification<GameEntity> hasMoveTimeLimit(int moveTimeLimit) {
        return (companyRoot, cq, cb) -> cb.equal(companyRoot.get("settings").get("moveTimeLimit"), moveTimeLimit);
    }

    private static Specification<GameEntity> hasPreferableSide(PreferableSide preferableSide) {
        if (preferableSide == PreferableSide.X) {
            return (companyRoot, cq, cb) -> cb.isNull(companyRoot.get("settings").get("xPlayerId"));
        } else if (preferableSide == PreferableSide.O) {
            return (companyRoot, cq, cb) -> cb.isNull(companyRoot.get("settings").get("oPlayerId"));
        }
        return (companyRoot, cq, cb) -> cb.or(cb.isNull(companyRoot.get("settings").get("xPlayerId")), cb.isNull(companyRoot.get("settings").get("oPlayerId")));
    }
}
