package com.company.battle.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(String description) {
        String[] coordinates = description.split(";");
        this.x = Integer.parseInt(coordinates[0]);
        this.y = Integer.parseInt(coordinates[1]);
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }
}
