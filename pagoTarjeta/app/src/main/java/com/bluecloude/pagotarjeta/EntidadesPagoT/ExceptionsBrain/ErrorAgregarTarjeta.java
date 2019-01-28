package com.bluecloude.pagotarjeta.EntidadesPagoT.ExceptionsBrain;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorAgregarTarjeta {

    private Data data;
    private List<Error_> errors = null;
    private Extensions_ extensions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Error_> getErrors() {
        return errors;
    }

    public void setErrors(List<Error_> errors) {
        this.errors = errors;
    }

    public Extensions_ getExtensions() {
        return extensions;
    }

    public void setExtensions(Extensions_ extensions) {
        this.extensions = extensions;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
