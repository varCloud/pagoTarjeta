package com.bluecloude.pagotarjeta.EntidadesPagoT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentMethod {
    private String bin;
    private String expirationMonth;
    private String expirationYear;
    private String last4;
    private String cardType;
    private Object cardholderName;
    private String commercial;
    private String countryOfIssuance;
    private CreatedAt createdAt;
    private String customerId;
    private String customerLocation;
    private String debit;
    private Boolean _default;
    private String durbinRegulated;
    private Boolean expired;
    private String globalId;
    private String healthcare;
    private String imageUrl;
    private String issuingBank;
    private String payroll;
    private String prepaid;
    private String productId;
    private List<Object> subscriptions = null;
    private String token;
    private String uniqueNumberIdentifier;
    private UpdatedAt updatedAt;
    private Boolean venmoSdk;
    private List<Verifications> verifications = null;
    private Object billingAddress;
    private String expirationDate;
    private String maskedNumber;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
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

    public Object getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(Object cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCommercial() {
        return commercial;
    }

    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }

    public String getCountryOfIssuance() {
        return countryOfIssuance;
    }

    public void setCountryOfIssuance(String countryOfIssuance) {
        this.countryOfIssuance = countryOfIssuance;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public Boolean getDefault() {
        return _default;
    }

    public void setDefault(Boolean _default) {
        this._default = _default;
    }

    public String getDurbinRegulated() {
        return durbinRegulated;
    }

    public void setDurbinRegulated(String durbinRegulated) {
        this.durbinRegulated = durbinRegulated;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getHealthcare() {
        return healthcare;
    }

    public void setHealthcare(String healthcare) {
        this.healthcare = healthcare;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public String getPayroll() {
        return payroll;
    }

    public void setPayroll(String payroll) {
        this.payroll = payroll;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(String prepaid) {
        this.prepaid = prepaid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<Object> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Object> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUniqueNumberIdentifier() {
        return uniqueNumberIdentifier;
    }

    public void setUniqueNumberIdentifier(String uniqueNumberIdentifier) {
        this.uniqueNumberIdentifier = uniqueNumberIdentifier;
    }

    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(UpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getVenmoSdk() {
        return venmoSdk;
    }

    public void setVenmoSdk(Boolean venmoSdk) {
        this.venmoSdk = venmoSdk;
    }

    public List<Verifications> getVerifications() {
        return verifications;
    }

    public void setVerifications(List<Verifications> verifications) {
        this.verifications = verifications;
    }

    public Object getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Object billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getMaskedNumber() {
        return maskedNumber;
    }

    public void setMaskedNumber(String maskedNumber) {
        this.maskedNumber = maskedNumber;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


