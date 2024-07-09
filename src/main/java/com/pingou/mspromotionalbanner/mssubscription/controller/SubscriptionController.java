package com.pingou.mspromotionalbanner.mssubscription.controller;

import com.pingou.mspromotionalbanner.mssubscription.model.Subscription;
import com.pingou.mspromotionalbanner.mssubscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

private final SubscriptionService subscriptionService;

public SubscriptionController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
}

@GetMapping
public ResponseEntity<List<Subscription>> getAllSubscriptions() {
    List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
    return ResponseEntity.ok(subscriptions);
}

@GetMapping("/{id}")
public ResponseEntity<Subscription> getSubscriptionById(@PathVariable String id) {
    Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);
    return subscription.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

@PostMapping
public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
    Subscription createdSubscription = subscriptionService.createSubscription(subscription);
    return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<Subscription> updateSubscription(@PathVariable String id, @RequestBody Subscription subscription) {
    Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscription);
    return ResponseEntity.ok(updatedSubscription);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteSubscription(@PathVariable String id) {
    subscriptionService.deleteSubscription(id);
    return ResponseEntity.noContent().build();
}

@GetMapping("/{subscriptionId}/ultimas-cachacas")
public ResponseEntity<List<String>> ultimasCachacas(@PathVariable String subscriptionId) {
    List<String> ultimasCachacas = subscriptionService.ultimasCachacas(subscriptionId);
    return ResponseEntity.ok(ultimasCachacas);
}

@PostMapping("/{subscriptionId}/adicionar-cachacas")
public ResponseEntity<Subscription> adicionarCachacas(@PathVariable String subscriptionId,
                                                      @RequestParam String novaCachaca1,
                                                      @RequestParam String novaCachaca2) {
    Subscription updatedSubscription = subscriptionService.adicionarCachacas(subscriptionId, novaCachaca1, novaCachaca2);
    return ResponseEntity.ok(updatedSubscription);
}

@GetMapping("/subsByUser/{userId}")
public ResponseEntity<Subscription> getByIdUser(@PathVariable String userId) {
    Subscription subscriptions = subscriptionService.getByIdUser(userId);
    return ResponseEntity.ok(subscriptions);
}

@PostMapping("/subsByUser/{userId}/{subsId}")
public void createSubscriptionByUser(@PathVariable String userId, @PathVariable String subsId) {
    subscriptionService.createSubscriptionByUser(userId, subsId);
}
}
