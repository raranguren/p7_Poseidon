package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bidlist")
public class BidList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BidListId")
    private Integer bidListId;

    @NotBlank(message = "{mandatory.account}")
    @Size(max = 30, message = "{size.max}")
    private String account;

    @NotBlank(message = "{mandatory.type}")
    @Size(max = 30, message = "{size.max}")
    private String type;

    @Positive(message = "{positive}")
    @DecimalMax(value = "2", message = "{decimals.max}")
    private Double bidQuantity;

    @Positive(message = "{positive}")
    @DecimalMax(value = "2", message = "{decimals.max}")
    private Double askQuantity;

    @Positive(message = "{positive}")
    @DecimalMax(value = "2", message = "{decimals.max}")
    private Double bid;

    @Positive(message = "{positive}")
    @DecimalMax(value = "2", message = "{decimals.max}")
    private Double ask;

    @Size(max = 125, message = "{size.max}")
    private String benchmark;

    private Timestamp bidListDate;

    @Size(max = 125, message = "{size.max}")
    private String commentary;

    @Size(max = 125, message = "{size.max}")
    private String security;

    @Size(max = 10, message = "{size.max}")
    private String status;

    @Size(max = 125, message = "{size.max}")
    private String trader;

    @Size(max = 125, message = "{size.max}")
    private String book;

    @Size(max = 125, message = "{size.max}")
    private String creationName;

    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());

    @Size(max = 125, message = "{size.max}")
    private String revisionName;

    private Timestamp revisionDate;

    @Size(max = 125, message = "{size.max}")
    private String dealName;

    @Size(max = 125, message = "{size.max}")
    private String dealType;

    @Size(max = 125, message = "{size.max}")
    private String sourceListId;

    @Size(max = 125, message = "{size.max}")
    private String side;

    // CONSTRUCTORS

    public BidList() {}

    public BidList(String account, String type, double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    // GETTERS

    public Integer getBidListId() {
        return bidListId;
    }

    public String getAccount() {
        return account;
    }

    public String getType() {
        return type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public Double getAsk() {
        return ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public String getCommentary() {
        return commentary;
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

    // SETTERS

    public void setAccount(String account) {
        this.account = account;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
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
