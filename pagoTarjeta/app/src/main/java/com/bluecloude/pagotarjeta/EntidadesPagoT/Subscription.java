package com.bluecloude.pagotarjeta.EntidadesPagoT;

import java.util.HashMap;
import java.util.Map;

public class Subscription {

    private Object billingPeriodEndDate;
    private Object billingPeriodStartDate;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getBillingPeriodEndDate() {
        return billingPeriodEndDate;
    }

    public void setBillingPeriodEndDate(Object billingPeriodEndDate) {
        this.billingPeriodEndDate = billingPeriodEndDate;
    }

    public Object getBillingPeriodStartDate() {
        return billingPeriodStartDate;
    }

    public void setBillingPeriodStartDate(Object billingPeriodStartDate) {
        this.billingPeriodStartDate = billingPeriodStartDate;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}