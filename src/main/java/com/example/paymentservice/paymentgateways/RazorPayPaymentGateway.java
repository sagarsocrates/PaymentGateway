package com.example.paymentservice.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.example.paymentservice.paymentgateways.RazorPayPaymentGateway;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

@Component
@Primary
public class RazorPayPaymentGateway implements PaymentGateway {

    private RazorpayClient razorpayClient;

    public RazorPayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String createPaymentLink(Long orderId) {

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000); // last 2 digits means the decimal vallues, so this is 10.00
        paymentLinkRequest.put("currency","INR");
        // paymentLinkRequest.put("accept_partial",true);
        // paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1691097057);
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Payment for order Id" + orderId.toString());
        JSONObject customer = new JSONObject();
        customer.put("name","+919000090000");
        customer.put("contact","Sagar");
        customer.put("email","sagarsocrates22@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Scaler");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://www.scaler.com/academy/mentee-dashboard/core-curriculum/m/124/classes");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = null;
        try {
            payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        } catch ( RazorpayException e){
            throw new RuntimeException(e);
        }

        return payment.toString();
    }
}
