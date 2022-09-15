package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;


@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tradeId;

    @NotBlank(message = "Account is mandatory")
    @Size(max = 30, message = "Maximum of {max} characters")
    private String account;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 30, message = "Maximum of {max} characters")
    private String type;

    @Positive(message = "Must be a positive number")
    @DecimalMax(value = "2", message = "Must not have more than {value} decimals")
    @NotNull(message = "Buy quantity is mandatory")
    private Double buyQuantity;

    @Positive(message = "Must be a positive number")
    @DecimalMax(value = "2", message = "Must not have more than {value} decimals")
    private Double sellQuantity;

    @Positive(message = "Must be a positive number")
    @DecimalMax(value = "2", message = "Must not have more than {value} decimals")
    private Double buyPrice;

    @Positive(message = "Must be a positive number")
    @DecimalMax(value = "2", message = "Must not have more than {value} decimals")
    private Double sellPrice;

    private Timestamp tradeDate;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String security;

    @Size(max = 10, message = "Maximum of {max} characters")
    private String status;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String trader;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String benchmark;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String book;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String creationName;

    private Timestamp creationDate;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String revisionName;

    private Timestamp revisionDate;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String dealName;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String dealType;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String sourceListId;

    @Size(max = 125, message = "Maximum of {max} characters")
    private String side;

    // CONSTRUCTORS

    public Trade() {}

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

    // GETTERS

    public Integer getTradeId() {
        return tradeId;
    }

    public String getAccount() {
        return account;
    }

    public String getType() {
        return type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public String getSecurity() {
        return security;
    }

    public String getStatus() {
        return status;
    }

    public String getTrader() {
        return trader;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public String getBook() {
        return book;
    }

    public String getCreationName() {
        return creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public String getSide() {
        return side;
    }

    // SETTER

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
