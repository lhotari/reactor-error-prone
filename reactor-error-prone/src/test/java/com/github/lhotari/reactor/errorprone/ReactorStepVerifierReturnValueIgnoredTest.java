package com.github.lhotari.reactor.errorprone;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Test;

import static com.github.lhotari.reactor.errorprone.ReactiveTypesFixture.addReactiveTypeStubs;

public class ReactorStepVerifierReturnValueIgnoredTest {
    private final CompilationTestHelper compilationHelper = addReactiveTypeStubs(CompilationTestHelper.newInstance(ReactorStepVerifierReturnValueIgnored.class, getClass()))
            .addSourceLines(
                    "reactor/test/StepVerifier.java",
                    "package reactor.test;",
                    "public interface StepVerifier {",
                    "   java.time.Duration verify() throws AssertionError;",
                    "   static <T> FirstStep<T> create(org.reactivestreams.Publisher<? extends T> publisher) { return null; }",
                    "   interface FirstStep<T> extends Step<T> {}",
                    "   interface Step<T> extends LastStep {",
                    "       Step<T> expectNext(T t);",
                    "   }",
                    "   interface LastStep {",
                    "       StepVerifier expectComplete();",
                    "       java.time.Duration verifyComplete();",
                    "   }",
                    "}"
            );

    @Test
    public void positiveCases() {
        compilationHelper.addSourceFile("ReactorStepVerifierReturnValueIgnoredPositiveCases.java").doTest();
    }

    @Test
    public void negativeCases() {
        compilationHelper.addSourceFile("ReactorStepVerifierReturnValueIgnoredNegativeCases.java").doTest();
    }

    @Test
    public void firstStepReturnValueIgnored() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import reactor.test.StepVerifier;",
                        "class Test {",
                        "  void f() {",
                        "    // BUG: Diagnostic contains: Returned Reactor StepVerifier & StepVerifier.LastStep must be checked.",
                        "    StepVerifier.create(null);",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    public void stepVerifierReturnValueIgnored() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import reactor.test.StepVerifier;",
                        "class Test {",
                        "  void f() {",
                        "    // BUG: Diagnostic contains: Returned Reactor StepVerifier & StepVerifier.LastStep must be checked.",
                        "    StepVerifier.create(null).expectComplete();",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    public void stepReturnValueIgnored() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import reactor.test.StepVerifier;",
                        "class Test {",
                        "  void f() {",
                        "    // BUG: Diagnostic contains: Returned Reactor StepVerifier & StepVerifier.LastStep must be checked.",
                        "    StepVerifier.create(null).expectNext(null);",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    public void completedVerifierCompiles() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import reactor.test.StepVerifier;",
                        "class Test {",
                        "  void f() {",
                        "    StepVerifier.create(null).expectNext(null).expectComplete().verify();",
                        "  }",
                        "}")
                .doTest();
    }

    @Test
    public void completedStepCompiles() {
        compilationHelper
                .addSourceLines(
                        "Test.java",
                        "import reactor.test.StepVerifier;",
                        "class Test {",
                        "  void f() {",
                        "    StepVerifier.create(null).expectNext(null).verifyComplete();",
                        "  }",
                        "}")
                .doTest();
    }
}
