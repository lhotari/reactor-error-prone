package com.github.lhotari.reactor.errorprone;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.matchers.Matchers;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.Tree.Kind;

import static com.google.errorprone.BugPattern.SeverityLevel.WARNING;
import static com.google.errorprone.matchers.Matchers.*;

/**
 * Checks that Reactor StepVerifier return values aren't ignored.
 */
@BugPattern(
        name = "ReactorStepVerifierReturnValueIgnored",
        summary =
                "Returned Reactor StepVerifier & StepVerifier.LastStep must be checked. Ignoring a returned StepVerifier or StepVerifier.LastStep means that the verification "
                        + "isn't completed",
        explanation =
                "Methods that ignore StepVerifier or StepVerifier.Step return values typically  "
                        + "indicate errors.\n\nIf you don’t use the returned value and complete it with one of the verify* methods, "
                        + "the verification checks will never be handled.",
        linkType = BugPattern.LinkType.CUSTOM,
        link = "https://github.com/lhotari/reactor-error-prone/wiki/ReactorStepVerifierReturnValueIgnored",
        severity = WARNING)
@AutoService(BugChecker.class)
public final class ReactorStepVerifierReturnValueIgnored extends AbstractReturnValueIgnored {
    private static final Matcher<ExpressionTree> MATCHER =
            allOf(
                    Matchers.kindIs(Kind.METHOD_INVOCATION),
                    anyOf(isSubtypeOf("reactor.test.StepVerifier"), isSubtypeOf("reactor.test.StepVerifier.LastStep")),
                    not(AbstractReturnValueIgnored::hasCanIgnoreReturnValueAnnotation));

    @Override
    public Matcher<? super ExpressionTree> specializedMatcher() {
        return MATCHER;
    }
}
