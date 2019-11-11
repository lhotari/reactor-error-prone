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
        getFlux().then().subscribe();

        Arrays.asList(1, 2, 3).forEach(n -> getFlux().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().then().subscribe());
    }

    public void conditional() {
        if (false) {
            getFlux().then().subscribe();
            getMono().then().subscribe();
        }

        return;
    }
}
