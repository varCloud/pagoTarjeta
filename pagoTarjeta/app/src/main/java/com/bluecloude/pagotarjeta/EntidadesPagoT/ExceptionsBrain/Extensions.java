package com.bluecloude.pagotarjeta.EntidadesPagoT.ExceptionsBrain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Extensions {

    private String errorType;
    private String errorClass;
    private String legacyCode;
    private List<String> inputPath = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorClass() {
        return errorClass;
    }

    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    public String getLegacyCode() {
        return legacyCode;
    }

    public void setLegacyCode(String legacyCode) {
        this.legacyCode = legacyCode;
    }

    public List<String> getInputPath() {
        return inputPath;
    }

    public void setInputPath(List<String> inputPath) {
        this.inputPath = inputPath;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
