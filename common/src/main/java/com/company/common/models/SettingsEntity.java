package com.company.common.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.UUID;

@Data
@Embeddable
public class SettingsEntity {

    private int squareSize;
    private int linesCountForWin;
    private UUID xPlayerId;
    private UUID oPlayerId;
    private int moveTimeLimit;
}
