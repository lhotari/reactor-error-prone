package com.github.lhotari.reactor.errorprone.testdata;

import reactor.test.StepVerifier;

public class ReactorStepVerifierReturnValueIgnoredPositiveCases {
    void firstStepReturnValueIgnored() {
        // BUG: Diagnostic contains: Returned Reactor StepVerifier & StepVerifier.LastStep must be checked.
        StepVerifier.create(null);
    }

    void stepVerifierReturnValueIgnored() {
        // BUG: Diagnostic contains: Returned Reactor StepVerifier & StepVerifier.LastStep must be checked.
        StepVerifier.create(null).expectComplete();
    }

    void stepReturnValueIgnored() {
        // BUG: Diagnostic contains: Returned Reactor StepVerifier & StepVerifier.LastStep must be checked.
        StepVerifier.create(null).expectNext(null);
    }
}
