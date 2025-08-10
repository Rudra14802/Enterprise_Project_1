package com.example.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    /** Load markets from a static JSON file in resources (no DB writes). */
    public Flux<Market> loadMarketsFromStaticJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Market> markets = mapper.readValue(
                    new ClassPathResource("markets.json").getInputStream(),
                    new TypeReference<List<Market>>() {}
            );
            return Flux.fromIterable(markets);
        } catch (Exception e) {
            e.printStackTrace();
            return Flux.empty();
        }
    }

    /** Load the static JSON and persist it into MongoDB. */
    public Mono<Void> importStaticJsonIntoMongo() {
        return loadMarketsFromStaticJson()
                .collectList()
                .flatMapMany(repository::saveAll)
                .then();
    }
}
