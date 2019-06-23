package com.besandr.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class TextTest {

    @Test
    public void testCreateSentenceListSizeMustBe3() {

        Text text = new Text("");

        String textString = "There are three cats... There are three rats. There are three cats again.";

        assertEquals(3, text.createSentencesList(textString).size());

    }
}