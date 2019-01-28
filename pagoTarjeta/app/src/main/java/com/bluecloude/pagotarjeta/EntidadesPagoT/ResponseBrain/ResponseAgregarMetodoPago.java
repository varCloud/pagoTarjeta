package com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain;

import com.bluecloude.pagotarjeta.EntidadesPagoT.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class ResponseAgregarMetodoPago {

    private Boolean success;
    private PaymentMethod paymentMethod;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
