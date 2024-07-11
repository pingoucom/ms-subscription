package com.pingou.mssubscription.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String title;
    private double monthlyPrice;
    private double semiAnnualPrice;

    @ElementCollection
    private List<String> features;

    @Enumerated(EnumType.STRING)
    private PlanFlag flag;

    private Date createdAt;

    @ElementCollection
    private List<String> idCachacas;

    @ElementCollection
    private List<String> usersId;
}
