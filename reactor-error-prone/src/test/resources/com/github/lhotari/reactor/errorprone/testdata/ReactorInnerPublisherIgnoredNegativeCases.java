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

    private static <T> Flux<T> getFluxAnyType() {
        return null;
    }

    private static <T> Mono<T> getMonoAnyType() {
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
        getFlux().then(null).subscribe();
        getMono().then().subscribe();
        getMono().thenMany(null).subscribe();
        getMono().thenEmpty(null).subscribe();
        getMono().thenReturn(null).subscribe();
        getMono().then(null).subscribe();

        Arrays.asList(1, 2, 3).forEach(n -> getFlux().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFlux().then(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().thenReturn(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMono().then(null).subscribe());
    }

    {
        getFluxAnyType().then().subscribe();
        getFluxAnyType().thenMany(null).subscribe();
        getFluxAnyType().thenEmpty(null).subscribe();
        getFluxAnyType().then(null).subscribe();
        getMonoAnyType().then().subscribe();
        getMonoAnyType().thenMany(null).subscribe();
        getMonoAnyType().thenEmpty(null).subscribe();
        getMonoAnyType().thenReturn(null).subscribe();
        getMonoAnyType().then(null).subscribe();

        Arrays.asList(1, 2, 3).forEach(n -> getFluxAnyType().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxAnyType().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxAnyType().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxAnyType().then(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoAnyType().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoAnyType().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoAnyType().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoAnyType().thenReturn(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoAnyType().then(null).subscribe());
    }

    // raw types
    {
        getFluxRaw().then().subscribe();
        getFluxRaw().thenMany(null).subscribe();
        getFluxRaw().thenEmpty(null).subscribe();
        getFluxRaw().then(null).subscribe();
        getMonoRaw().then().subscribe();
        getMonoRaw().thenMany(null).subscribe();
        getMonoRaw().thenEmpty(null).subscribe();
        getMonoRaw().thenReturn(null).subscribe();
        getMonoRaw().then(null).subscribe();

        Arrays.asList(1, 2, 3).forEach(n -> getFluxRaw().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxRaw().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxRaw().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getFluxRaw().then(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().then().subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().thenMany(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().thenEmpty(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().thenReturn(null).subscribe());
        Arrays.asList(1, 2, 3).forEach(n -> getMonoRaw().then(null).subscribe());
    }

    public void conditional() {
        if (false) {
            getFlux().then().subscribe();
            getFlux().thenMany(null).subscribe();
            getFlux().thenEmpty(null).subscribe();
            getFlux().then(null).subscribe();
            getMono().then().subscribe();
            getMono().thenMany(null).subscribe();
            getMono().thenEmpty(null).subscribe();
            getMono().thenReturn(null).subscribe();
            getMono().then(null).subscribe();
        }

        if (false) {
            getFluxAnyType().then().subscribe();
            getFluxAnyType().thenMany(null).subscribe();
            getFluxAnyType().thenEmpty(null).subscribe();
            getFluxAnyType().then(null).subscribe();
            getMonoAnyType().then().subscribe();
            getMonoAnyType().thenMany(null).subscribe();
            getMonoAnyType().thenEmpty(null).subscribe();
            getMonoAnyType().thenReturn(null).subscribe();
            getMonoAnyType().then(null).subscribe();
        }

        // raw types
        if (false) {
            getFluxRaw().then().subscribe();
            getFluxRaw().thenMany(null).subscribe();
            getFluxRaw().thenEmpty(null).subscribe();
            getFluxRaw().then(null).subscribe();
            getMonoRaw().then().subscribe();
            getMonoRaw().thenMany(null).subscribe();
            getMonoRaw().thenEmpty(null).subscribe();
            getMonoRaw().thenReturn(null).subscribe();
            getMonoRaw().then(null).subscribe();
        }

        return;
    }
}
