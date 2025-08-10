package com.example.demo;

import jakarta.validation.Valid;
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
    public Mono<Market> createMarket(@Valid @RequestBody Market market) {
        return service.createMarket(market);
    }

    @PutMapping("/{marketID}")
    public Mono<ResponseEntity<Market>> updateMarket(@PathVariable String marketID, @Valid @RequestBody Market market) {
        return service.updateMarket(marketID, market)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{marketID}")
    public Mono<Void> deleteMarket(@PathVariable String marketID) {
        return service.deleteMarket(marketID);
    }
    
    /** Returns markets from the static JSON file (no DB). */
    @GetMapping("/static")
    public Flux<Market> getStaticMarkets() {
        return service.loadMarketsFromStaticJson();
    }

    /** Imports the static JSON into MongoDB, returns 204 No Content when done. */
    @PostMapping("/static/import")
    public Mono<ResponseEntity<Void>> importStatic() {
        return service.importStaticJsonIntoMongo()
                .thenReturn(ResponseEntity.noContent().build());
    }
}
