package com.pingou.mssubscription.service;

import com.pingou.mssubscription.model.PlanFlag;
import com.pingou.mssubscription.model.Subscription;
import com.pingou.mssubscription.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> getSubscriptionById(String id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription createSubscription(Subscription subscription) {
        subscription.setCreatedAt(new Date());
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(String id, Subscription subscriptionDetails) {
        return subscriptionRepository.findById(id)
                .map(subscription -> {
                    updateSubscriptionDetails(subscription, subscriptionDetails);
                    return subscriptionRepository.save(subscription);
                })
                .orElseThrow(() -> new RuntimeException("Subscription not found with id " + id));
    }

    private void updateSubscriptionDetails(Subscription subscription, Subscription subscriptionDetails) {
        subscription.setTitle(subscriptionDetails.getTitle());
        subscription.setMonthlyPrice(subscriptionDetails.getMonthlyPrice());
        subscription.setSemiAnnualPrice(subscriptionDetails.getSemiAnnualPrice());
        subscription.setFeatures(subscriptionDetails.getFeatures());
        setPlanFlag(subscription, subscriptionDetails.getMonthlyPrice());
        subscription.setIdCachacas(subscriptionDetails.getIdCachacas());
    }

    private void setPlanFlag(Subscription subscription, double monthlyPrice) {
        if (monthlyPrice < 30) {
            subscription.setFlag(PlanFlag.cheapest);
        } else if (monthlyPrice < 45) {
            subscription.setFlag(PlanFlag.recommended);
        } else {
            subscription.setFlag(PlanFlag.premium);
        }
    }

    public void deleteSubscription(String id) {
        subscriptionRepository.deleteById(id);
    }

    public List<String> ultimasCachacas(String subscriptionId) {
        Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(subscriptionId);
        if (subscriptionOptional.isPresent()) {
            Subscription subscription = subscriptionOptional.get();
            List<String> idCachacas = subscription.getIdCachacas();
            int size = idCachacas.size();
            return size >= 2 ? idCachacas.subList(size - 2, size) : idCachacas;
        } else {
            throw new RuntimeException("Subscription not found with id " + subscriptionId);
        }
    }

    public Subscription adicionarCachacas(String subscriptionId, String novaCachaca1, String novaCachaca2) {
        Optional<Subscription> subscriptionOptional = subscriptionRepository.findById(subscriptionId);
        if (subscriptionOptional.isPresent()) {
            Subscription subscription = subscriptionOptional.get();
            List<String> idCachacas = subscription.getIdCachacas();
            idCachacas.add(novaCachaca1);
            idCachacas.add(novaCachaca2);
            subscription.setIdCachacas(idCachacas);
            return subscriptionRepository.save(subscription);
        } else {
            throw new RuntimeException("Subscription not found with id " + subscriptionId);
        }
    }

    public void createSubscriptionByUser(String userId, String subscriptionId){
        Subscription subscription = subscriptionRepository.findById(subscriptionId).get();
        List<String> usersId = subscription.getUsersId();
        usersId.add(userId);
        subscription.setUsersId(usersId);
        subscriptionRepository.save(subscription);
    }

    public Subscription getByIdUser(String userId){
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        for(Subscription subscription : subscriptions){
            List<String> usersId = subscription.getUsersId();
            if(usersId.contains(userId)){
                return subscription;
            }
        }
        return null;
    }
}
