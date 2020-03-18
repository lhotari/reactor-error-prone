package com.github.lhotari.reactor.errorprone;

import com.google.errorprone.VisitorState;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.*;
import com.sun.tools.javac.code.Symbol;

import static com.google.errorprone.util.ASTHelpers.getSymbol;
import static com.google.errorprone.util.ASTHelpers.hasAnnotation;

abstract class AbstractReturnValueIgnored extends com.google.errorprone.bugpatterns.AbstractReturnValueIgnored {
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

    static boolean hasCanIgnoreReturnValueAnnotation(ExpressionTree tree, VisitorState state) {
        Symbol untypedSymbol = getSymbol(tree);
        if (!(untypedSymbol instanceof Symbol.MethodSymbol)) {
            return false;
        }

        Symbol.MethodSymbol sym = (Symbol.MethodSymbol) untypedSymbol;
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
