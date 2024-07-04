package com.pingou.mspromotionalbanner.mssubscription.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SubscriptionDTO {
    private Long id;
    private String subscriptionName;
    private String description;
    private BigDecimal price;
    private List<Long> ids;
}
