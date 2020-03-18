package com.github.lhotari.reactor.errorprone.testdata;

import reactor.test.StepVerifier;

public class ReactorStepVerifierReturnValueIgnoredNegativeCases {
    void stepVerifierIsVerified() {
        StepVerifier.create(null)
                .expectNext(null)
                .expectComplete()
                .verify();
    }

    void lastStepIsVerified() {
        StepVerifier.create(null)
                .expectNext(null)
                .verifyComplete();
    }
}
