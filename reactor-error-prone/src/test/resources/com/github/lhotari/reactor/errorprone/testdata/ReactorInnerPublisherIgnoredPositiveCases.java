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

    // then
    {
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getFluxOfFlux().then().subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getFluxOfMono().then().subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfMono().then().subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfFlux().then().subscribe();

        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfFlux().then().subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfMono().then().subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfMono().then().subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfFlux().then().subscribe());
    }

    // thenEmpty
    {
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getFluxOfFlux().thenEmpty(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getFluxOfMono().thenEmpty(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfMono().thenEmpty(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfFlux().thenEmpty(null).subscribe();

        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfFlux().thenEmpty(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfMono().thenEmpty(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfMono().thenEmpty(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfFlux().thenEmpty(null).subscribe());
    }

    // thenMany
    {
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getFluxOfFlux().thenMany(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getFluxOfMono().thenMany(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfMono().thenMany(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfFlux().thenMany(null).subscribe();

        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfFlux().thenMany(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfMono().thenMany(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfMono().thenMany(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfFlux().thenMany(null).subscribe());
    }

    // thenReturn
    {
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfMono().thenReturn(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfFlux().thenReturn(null).subscribe();

        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfMono().thenReturn(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfFlux().thenReturn(null).subscribe());
    }

    // and
    {
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getFluxOfFlux().then(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getFluxOfMono().then(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfMono().then(null).subscribe();
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        getMonoOfFlux().then(null).subscribe();

        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfFlux().then(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getFluxOfMono().then(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfMono().then(null).subscribe());
        // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
        Arrays.asList(1, 2, 3).forEach(n -> getMonoOfFlux().then(null).subscribe());
    }

    public void conditional() {
        // then
        if (false) {
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getFluxOfFlux().then().subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getFluxOfMono().then().subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfMono().then().subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfFlux().then().subscribe();
        }

        // thenEmpty
        if (false) {
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getFluxOfFlux().thenEmpty(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getFluxOfMono().thenEmpty(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfMono().thenEmpty(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfFlux().thenEmpty(null).subscribe();
        }

        // thenMany
        if (false) {
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getFluxOfFlux().thenMany(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getFluxOfMono().thenMany(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfMono().thenMany(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfFlux().thenMany(null).subscribe();
        }

        // thenReturn
        if (false) {
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfMono().thenReturn(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfFlux().thenReturn(null).subscribe();
        }

        // and
        if (false) {
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getFluxOfFlux().then(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getFluxOfMono().then(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfMono().then(null).subscribe();
            // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution
            getMonoOfFlux().then(null).subscribe();
        }

        return;
    }
}
