package com.github.lhotari.reactor.errorprone.testdata;

import reactor.core.publisher.*;

import java.util.Arrays;

public class ReactorInnerPublisherIgnoredNegativeCases {
    private static Flux<Integer> getFlux() {
        return null;
    }

    private static Mono<Integer> getMono() {
        return null;
    }

    {
        getFlux().then().subscribe();
        getFlux().thenMany(null).subscribe();
        getFlux().thenEmpty(null).subscribe();
        getMono().then().subscribe();
        getMono().thenMany(null).subscribe();
        getMono().thenEmpty(null).subscribe();
        getMono().thenReturn(null).subscribe();

        Arrays.asList(1, 2, 3).forEach(n -> getFlux().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenReturn(null).subscribe());
    }

    public void conditional() {
        if (false) {
            getFlux().then().subscribe();
            getFlux().thenMany(null).subscribe();
            getFlux().thenEmpty(null).subscribe();
            getMono().then().subscribe();
            getMono().thenMany(null).subscribe();
            getMono().thenEmpty(null).subscribe();
            getMono().thenReturn(null).subscribe();
        }

        return;
    }
}
