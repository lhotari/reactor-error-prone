package com.github.lhotari.reactor.errorprone;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.bugpatterns.AbstractReturnValueIgnored;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.matchers.*;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.*;
import com.sun.source.tree.Tree.Kind;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symbol.MethodSymbol;

import static com.google.errorprone.BugPattern.SeverityLevel.WARNING;
import static com.google.errorprone.matchers.Matchers.*;
import static com.google.errorprone.util.ASTHelpers.getSymbol;
import static com.google.errorprone.util.ASTHelpers.hasAnnotation;

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
        linkType = BugPattern.LinkType.NONE,
        severity = WARNING)
@AutoService(BugChecker.class)
public final class ReactiveStreamsPublisherReturnValueIgnored extends AbstractReturnValueIgnored {
    private static final Matcher<ExpressionTree> MATCHER =
            allOf(
                    Matchers.kindIs(Kind.METHOD_INVOCATION),
                    isSubtypeOf("org.reactivestreams.Publisher"),
                    not(ReactiveStreamsPublisherReturnValueIgnored::hasCanIgnoreReturnValueAnnotation));

    @Override
    public Description matchMethodInvocation(MethodInvocationTree tree, VisitorState state) {
        Description description = super.matchMethodInvocation(tree, state);
        return description.equals(Description.NO_MATCH) ? Description.NO_MATCH : describeMatch(tree);
    }

    @Override
    public Description matchMemberReference(MemberReferenceTree tree, VisitorState state) {
        Description description = super.matchMemberReference(tree, state);
        return description.equals(Description.NO_MATCH) ? Description.NO_MATCH : describeMatch(tree);
    }

    @Override
    public Matcher<? super ExpressionTree> specializedMatcher() {
        return MATCHER;
    }

    private static boolean hasCanIgnoreReturnValueAnnotation(ExpressionTree tree, VisitorState state) {
        Symbol untypedSymbol = getSymbol(tree);
        if (!(untypedSymbol instanceof MethodSymbol)) {
            return false;
        }

        MethodSymbol sym = (MethodSymbol) untypedSymbol;
        // Directly has @CanIgnoreReturnValue
        if (ASTHelpers.hasAnnotation(sym, CanIgnoreReturnValue.class, state)) {
            return true;
        }

        // If a super-class's method is annotated with @CanIgnoreReturnValue, we only honor that
        // if the super-type returned the exact same type. This lets us catch issues where a
        // superclass was annotated with @CanIgnoreReturnValue but the parent did not intend to
        // return an Reactive Streams publisher type
        return ASTHelpers.findSuperMethods(sym, state.getTypes()).stream()
                .anyMatch(
                        superSym ->
                                hasAnnotation(superSym, CanIgnoreReturnValue.class, state)
                                        && superSym.getReturnType().tsym.equals(sym.getReturnType().tsym));
    }
}
