package com.pingou.mspromotionalbanner.mssubscription.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "subscription_name")
    private String subscriptionName;

    @Column
    private String description;

    @Column
    private BigDecimal price;

//    @Column
//    @OneToMany(mappedBy = "subscription")
//    private List<User> user;

    @Column(name = "user_ids")
    @ElementCollection
    @CollectionTable(name = "subscription_ids", joinColumns = @JoinColumn(name = "subscription_id"))
    private List<Long> userIds;
}
