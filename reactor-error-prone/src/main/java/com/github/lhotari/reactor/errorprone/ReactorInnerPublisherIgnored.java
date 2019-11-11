package com.github.lhotari.reactor.errorprone;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.MethodInvocationTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.suppliers.Suppliers;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.tools.javac.code.*;

import static com.google.errorprone.BugPattern.SeverityLevel.WARNING;
import static com.google.errorprone.matchers.Description.NO_MATCH;
import static com.google.errorprone.matchers.method.MethodMatchers.instanceMethod;
import static com.google.errorprone.predicates.TypePredicates.isDescendantOfAny;

@BugPattern(
        name = "ReactorInnerPublisherIgnored",
        summary =
                "The inner Flux|Mono (Publisher) is ignored and it is never scheduled for execution",
        explanation =
                "Calling then|thenEmpty|thenMany|thenReturn|and on a Flux<Flux<?>> type or Mono<Mono<?>> generally "
                        + "indicate errors.\n\nThe inner publisher will never execute." +
                        "It also means the error case is not being handled",
        linkType = BugPattern.LinkType.CUSTOM,
        link = "https://github.com/lhotari/reactor-error-prone/wiki/ReactorInnerPublisherIgnored",
        severity = WARNING)
@AutoService(BugChecker.class)
public class ReactorInnerPublisherIgnored extends BugChecker implements MethodInvocationTreeMatcher {
    Matcher<ExpressionTree> COMPLETES_IGNORING_ELEMENTS_METHOD =
            instanceMethod()
                    .onClass(
                            isDescendantOfAny(
                                    ImmutableList.of(
                                            "reactor.core.publisher.Flux",
                                            "reactor.core.publisher.Mono"
                                    )
                            )
                    )
                    .namedAnyOf("then", "thenEmpty", "thenMany", "thenReturn", "and");

    @Override
    public Description matchMethodInvocation(MethodInvocationTree tree, VisitorState state) {
        if (!COMPLETES_IGNORING_ELEMENTS_METHOD.matches(tree, state)) {
            return NO_MATCH;
        }

        Type fluxType = Suppliers.typeFromString("reactor.core.publisher.Flux").get(state);
        Type monoType = Suppliers.typeFromString("reactor.core.publisher.Mono").get(state);
        Type receiverType = ASTHelpers.getReceiverType(tree);
        Type fluxOrMonoType = ASTHelpers.isSubtype(fluxType, receiverType, state) ? fluxType : monoType;

        Type typeArg = extractTypeArgAsMemberOfSupertype(
                receiverType,
                fluxOrMonoType.asElement(),
                0,
                state.getTypes());

        if (typeArg == null || !(ASTHelpers.isSubtype(fluxType, typeArg, state) || ASTHelpers.isSubtype(monoType, typeArg, state))) {
            return NO_MATCH;
        }

        return describeMatch(tree);
    }

    private static final Type extractTypeArgAsMemberOfSupertype(
            Type type, Symbol superTypeSym, int typeArgIndex, Types types) {
        Type superType = types.asSuper(type, superTypeSym);
        if (superType == null) {
            return null;
        }
        com.sun.tools.javac.util.List<Type> tyargs = superType.getTypeArguments();
        if (tyargs.size() <= typeArgIndex) {
            // raw type
            return null;
        }
        return tyargs.get(typeArgIndex);
    }

}
