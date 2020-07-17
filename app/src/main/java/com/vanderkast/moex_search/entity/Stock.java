package com.vanderkast.moex_search.entity;

public class Stock {
    private final String name;
    private final String previewValue;
    private final String boardId;
    private final String securityId;

    public Stock(String name, String previewValue, String boardId, String securityId) {
        this.name = name;
        this.previewValue = previewValue;
        this.boardId = boardId;
        this.securityId = securityId;
    }

    public String getName() {
        return name;
    }

    public String getPreviewValue() {
        return previewValue;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getSecurityId() {
        return securityId;
    }
}
