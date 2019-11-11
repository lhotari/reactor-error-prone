package com.github.lhotari.reactor.errorprone;

import com.google.errorprone.CompilationTestHelper;

class ReactiveTypesFixture {

    static CompilationTestHelper addReactiveTypeStubs(CompilationTestHelper compilationTestHelper) {
        return compilationTestHelper
                // Reactive Streams Publisher stub
                .addSourceLines(
                        "org/reactivestreams/Publisher.java",
                        "package org.reactivestreams;",
                        "public interface Publisher<T> {}"
                )
                // Reactor CorePublisher stub
                .addSourceLines(
                        "reactor/core/CorePublisher.java",
                        "package reactor.core;",
                        "public interface CorePublisher<T> extends org.reactivestreams.Publisher<T> {}"
                )
                // Reactor Flux stub
                .addSourceLines(
                        "reactor/core/publisher/Flux.java",
                        "package reactor.core.publisher;",
                        "public class Flux<T> implements reactor.core.CorePublisher<T> {",
                        "  public Mono<Void> then() { return null; }",
                        "  public Mono<Void> thenEmpty(org.reactivestreams.Publisher<Void> other) { return null; }",
                        "  public <V> Flux<V> thenMany(org.reactivestreams.Publisher<V> other) { return null; }",
                        "  public Mono<Void> and(org.reactivestreams.Publisher<?> other) { return null; }",
                        "  public void subscribe() { }",
                        "}"
                )
                // Reactor Mono stub
                .addSourceLines(
                        "reactor/core/publisher/Mono.java",
                        "package reactor.core.publisher;",
                        "public class Mono<T> implements reactor.core.CorePublisher<T>  {",
                        "  public Mono<Void> then() { return null; }",
                        "  public Mono<Void> thenEmpty(org.reactivestreams.Publisher<Void> other) { return null; }",
                        "  public <V> Flux<V> thenMany(org.reactivestreams.Publisher<V> other) { return null; }",
                        "  public <V> Mono<V> thenReturn(V value) { return null; }",
                        "  public Mono<Void> and(org.reactivestreams.Publisher<?> other) { return null; }",
                        "  public void subscribe() { }",
                        "}"
                );
    }

}
