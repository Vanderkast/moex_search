package com.vanderkast.moex_search.entity;

public class Stock {
    private final String name;
    private final String previewValue;
    private final String boardId;

    public Stock(String name, String previewValue, String boardId) {
        this.name = name;
        this.previewValue = previewValue;
        this.boardId = boardId;
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
}
