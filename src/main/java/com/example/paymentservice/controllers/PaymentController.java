package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.CreatePaymentLinkRequestDto;
import com.example.paymentservice.services.PaymentService;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentSerice;

    public PaymentController(PaymentService paymentService) {
        this.paymentSerice = paymentService;
    }

    @PostMapping("/")
    public String createPaymentLink(CreatePaymentLinkRequestDto createPaymentLinkRequestDto) {
        String payment = null;
        try{
            payment = paymentSerice.createPaymentLink(createPaymentLinkRequestDto.getOrderId());
        } catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return payment;
    }
}
