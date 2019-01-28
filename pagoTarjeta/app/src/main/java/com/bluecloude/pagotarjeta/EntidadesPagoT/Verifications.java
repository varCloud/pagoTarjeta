package com.bluecloude.pagotarjeta.EntidadesPagoT;

import java.util.HashMap;
import java.util.Map;

public class Verifications{
        private String status;
        private String cvvResponseCode;
        private Object avsErrorResponseCode;
        private String avsPostalCodeResponseCode;
        private String avsStreetAddressResponseCode;
        private Object gatewayRejectionReason;
        private String merchantAccountId;
        private String processorResponseCode;
        private String processorResponseText;
        private String amount;
        private String currencyIsoCode;
        private String processorResponseType;
        private String id;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCvvResponseCode() {
            return cvvResponseCode;
        }

        public void setCvvResponseCode(String cvvResponseCode) {
            this.cvvResponseCode = cvvResponseCode;
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

        public Object getGatewayRejectionReason() {
            return gatewayRejectionReason;
        }

        public void setGatewayRejectionReason(Object gatewayRejectionReason) {
            this.gatewayRejectionReason = gatewayRejectionReason;
        }

        public String getMerchantAccountId() {
            return merchantAccountId;
        }

        public void setMerchantAccountId(String merchantAccountId) {
            this.merchantAccountId = merchantAccountId;
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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCurrencyIsoCode() {
            return currencyIsoCode;
        }

        public void setCurrencyIsoCode(String currencyIsoCode) {
            this.currencyIsoCode = currencyIsoCode;
        }

        public String getProcessorResponseType() {
            return processorResponseType;
        }

        public void setProcessorResponseType(String processorResponseType) {
            this.processorResponseType = processorResponseType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

}