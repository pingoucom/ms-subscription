package com.pingou.mspromotionalbanner.mssubscription.repository;

import com.pingou.mspromotionalbanner.mssubscription.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}