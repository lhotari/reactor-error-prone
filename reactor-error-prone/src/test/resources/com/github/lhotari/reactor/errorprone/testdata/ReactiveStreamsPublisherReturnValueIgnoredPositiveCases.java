package com.github.lhotari.reactor.errorprone.testdata;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.reactivestreams.Publisher;
import reactor.core.publisher.*;

import java.util.Arrays;

public class ReactiveStreamsPublisherReturnValueIgnoredPositiveCases {
    private static Publisher getPublisher() {
        return null;
    }

    private static Flux getFlux() {
        return null;
    }

    private static Mono getMono() {
        return null;
    }

    {
        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        getPublisher();
        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        getFlux();
        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        getMono();

        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        Arrays.asList(1, 2, 3).forEach(n -> getPublisher());
        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        Arrays.asList(1, 2, 3).forEach(n -> getFlux());
        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        Arrays.asList(1, 2, 3).forEach(n -> getMono());
    }

    private abstract static class IgnoringParent<T> {
        @CanIgnoreReturnValue
        abstract T ignoringFunction();
    }

    private class NonIgnoringPublisherChild extends IgnoringParent<Publisher<Integer>> {
        @Override
        Publisher<Integer> ignoringFunction() {
            return null;
        }
    }

    private class NonIgnoringFluxChild extends IgnoringParent<Flux<Integer>> {
        @Override
        Flux<Integer> ignoringFunction() {
            return null;
        }
    }

    private class NonIgnoringMonoChild extends IgnoringParent<Mono<Integer>> {
        @Override
        Mono<Integer> ignoringFunction() {
            return null;
        }
    }

    public void inheritenceTest() {
        NonIgnoringPublisherChild publisherChild = new NonIgnoringPublisherChild();
        NonIgnoringFluxChild fluxChild = new NonIgnoringFluxChild();
        NonIgnoringMonoChild monoChild = new NonIgnoringMonoChild();

        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        publisherChild.ignoringFunction();
        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        fluxChild.ignoringFunction();
        // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
        monoChild.ignoringFunction();
    }

    public void conditional() {
        if (false) {
            // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
            getPublisher();
            // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
            getFlux();
            // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.
            getMono();
        }

        return;
    }
}
