package com.github.lhotari.reactor.errorprone;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReactorInnerPublisherIgnoredTest {
    private final CompilationTestHelper compilationHelper =
            CompilationTestHelper.newInstance(ReactorInnerPublisherIgnored.class, getClass());

    @Test
    public void positiveCases() {
        compilationHelper.addSourceFile("ReactorInnerPublisherIgnoredPositiveCases.java").doTest();
    }

    @Test
    public void negativeCases() {
        compilationHelper.addSourceFile("ReactorInnerPublisherIgnoredNegativeCases.java").doTest();
    }

    @Test
    public void reactorFluxOfFlux() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import reactor.core.publisher.*;",
                        "class Test {",
                        "  Flux<Flux<Integer>> getFlux() { return null; }",
                        "  Mono<Void> then() {",
                        "    // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution",
                        "    return getFlux().then();",
                        "  }",
                        "  Mono<Void> thenEmpty() {",
                        "    // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution",
                        "    return getFlux().thenEmpty(null);",
                        "  }",
                        "  Flux<Integer> thenMany() {",
                        "    // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution",
                        "    return getFlux().thenMany(null);",
                        "  }",
                        "  Mono<Void> and() {",
                        "    // BUG: Diagnostic contains: The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution",
                        "    return getFlux().then(null);",
                        "  }",
                        "}")
                .doTest();
    }
}
