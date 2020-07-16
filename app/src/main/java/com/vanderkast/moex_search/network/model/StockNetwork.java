package com.vanderkast.moex_search.network.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "row", strict = false)
public class StockNetwork {
    @Attribute(name = "SECID")
    private String securityId;

    @Attribute(name = "BOARDID")
    private String boardId;

    @Attribute(name = "SHORTNAME")
    private String shortName;

    @Attribute(name = "PREVPRICE")
    private String previewPrice;

    @Attribute(name = "SECNAME")
    private String name;

    @Attribute(name = "LATNAME")
    private String latinName;

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPreviewPrice() {
        return previewPrice;
    }

    public void setPreviewPrice(String previewPrice) {
        this.previewPrice = previewPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }
}
