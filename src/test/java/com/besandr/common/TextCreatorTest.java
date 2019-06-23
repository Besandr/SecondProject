package com.besandr.common;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class TextCreatorTest {

    private TextCreator textCreator;

    @Before
    public void init(){
        textCreator = new TextCreator();
    }

    @Test
    public void testConvertInputStreamToString() {

        String source = "This is\n" +
                        "a text string";

        ByteArrayInputStream bais = new ByteArrayInputStream(source.getBytes());

        String result = textCreator.convertInputStreamToString(bais);

        assertEquals("Strings must be equal", source, result);

    }

    @Test
    public void testRemoveTabulationAndExtraSpaces(){

        String source = "This   \t\t  is the \tsource     string   ";
        String expected = "This is the source string ";

        String result = textCreator.removeTabulationAndExtraSpaces(source);

        assertEquals("Returned string must be without tabulation sign and with single spaces",
                expected, result);

    }
}