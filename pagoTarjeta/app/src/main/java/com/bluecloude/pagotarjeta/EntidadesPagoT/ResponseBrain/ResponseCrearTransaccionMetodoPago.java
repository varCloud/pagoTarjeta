package com.bluecloude.pagotarjeta.EntidadesPagoT.ResponseBrain;

import com.bluecloude.pagotarjeta.EntidadesPagoT.AuthorizationExpiresAt;
import com.bluecloude.pagotarjeta.EntidadesPagoT.Billing;
import com.bluecloude.pagotarjeta.EntidadesPagoT.BillingDetails;
import com.bluecloude.pagotarjeta.EntidadesPagoT.CreatedAt;
import com.bluecloude.pagotarjeta.EntidadesPagoT.CreditCard;
import com.bluecloude.pagotarjeta.EntidadesPagoT.CreditCardDetails;
import com.bluecloude.pagotarjeta.EntidadesPagoT.Customer;
import com.bluecloude.pagotarjeta.EntidadesPagoT.CustomerDetails;
import com.bluecloude.pagotarjeta.EntidadesPagoT.Descriptor;
import com.bluecloude.pagotarjeta.EntidadesPagoT.DisbursementDetails;
import com.bluecloude.pagotarjeta.EntidadesPagoT.PaymentMethod;
import com.bluecloude.pagotarjeta.EntidadesPagoT.Shipping;
import com.bluecloude.pagotarjeta.EntidadesPagoT.ShippingDetails;
import com.bluecloude.pagotarjeta.EntidadesPagoT.StatusHistory;
import com.bluecloude.pagotarjeta.EntidadesPagoT.Subscription;
import com.bluecloude.pagotarjeta.EntidadesPagoT.SubscriptionDetails;
import com.bluecloude.pagotarjeta.EntidadesPagoT.Transaction;
import com.bluecloude.pagotarjeta.EntidadesPagoT.UpdatedAt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseCrearTransaccionMetodoPago{
    private Boolean success;
    private Transaction transaction;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}