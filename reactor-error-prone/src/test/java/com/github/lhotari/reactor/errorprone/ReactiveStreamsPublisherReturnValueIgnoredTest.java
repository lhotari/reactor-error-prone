package com.github.lhotari.reactor.errorprone;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class ReactiveStreamsPublisherReturnValueIgnoredTest {
    private final CompilationTestHelper compilationHelper =
            ReactiveTypesFixture.addReactiveTypeStubs(CompilationTestHelper.newInstance(ReactiveStreamsPublisherReturnValueIgnored.class, getClass()));

    @Test
    public void positiveCases() {
        compilationHelper.addSourceFile("ReactiveStreamsPublisherReturnValueIgnoredPositiveCases.java").doTest();
    }

    @Test
    public void negativeCases() {
        compilationHelper.addSourceFile("ReactiveStreamsPublisherReturnValueIgnoredNegativeCases.java").doTest();
    }

    @Test
    public void reactiveStreamsPublisher() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import org.reactivestreams.Publisher;",
                        "class Test {",
                        "  Publisher getPublisher() { return null; }",
                        "  void f() {",
                        "    // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.",
                        "    getPublisher();",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    public void reactorFlux() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import reactor.core.publisher.Flux;",
                        "class Test {",
                        "  Flux getFlux() { return null; }",
                        "  void f() {",
                        "    // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.",
                        "    getFlux();",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    public void reactorMono() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import reactor.core.publisher.Mono;",
                        "class Test {",
                        "  Mono getMono() { return null; }",
                        "  void f() {",
                        "    // BUG: Diagnostic contains: Reactive Streams Publisher must be checked.",
                        "    getMono();",
                        "  }",
                        "}")
                .doTest();
    }
}
