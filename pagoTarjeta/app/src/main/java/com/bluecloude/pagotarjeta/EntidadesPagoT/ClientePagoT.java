package com.bluecloude.pagotarjeta.EntidadesPagoT;

import java.util.List;

public class ClientePagoT {

    private String Nombre;
    private String Apellido;
    private String Mail;
    private String Telefono;
    private String IdClienteBrain;
    private String TokenBrain;

    private List<PaymentMethod> paymentMethods;

    public void setIdClienteBrain(String idClienteBrain) {
        IdClienteBrain = idClienteBrain;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public String getIdClienteBrain() {
        return IdClienteBrain;
    }

    public void setId(String IdClienteBrain) {
        this.IdClienteBrain = IdClienteBrain;
    }

    public String getTokenBrain() {
        return TokenBrain;
    }

    public void setTokenBrain(String TokenBrain) {
        this.TokenBrain = TokenBrain;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }


}
