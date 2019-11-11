package com.github.lhotari.reactor.errorprone;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReactorInnerPublisherIgnoredTest {
    private final CompilationTestHelper compilationHelper =
            ReactiveTypesFixture.addReactiveTypeStubs(CompilationTestHelper.newInstance(ReactorInnerPublisherIgnored.class, getClass()));

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
                        "  Mono<Void> f() {",
                        "    // BUG: Diagnostic contains: Calling then on a Flux or Mono that contains inner Flux/Mono means that it is never scheduled for execution",
                        "    return getFlux().then();",
                        "  }",
                        "}")
                .doTest();
    }
}
