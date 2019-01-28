package com.bluecloude.pagotarjeta.EntidadesPagoT;

import java.util.HashMap;
import java.util.Map;

public class Shipping {

    private Object id;
    private Object firstName;
    private Object lastName;
    private Object company;
    private Object streetAddress;
    private Object extendedAddress;
    private Object locality;
    private Object region;
    private Object postalCode;
    private Object countryName;
    private Object countryCodeAlpha2;
    private Object countryCodeAlpha3;
    private Object countryCodeNumeric;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(Object streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Object getExtendedAddress() {
        return extendedAddress;
    }

    public void setExtendedAddress(Object extendedAddress) {
        this.extendedAddress = extendedAddress;
    }

    public Object getLocality() {
        return locality;
    }

    public void setLocality(Object locality) {
        this.locality = locality;
    }

    public Object getRegion() {
        return region;
    }

    public void setRegion(Object region) {
        this.region = region;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public Object getCountryName() {
        return countryName;
    }

    public void setCountryName(Object countryName) {
        this.countryName = countryName;
    }

    public Object getCountryCodeAlpha2() {
        return countryCodeAlpha2;
    }

    public void setCountryCodeAlpha2(Object countryCodeAlpha2) {
        this.countryCodeAlpha2 = countryCodeAlpha2;
    }

    public Object getCountryCodeAlpha3() {
        return countryCodeAlpha3;
    }

    public void setCountryCodeAlpha3(Object countryCodeAlpha3) {
        this.countryCodeAlpha3 = countryCodeAlpha3;
    }

    public Object getCountryCodeNumeric() {
        return countryCodeNumeric;
    }

    public void setCountryCodeNumeric(Object countryCodeNumeric) {
        this.countryCodeNumeric = countryCodeNumeric;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}