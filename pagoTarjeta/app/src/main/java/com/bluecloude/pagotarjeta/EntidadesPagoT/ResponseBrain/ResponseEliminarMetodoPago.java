package com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain;

public class ResponseEliminarMetodoPago {

   private Boolean estatus;
   private String mensaje;

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
