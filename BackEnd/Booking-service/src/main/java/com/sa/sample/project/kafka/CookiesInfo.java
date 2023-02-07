package com.sa.sample.project.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CookiesInfo {
    private String email;
    private String fullName;
}
