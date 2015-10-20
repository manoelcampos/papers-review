package com.manoelcampos.papersreview.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.CharacterFilterReader;
import org.jbibtex.Key;
import org.jbibtex.ObjectResolutionException;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.jbibtex.Value;

/**
 *
 * @author Manoel Campos da Silva Filho <manoelcampos at gmail.com>
 */
public class BibTexReader {
    private BibTeXParser parser;
    private BibTeXDatabase database;

    public BibTexReader(final InputStream inputStream) {
        try {
            try (final CharacterFilterReader filterReader = new CharacterFilterReader(new InputStreamReader(inputStream))) {
                parser = this.getBibTexParserInstance();
                database = parser.parse(filterReader);
            }        
        } catch (IOException | ParseException | ObjectResolutionException | TokenMgrException e) {
            throw new RuntimeException(
                    "It was not possible to pase the BibTex file " + inputStream + ". Maybe the file is invalid\n");        
        }
    }
    
    private BibTeXParser getBibTexParserInstance() throws ParseException {
        return new BibTeXParser() {
            @Override
            public void checkStringResolution(org.jbibtex.Key key, org.jbibtex.BibTeXString string) {
                if(string == null){
                    System.err.println("Unresolved string: \"" + key.getValue() + "\"");
                }
            }

            @Override
            public void checkCrossReferenceResolution(org.jbibtex.Key key, org.jbibtex.BibTeXEntry entry) {
                if(entry == null){
                    System.err.println("Unresolved cross-reference: \"" + key.getValue() + "\"");
                }
            }
        };
    }         
    
    public Collection<BibTeXEntry> getEntriesCollection() {
        return database.getEntries().values();
    }

    public void parseAllPapers() {
        for (BibTeXEntry bibEntry : getEntriesCollection()) {
        }
    }
    
    public String  getFieldValue(BibTeXEntry b, Key key){
        Value value = b.getField(key);
        if(value != null)
            return value.toUserString();
        return "";
    }
    
}
