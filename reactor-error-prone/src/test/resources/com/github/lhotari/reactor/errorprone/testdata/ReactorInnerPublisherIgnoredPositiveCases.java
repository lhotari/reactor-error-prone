package com.github.lhotari.reactor.errorprone.testdata;

import reactor.core.publisher.*;

import java.util.Arrays;

public class ReactorInnerPublisherIgnoredPositiveCases {
    private static Flux<Flux<Integer>> getFluxOfFlux() {
        return null;
    }

    private static Flux<Mono<Integer>> getFluxOfMono() {
        return null;
    }

    private static Mono<Mono<Integer>> getMonoOfMono() {
        return null;
    }

    private static Mono<Flux<Integer>> getMonoOfFlux() {
        return null;
    }

    {
        // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
        getFluxOfFlux().then().subscribe();
        // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
        getFluxOfMono().then().subscribe();
        // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
        getMonoOfMono().then().subscribe();
        // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
        getMonoOfFlux().then().subscribe();

        // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfFlux().then().subscribe());
        // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfMono().then().subscribe());
        // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfMono().then().subscribe());
        // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfFlux().then().subscribe());
    }

    public void conditional() {
        if (false) {
            // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
            getFluxOfFlux().then().subscribe();
            // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
            getFluxOfMono().then().subscribe();
            // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
            getMonoOfMono().then().subscribe();
            // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution
            getMonoOfFlux().then().subscribe();
        }

        return;
    }
}
