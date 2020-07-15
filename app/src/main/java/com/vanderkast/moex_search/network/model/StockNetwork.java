package com.vanderkast.moex_search.network.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "row")
public class StockNetwork {
    @Attribute(name = "SECID")
    private final String securityId;

    @Attribute(name = "BOARDID")
    private final String boardId;

    @Attribute(name = "SHORTNAME")
    private final String shortName;

    @Attribute(name = "PREVPRICE")
    private final String previewPrice;

    @Attribute(name = "SECNAME")
    private final String name;

    @Attribute(name = "LATNAME")
    private final String latinName;

    public StockNetwork(String securityId, String boardId, String shortName, String previewPrice, String name, String latinName) {
        this.securityId = securityId;
        this.boardId = boardId;
        this.shortName = shortName;
        this.previewPrice = previewPrice;
        this.name = name;
        this.latinName = latinName;
    }

    public String getSecurityId() {
        return securityId;
    }

    public String getBoardId() {
        return boardId;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPreviewPrice() {
        return previewPrice;
    }

    public String getName() {
        return name;
    }

    public String getLatinName() {
        return latinName;
    }
}
