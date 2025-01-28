package org.example;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoAppl;
import org.spoofax.terms.StrategoConstructor;
import org.strategoxt.lang.Context;
import front.trans.trans;
import front.trans.desugar_0_0;
import org.spoofax.interpreter.terms.ITermFactory;

public class Main {
    public static void main(String[] args) {
        // Initialise the Stratego code
        final Context c = trans.init();
        // If you have Stratego code from multiple sources to initialise, you can use the Context object from the first
        //  initialisation as an argument to initialisation of the subsequent Stratego code.

        final ITermFactory tf = c.getFactory();

        final IStrategoTerm exampleInput = tf.makeAppl(tf.makeConstructor("Front", 0));
        System.out.println(exampleInput);

        // You can also reading in ATerms from file using the org.spoofax.terms.io.binary.TermReader class.

        // Pick a fun strategy/rule to call. desugar takes zero strategy arguments and zero term arguments, so is
        //  mangled to desugar_0_0.
        final IStrategoTerm result = desugar_0_0.instance.invoke(c, exampleInput);
        System.out.println(result);

        if(!result.match(tf.makeAppl(tf.makeConstructor("FrontDesugared", 0)))) {
            System.err.println("Unexpected failure of desugar call, expected FrontDesugared but got " + result);
        }

        // Use the termfactory from the context given by initialisation for building terms yourself! This way will
        //  the terms you build match what the Stratego code expects. Consider the following demonstration:
        final IStrategoTerm unusableTerm = new StrategoAppl(new StrategoConstructor("Front", 0), new IStrategoTerm[0], null);

        final IStrategoTerm failedResult = desugar_0_0.instance.invoke(c, unusableTerm);
        if(failedResult == null) {
            System.out.println("desugar call failed on term built with wrong term factory");
        }
    }
}