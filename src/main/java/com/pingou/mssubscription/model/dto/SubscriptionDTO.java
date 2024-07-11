package com.pingou.mssubscription.model.dto;

import com.pingou.mssubscription.model.PlanFlag;
import lombok.Data;

import java.util.List;

@Data
public class SubscriptionDTO {
    private String id;
    private String title;
    private double monthlyPrice;
    private double semiAnnualPrice;
    private List<String> features;
    private PlanFlag flag;
    private List<String> idCachacas;
}
