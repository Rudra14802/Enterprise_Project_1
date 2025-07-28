package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/markets")
@AllArgsConstructor
public class MarketController {

    private final MarketService service;

    public MarketController(MarketService service) {
        this.service = service;
    }
    @GetMapping
    public Flux<Market> getAllMarkets() {
        return service.getAllMarkets();
    }

    @PostMapping
    public Mono<Market> createMarket(@RequestBody Market market) {
        return service.createMarket(market);
    }

    @PutMapping("/{marketID}")
    public Mono<ResponseEntity<Market>> updateMarket(@PathVariable String marketID, @RequestBody Market market) {
        return service.updateMarket(marketID, market)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{marketID}")
    public Mono<Void> deleteMarket(@PathVariable String marketID) {
        return service.deleteMarket(marketID);
    }
}
