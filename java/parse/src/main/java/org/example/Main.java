package org.example;

import org.metaborg.parsetable.IParseTable;
import org.metaborg.parsetable.ParseTableReadException;
import org.metaborg.parsetable.ParseTableVariant;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.jsglr2.*;
import org.spoofax.jsglr2.imploder.ImploderVariant;
import org.spoofax.jsglr2.parseforest.IParseForest;
import org.spoofax.jsglr2.parseforest.ParseForestConstruction;
import org.spoofax.jsglr2.parseforest.ParseForestRepresentation;
import org.spoofax.jsglr2.parser.ParserVariant;
import org.spoofax.jsglr2.reducing.Reducing;
import org.spoofax.jsglr2.stack.StackRepresentation;
import org.spoofax.jsglr2.stack.collections.ActiveStacksRepresentation;
import org.spoofax.jsglr2.stack.collections.ForActorStacksRepresentation;
import org.spoofax.jsglr2.tokens.TokenizerVariant;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException, ParseTableReadException {
        if (args.length != 1) {
            System.out.println("USAGE: provide 1 input file as argument.");
            System.exit(1);
        }

        parseAndImplode(Files.readString(Path.of(args[0])), getParser(getParseTable()));
    }

    private static JSGLR2Implementation<IParseForest, ?, ?, IStrategoTerm, ?, ?> getParser(IParseTable parseTable) {
        final ParserVariant parserVariant = new ParserVariant(ActiveStacksRepresentation.standard(), ForActorStacksRepresentation.standard(), ParseForestRepresentation.standard(), ParseForestConstruction.standard(), StackRepresentation.standard(), Reducing.standard(), false);
        final JSGLR2Variant jsglr2Variant = new JSGLR2Variant(parserVariant, ImploderVariant.standard(), TokenizerVariant.standard());
        return (JSGLR2Implementation<IParseForest, ?, ?, IStrategoTerm, ?, ?>) jsglr2Variant.getJSGLR2(parseTable);
    }

    private static IParseTable getParseTable() throws ParseTableReadException, IOException {
        final InputStream parseTableInputStream = Main.class.getClassLoader().getResourceAsStream("front.tbl");
        final ParseTableVariant tableVariant = new ParseTableVariant();
        return tableVariant.parseTableReader().read(parseTableInputStream);
    }

    private static void parseAndImplode(String fileContents, JSGLR2Implementation<?, ?, ?, IStrategoTerm, ?, ?> jsglr2) {
        JSGLR2Result<IStrategoTerm> result = jsglr2.parseResult(fileContents);

        if (result.isSuccess()) {
            System.out.println(((JSGLR2Success<IStrategoTerm>) result).ast);
        } else {
            System.out.println(((JSGLR2Failure<IStrategoTerm>) result).parseFailure.failureCause.causeMessage());
        }
    }
}
