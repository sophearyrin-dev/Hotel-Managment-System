package com.sa.sample.project.dto;

import com.sa.sample.project.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntityDTO {
    private Booking booking;
    private Room room;
    CreditCardDto paymentMethod;
}
