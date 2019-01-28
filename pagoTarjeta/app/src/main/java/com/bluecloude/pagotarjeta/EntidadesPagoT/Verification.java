package com.bluecloude.pagotarjeta.EntidadesPagoT;

import java.util.HashMap;
import java.util.Map;

public class Verification {
        private String merchantAccountId;
        private String processorResponseCode;
        private String processorResponseText;
        private String processorResponseType;
        private String id;
        private Billing billing;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

        public Billing getBilling() {
            return billing;
        }

        public void setBilling(Billing billing) {
            this.billing = billing;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

}