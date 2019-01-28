package com.bluecloude.pagotarjeta.EntidadesPagoT.ExceptionsBrain;

import java.util.HashMap;
import java.util.Map;

public class Extensions_ {

    private String requestId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
