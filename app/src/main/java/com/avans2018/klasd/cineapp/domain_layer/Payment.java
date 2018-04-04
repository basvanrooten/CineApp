package com.avans2018.klasd.cineapp.domain_layer;

import java.io.Serializable;

public class Payment implements Serializable {
    private String id;
    private Double amount;
    private String description;
    private String paymentUrl;
    private String redirectUrl;
    private String webhookUrl;
    private String status;

    public Payment(Double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {

        return status;
    }
}
