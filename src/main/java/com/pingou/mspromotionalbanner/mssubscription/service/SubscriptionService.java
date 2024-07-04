package com.pingou.mspromotionalbanner.mssubscription.service;

import com.pingou.mspromotionalbanner.mssubscription.model.Subscription;

import com.pingou.mspromotionalbanner.mssubscription.model.dto.SubscriptionDTO;
import com.pingou.mspromotionalbanner.mssubscription.repository.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionName(subscriptionDTO.getSubscriptionName());
        subscription.setDescription(subscriptionDTO.getDescription());
        subscription.setPrice(subscriptionDTO.getPrice());


        return subscriptionRepository.save(subscription);
    }

    public void deleteSubscription(Long id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Subscription not found for this id : " + id);
        }
    }

    public Subscription updateSubscription(Long id, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found for this id : " + id));

        subscription.setSubscriptionName(subscriptionDTO.getSubscriptionName());
        subscription.setDescription(subscriptionDTO.getDescription());
        subscription.setPrice(subscriptionDTO.getPrice());

        return subscriptionRepository.save(subscription);
    }

    public Subscription getSubscription(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found for this id : " + id));
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription subscribeToSubscription(Long subscriptionId, Long userId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found for this id : " + subscriptionId));

        if (!subscription.getUserIds().contains(userId)) {
            subscription.getUserIds().add(userId);
            return subscriptionRepository.save(subscription);
        } else {
            throw new RuntimeException("User already subscribed to this subscription");
        }
    }

//    public Long findSubscriptionIdByUserId(Long userId) {
//        for (Subscription subscription : subscriptionRepository.findAll()) {
//            if (subscription.getUserIds().contains(userId)) {
//                return subscription.getId();
//            }
//        }
//        throw new RuntimeException("Subscription not found for this user id : " + userId);
//    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        System.out.println(userId);
        return subscriptionRepository.findAll().stream()
                .filter(subscription -> subscription.getUserIds().contains(userId))
                .collect(Collectors.toList());
    }

}
