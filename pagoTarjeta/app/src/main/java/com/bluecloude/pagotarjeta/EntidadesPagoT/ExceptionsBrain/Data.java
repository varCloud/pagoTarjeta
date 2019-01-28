package com.bluecloude.pagotarjeta.EntidadesPagoT.ExceptionsBrain;

import java.util.HashMap;
import java.util.Map;

public class Data {

    private Object tokenizeCreditCard;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getTokenizeCreditCard() {
        return tokenizeCreditCard;
    }

    public void setTokenizeCreditCard(Object tokenizeCreditCard) {
        this.tokenizeCreditCard = tokenizeCreditCard;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
