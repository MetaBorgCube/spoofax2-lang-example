package org.example;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoAppl;
import org.spoofax.terms.StrategoConstructor;
import org.strategoxt.lang.Context;
import front.trans.trans;
import front.trans.desugar_0_0;
import front.trans.desugar_otf_0_0;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.jsglr.client.imploder.ImploderOriginTermFactory;
import org.spoofax.terms.TermFactory;
import org.strategoxt.lang.Context;
import org.spoofax.terms.attachments.OriginAttachment;
import mb.jsglr.shared.ImploderAttachment;

public class Main {
    public static void main(String[] args) {
        // Initialise the Stratego code. The custom context is to pass in the special term factory that can do origin tracking.
        final Context c = trans.init(new Context(new ImploderOriginTermFactory(new TermFactory())));
        // If you have Stratego code from multiple sources to initialise, you should use the Context object from the first
        //  initialisation as an argument to initialisation of the subsequent Stratego code.

        final ITermFactory tf = c.getFactory();

        final IStrategoTerm exampleInput = tf.makeAppl(tf.makeConstructor("Front", 0));
        // Add a dummy ImploderAttachment to make origin tracking work. Normally the JSGLR parser will set these attachments.
        // This attachment is only for demonstration purposes of origin tracking.
        exampleInput.putAttachment(ImploderAttachment.createCompactPositionAttachment("no file", 0, 0, 0, 0));
        System.out.println("example input: " + exampleInput);

        // You can also reading in ATerms from file using the org.spoofax.terms.io.binary.TermReader class.

        // Pick a fun strategy/rule to call. desugar-otf takes zero strategy arguments and zero term arguments, so is
        //  mangled to desugar_otf_0_0.
        final IStrategoTerm result = desugar_otf_0_0.instance.invoke(c, exampleInput);
        System.out.println("result: " + result);

        // The result has an "origin" pointing back to the original input term, tracked due to the use of the ImploderOriginTermFactory
        //  and the ImploderAttachment that marks the input term as an "origin root".
        System.out.println("origin of result: " + OriginAttachment.get(result).getOrigin());

        // Note that origin tracking in Stratego is finnicky, if you run desugar_0_0 it won't have a tracked origin,
        //  see https://spoofax.dev/background/stratego/origin-tracking/#origin-tracking-in-stratego for more information.

        if(!result.match(tf.makeAppl(tf.makeConstructor("FrontDesugared", 0)))) {
            System.err.println("Unexpected failure of desugar call, expected FrontDesugared but got " + result);
        }

        // Use the termfactory from the context given by initialisation for building terms yourself! This way
        //  the terms you build match what the Stratego code expects. Consider the following demonstration:
        final IStrategoTerm unusableTerm = new StrategoAppl(new StrategoConstructor("Front", 0), new IStrategoTerm[0], null);

        final IStrategoTerm failedResult = desugar_0_0.instance.invoke(c, unusableTerm);
        if(failedResult == null) {
            System.out.println("desugar call failed on term built without term factory");
        }

        // The same holds for using a different term factory instance: the terms from there won't match with
        //  the ones from the Stratego code, and can cause strange behaviour.
    }
}