package com.github.lhotari.reactor.errorprone.testdata;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.reactivestreams.Publisher;
import reactor.core.publisher.*;

public class ReactiveStreamsPublisherReturnValueIgnoredNegativeCases {
    interface CanIgnoreMethod {
        @CanIgnoreReturnValue
        Publisher<Object> getPublisher();

        @CanIgnoreReturnValue
        Flux<Object> getFlux();

        @CanIgnoreReturnValue
        Mono<Object> getMono();
    }

    public static class CanIgnoreImpl implements CanIgnoreMethod {
        @Override
        public Publisher<Object> getPublisher() {
            return null;
        }

        @Override
        public Flux<Object> getFlux() {
            return null;
        }

        @Override
        public Mono<Object> getMono() {
            return null;
        }
    }

    static void callIgnoredInterfaceMethod() {
        new CanIgnoreImpl().getPublisher();
        new CanIgnoreImpl().getFlux();
        new CanIgnoreImpl().getMono();
    }

    @CanIgnoreReturnValue
    Publisher<Object> getPublisher() {
        return null;
    }

    @CanIgnoreReturnValue
    Flux<Object> getFlux() {
        return null;
    }

    @CanIgnoreReturnValue
    Mono<Object> getMono() {
        return null;
    }

    void checkIgnore() {
        getPublisher();
        getFlux();
        getMono();
    }
}
