package com.bluecloude.pagotarjeta.EntidadesPagoT.ExceptionsBrain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Error_ {
    private String message;
    private List<String> path = null;
    private Extensions extensions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public Extensions getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions extensions) {
        this.extensions = extensions;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
