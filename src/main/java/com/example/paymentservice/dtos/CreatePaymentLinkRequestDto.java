package com.example.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentLinkRequestDto {
    private Long orderId;

    public Long getOrderId() { // Explicit getter method
        return orderId;
    }
}
