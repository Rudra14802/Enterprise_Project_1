package com.example.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MarketRepository extends ReactiveMongoRepository<Market, String> {
}
