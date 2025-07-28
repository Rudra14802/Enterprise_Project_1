package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class MarketService {

	private final MarketRepository repository;

    public MarketService(MarketRepository repository) {
        this.repository = repository;
    }

    public Flux<Market> getAllMarkets() {
        return repository.findAll();
    }

    public Mono<Market> createMarket(Market market) {
        return repository.save(market);
    }

    public Mono<Market> updateMarket(String id, Market updated) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setStockSymbol(updated.getStockSymbol());
                    existing.setPrice(updated.getPrice());
                    existing.setAvailableQty(updated.getAvailableQty());
                    existing.setListedExchangeName(updated.getListedExchangeName());
                    return repository.save(existing);
                });
    }

    public Mono<Void> deleteMarket(String id) {
        return repository.deleteById(id);
    }
}
