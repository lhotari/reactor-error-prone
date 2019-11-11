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

    private static Flux getFluxRaw() {
        return null;
    }

    private static Mono getMonoRaw() {
        return null;
    }

    {
        getFlux().then().subscribe();
        getFlux().thenMany(null).subscribe();
        getFlux().thenEmpty(null).subscribe();
        getFlux().and(null).subscribe();
        getMono().then().subscribe();
        getMono().thenMany(null).subscribe();
        getMono().thenEmpty(null).subscribe();
        getMono().thenReturn(null).subscribe();
        getMono().and(null).subscribe();

        Arrays.asList(1, 2, 3).forEach(n -> getFlux().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().and(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenReturn(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().and(null).subscribe());
    }

    // raw types
    {
        getFluxRaw().then().subscribe();
        getFluxRaw().thenMany(null).subscribe();
        getFluxRaw().thenEmpty(null).subscribe();
        getFluxRaw().and(null).subscribe();
        getMonoRaw().then().subscribe();
        getMonoRaw().thenMany(null).subscribe();
        getMonoRaw().thenEmpty(null).subscribe();
        getMonoRaw().thenReturn(null).subscribe();
        getMonoRaw().and(null).subscribe();

        Arrays.asList(1, 2, 3).forEach(n -> getFluxRaw().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxRaw().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxRaw().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxRaw().and(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().thenReturn(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().and(null).subscribe());
    }

    public void conditional() {
        if (false) {
            getFlux().then().subscribe();
            getFlux().thenMany(null).subscribe();
            getFlux().thenEmpty(null).subscribe();
            getFlux().and(null).subscribe();
            getMono().then().subscribe();
            getMono().thenMany(null).subscribe();
            getMono().thenEmpty(null).subscribe();
            getMono().thenReturn(null).subscribe();
            getMono().and(null).subscribe();
        }

        // raw types
        if (false) {
            getFluxRaw().then().subscribe();
            getFluxRaw().thenMany(null).subscribe();
            getFluxRaw().thenEmpty(null).subscribe();
            getFluxRaw().and(null).subscribe();
            getMonoRaw().then().subscribe();
            getMonoRaw().thenMany(null).subscribe();
            getMonoRaw().thenEmpty(null).subscribe();
            getMonoRaw().thenReturn(null).subscribe();
            getMonoRaw().and(null).subscribe();
        }

        return;
    }
}
