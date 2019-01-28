package com.bluecloude.pagotarjeta.EntidadesPagoT;

import java.util.HashMap;
import java.util.Map;

public class CreditCard  {

    private String token;
    private String bin;
    private String last4;
    private String cardType;
    private String expirationMonth;
    private String expirationYear;
    private String customerLocation;
    private Object cardholderName;
    private String imageUrl;
    private String prepaid;
    private String healthcare;
    private String debit;
    private String durbinRegulated;
    private String commercial;
    private String payroll;
    private String issuingBank;
    private String countryOfIssuance;
    private String productId;
    private String globalId;
    private String uniqueNumberIdentifier;
    private Boolean venmoSdk;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public Object getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(Object cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(String prepaid) {
        this.prepaid = prepaid;
    }

    public String getHealthcare() {
        return healthcare;
    }

    public void setHealthcare(String healthcare) {
        this.healthcare = healthcare;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getDurbinRegulated() {
        return durbinRegulated;
    }

    public void setDurbinRegulated(String durbinRegulated) {
        this.durbinRegulated = durbinRegulated;
    }

    public String getCommercial() {
        return commercial;
    }

    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }

    public String getPayroll() {
        return payroll;
    }

    public void setPayroll(String payroll) {
        this.payroll = payroll;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public String getCountryOfIssuance() {
        return countryOfIssuance;
    }

    public void setCountryOfIssuance(String countryOfIssuance) {
        this.countryOfIssuance = countryOfIssuance;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getUniqueNumberIdentifier() {
        return uniqueNumberIdentifier;
    }

    public void setUniqueNumberIdentifier(String uniqueNumberIdentifier) {
        this.uniqueNumberIdentifier = uniqueNumberIdentifier;
    }

    public Boolean getVenmoSdk() {
        return venmoSdk;
    }

    public void setVenmoSdk(Boolean venmoSdk) {
        this.venmoSdk = venmoSdk;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}