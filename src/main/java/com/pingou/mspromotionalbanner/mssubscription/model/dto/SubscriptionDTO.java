package com.pingou.mspromotionalbanner.mssubscription.model.dto;

import com.pingou.mspromotionalbanner.mssubscription.model.PlanFlag;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;
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
