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
 * Checks that Reactive Streams Publisher return values aren't ignored
 * <p>
 * Based on https://github.com/google/error-prone/blob/a2704300027454d2db5e701f8b2444fb35b765b4/core/src/main/java/com/google/errorprone/bugpatterns/RxReturnValueIgnored.java
 * <p>
 * See {@link BugPattern} annotation.
 */
@BugPattern(
        name = "ReactiveStreamsPublisherReturnValueIgnored",
        summary =
                "Returned Reactive Streams Publisher must be checked. Ignoring a returned Publisher means it is never "
                        + "scheduled for execution",
        explanation =
                "Methods that return an ignored Publisher generally "
                        + "indicate errors.\n\nIf you don’t check the return value of these methods, the "
                        + "Publisher may never execute. It also means the error case is not being handled",
        linkType = BugPattern.LinkType.CUSTOM,
        link = "https://github.com/lhotari/reactor-error-prone/wiki/ReactiveStreamsPublisherReturnValueIgnored",
        severity = WARNING)
@AutoService(BugChecker.class)
public final class ReactiveStreamsPublisherReturnValueIgnored extends AbstractReturnValueIgnored {
    private static final Matcher<ExpressionTree> MATCHER =
            allOf(
                    Matchers.kindIs(Kind.METHOD_INVOCATION),
                    isSubtypeOf("org.reactivestreams.Publisher"),
                    not(AbstractReturnValueIgnored::hasCanIgnoreReturnValueAnnotation));

    @Override
    public Matcher<? super ExpressionTree> specializedMatcher() {
        return MATCHER;
    }
}
