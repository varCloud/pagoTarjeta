package com.bluecloude.pagotarjeta.EntidadesPagoT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Transaction {

    private String id;
    private String status;
    private String type;
    private String currencyIsoCode;
    private String amount;
    private String merchantAccountId;
    private Object subMerchantAccountId;
    private Object masterMerchantAccountId;
    private Object orderId;
    private CreatedAt createdAt;
    private UpdatedAt updatedAt;
    private Customer customer;
    private Billing billing;
    private Object refundId;
    private List<Object> refundIds = null;
    private Object refundedTransactionId;
    private List<Object> partialSettlementTransactionIds = null;
    private Object authorizedTransactionId;
    private Object settlementBatchId;
    private Shipping shipping;
    private Object customFields;
    private Object avsErrorResponseCode;
    private String avsPostalCodeResponseCode;
    private String avsStreetAddressResponseCode;
    private String cvvResponseCode;
    private Object gatewayRejectionReason;
    private String processorAuthorizationCode;
    private String processorResponseCode;
    private String processorResponseText;
    private Object additionalProcessorResponse;
    private Object voiceReferralNumber;
    private Object purchaseOrderNumber;
    private Object taxAmount;
    private Boolean taxExempt;
    private CreditCard creditCard;
    private List<StatusHistory> statusHistory = null;
    private Object planId;
    private Object subscriptionId;
    private Subscription subscription;
    private List<Object> addOns = null;
    private List<Object> discounts = null;
    private Descriptor descriptor;
    private Boolean recurring;
    private Object channel;
    private Object serviceFeeAmount;
    private Object escrowStatus;
    private DisbursementDetails disbursementDetails;
    private List<Object> disputes = null;
    private List<Object> authorizationAdjustments = null;
    private String paymentInstrumentType;
    private Object processorSettlementResponseCode;
    private Object processorSettlementResponseText;
    private Object threeDSecureInfo;
    private Object shipsFromPostalCode;
    private Object shippingAmount;
    private Object discountAmount;
    private Object networkTransactionId;
    private List<Object> refundGlobalIds = null;
    private List<Object> partialSettlementTransactionGlobalIds = null;
    private Object refundedTransactionGlobalId;
    private Object authorizedTransactionGlobalId;
    private String globalId;
    private String processorResponseType;
    private AuthorizationExpiresAt authorizationExpiresAt;
    private CreditCardDetails creditCardDetails;
    private CustomerDetails customerDetails;
    private BillingDetails billingDetails;
    private ShippingDetails shippingDetails;
    private SubscriptionDetails subscriptionDetails;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrencyIsoCode() {
        return currencyIsoCode;
    }

    public void setCurrencyIsoCode(String currencyIsoCode) {
        this.currencyIsoCode = currencyIsoCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMerchantAccountId() {
        return merchantAccountId;
    }

    public void setMerchantAccountId(String merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }

    public Object getSubMerchantAccountId() {
        return subMerchantAccountId;
    }

    public void setSubMerchantAccountId(Object subMerchantAccountId) {
        this.subMerchantAccountId = subMerchantAccountId;
    }

    public Object getMasterMerchantAccountId() {
        return masterMerchantAccountId;
    }

    public void setMasterMerchantAccountId(Object masterMerchantAccountId) {
        this.masterMerchantAccountId = masterMerchantAccountId;
    }

    public Object getOrderId() {
        return orderId;
    }

    public void setOrderId(Object orderId) {
        this.orderId = orderId;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(UpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Object getRefundId() {
        return refundId;
    }

    public void setRefundId(Object refundId) {
        this.refundId = refundId;
    }

    public List<Object> getRefundIds() {
        return refundIds;
    }

    public void setRefundIds(List<Object> refundIds) {
        this.refundIds = refundIds;
    }

    public Object getRefundedTransactionId() {
        return refundedTransactionId;
    }

    public void setRefundedTransactionId(Object refundedTransactionId) {
        this.refundedTransactionId = refundedTransactionId;
    }

    public List<Object> getPartialSettlementTransactionIds() {
        return partialSettlementTransactionIds;
    }

    public void setPartialSettlementTransactionIds(List<Object> partialSettlementTransactionIds) {
        this.partialSettlementTransactionIds = partialSettlementTransactionIds;
    }

    public Object getAuthorizedTransactionId() {
        return authorizedTransactionId;
    }

    public void setAuthorizedTransactionId(Object authorizedTransactionId) {
        this.authorizedTransactionId = authorizedTransactionId;
    }

    public Object getSettlementBatchId() {
        return settlementBatchId;
    }

    public void setSettlementBatchId(Object settlementBatchId) {
        this.settlementBatchId = settlementBatchId;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Object getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Object customFields) {
        this.customFields = customFields;
    }

    public Object getAvsErrorResponseCode() {
        return avsErrorResponseCode;
    }

    public void setAvsErrorResponseCode(Object avsErrorResponseCode) {
        this.avsErrorResponseCode = avsErrorResponseCode;
    }

    public String getAvsPostalCodeResponseCode() {
        return avsPostalCodeResponseCode;
    }

    public void setAvsPostalCodeResponseCode(String avsPostalCodeResponseCode) {
        this.avsPostalCodeResponseCode = avsPostalCodeResponseCode;
    }

    public String getAvsStreetAddressResponseCode() {
        return avsStreetAddressResponseCode;
    }

    public void setAvsStreetAddressResponseCode(String avsStreetAddressResponseCode) {
        this.avsStreetAddressResponseCode = avsStreetAddressResponseCode;
    }

    public String getCvvResponseCode() {
        return cvvResponseCode;
    }

    public void setCvvResponseCode(String cvvResponseCode) {
        this.cvvResponseCode = cvvResponseCode;
    }

    public Object getGatewayRejectionReason() {
        return gatewayRejectionReason;
    }

    public void setGatewayRejectionReason(Object gatewayRejectionReason) {
        this.gatewayRejectionReason = gatewayRejectionReason;
    }

    public String getProcessorAuthorizationCode() {
        return processorAuthorizationCode;
    }

    public void setProcessorAuthorizationCode(String processorAuthorizationCode) {
        this.processorAuthorizationCode = processorAuthorizationCode;
    }

    public String getProcessorResponseCode() {
        return processorResponseCode;
    }

    public void setProcessorResponseCode(String processorResponseCode) {
        this.processorResponseCode = processorResponseCode;
    }

    public String getProcessorResponseText() {
        return processorResponseText;
    }

    public void setProcessorResponseText(String processorResponseText) {
        this.processorResponseText = processorResponseText;
    }

    public Object getAdditionalProcessorResponse() {
        return additionalProcessorResponse;
    }

    public void setAdditionalProcessorResponse(Object additionalProcessorResponse) {
        this.additionalProcessorResponse = additionalProcessorResponse;
    }

    public Object getVoiceReferralNumber() {
        return voiceReferralNumber;
    }

    public void setVoiceReferralNumber(Object voiceReferralNumber) {
        this.voiceReferralNumber = voiceReferralNumber;
    }

    public Object getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(Object purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public Object getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Object taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Boolean getTaxExempt() {
        return taxExempt;
    }

    public void setTaxExempt(Boolean taxExempt) {
        this.taxExempt = taxExempt;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public List<StatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<StatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public Object getPlanId() {
        return planId;
    }

    public void setPlanId(Object planId) {
        this.planId = planId;
    }

    public Object getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Object subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<Object> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<Object> addOns) {
        this.addOns = addOns;
    }

    public List<Object> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Object> discounts) {
        this.discounts = discounts;
    }

    public Descriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Descriptor descriptor) {
        this.descriptor = descriptor;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Object getChannel() {
        return channel;
    }

    public void setChannel(Object channel) {
        this.channel = channel;
    }

    public Object getServiceFeeAmount() {
        return serviceFeeAmount;
    }

    public void setServiceFeeAmount(Object serviceFeeAmount) {
        this.serviceFeeAmount = serviceFeeAmount;
    }

    public Object getEscrowStatus() {
        return escrowStatus;
    }

    public void setEscrowStatus(Object escrowStatus) {
        this.escrowStatus = escrowStatus;
    }

    public DisbursementDetails getDisbursementDetails() {
        return disbursementDetails;
    }

    public void setDisbursementDetails(DisbursementDetails disbursementDetails) {
        this.disbursementDetails = disbursementDetails;
    }

    public List<Object> getDisputes() {
        return disputes;
    }

    public void setDisputes(List<Object> disputes) {
        this.disputes = disputes;
    }

    public List<Object> getAuthorizationAdjustments() {
        return authorizationAdjustments;
    }

    public void setAuthorizationAdjustments(List<Object> authorizationAdjustments) {
        this.authorizationAdjustments = authorizationAdjustments;
    }

    public String getPaymentInstrumentType() {
        return paymentInstrumentType;
    }

    public void setPaymentInstrumentType(String paymentInstrumentType) {
        this.paymentInstrumentType = paymentInstrumentType;
    }

    public Object getProcessorSettlementResponseCode() {
        return processorSettlementResponseCode;
    }

    public void setProcessorSettlementResponseCode(Object processorSettlementResponseCode) {
        this.processorSettlementResponseCode = processorSettlementResponseCode;
    }

    public Object getProcessorSettlementResponseText() {
        return processorSettlementResponseText;
    }

    public void setProcessorSettlementResponseText(Object processorSettlementResponseText) {
        this.processorSettlementResponseText = processorSettlementResponseText;
    }

    public Object getThreeDSecureInfo() {
        return threeDSecureInfo;
    }

    public void setThreeDSecureInfo(Object threeDSecureInfo) {
        this.threeDSecureInfo = threeDSecureInfo;
    }

    public Object getShipsFromPostalCode() {
        return shipsFromPostalCode;
    }

    public void setShipsFromPostalCode(Object shipsFromPostalCode) {
        this.shipsFromPostalCode = shipsFromPostalCode;
    }

    public Object getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(Object shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public Object getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Object discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Object getNetworkTransactionId() {
        return networkTransactionId;
    }

    public void setNetworkTransactionId(Object networkTransactionId) {
        this.networkTransactionId = networkTransactionId;
    }

    public List<Object> getRefundGlobalIds() {
        return refundGlobalIds;
    }

    public void setRefundGlobalIds(List<Object> refundGlobalIds) {
        this.refundGlobalIds = refundGlobalIds;
    }

    public List<Object> getPartialSettlementTransactionGlobalIds() {
        return partialSettlementTransactionGlobalIds;
    }

    public void setPartialSettlementTransactionGlobalIds(List<Object> partialSettlementTransactionGlobalIds) {
        this.partialSettlementTransactionGlobalIds = partialSettlementTransactionGlobalIds;
    }

    public Object getRefundedTransactionGlobalId() {
        return refundedTransactionGlobalId;
    }

    public void setRefundedTransactionGlobalId(Object refundedTransactionGlobalId) {
        this.refundedTransactionGlobalId = refundedTransactionGlobalId;
    }

    public Object getAuthorizedTransactionGlobalId() {
        return authorizedTransactionGlobalId;
    }

    public void setAuthorizedTransactionGlobalId(Object authorizedTransactionGlobalId) {
        this.authorizedTransactionGlobalId = authorizedTransactionGlobalId;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getProcessorResponseType() {
        return processorResponseType;
    }

    public void setProcessorResponseType(String processorResponseType) {
        this.processorResponseType = processorResponseType;
    }

    public AuthorizationExpiresAt getAuthorizationExpiresAt() {
        return authorizationExpiresAt;
    }

    public void setAuthorizationExpiresAt(AuthorizationExpiresAt authorizationExpiresAt) {
        this.authorizationExpiresAt = authorizationExpiresAt;
    }

    public CreditCardDetails getCreditCardDetails() {
        return creditCardDetails;
    }

    public void setCreditCardDetails(CreditCardDetails creditCardDetails) {
        this.creditCardDetails = creditCardDetails;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }

    public ShippingDetails getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(ShippingDetails shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public SubscriptionDetails getSubscriptionDetails() {
        return subscriptionDetails;
    }

    public void setSubscriptionDetails(SubscriptionDetails subscriptionDetails) {
        this.subscriptionDetails = subscriptionDetails;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}