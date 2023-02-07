package com.miu.edu.cs590.project.notification.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntityDTO {
    private Booking booking;
    private Room room;
    private CookiesInfo cookiesInfo;
}
