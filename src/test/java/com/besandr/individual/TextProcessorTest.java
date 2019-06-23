package com.besandr.individual;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TextProcessorTest {

    private TextProcessor textProcessor;

    @Before
    public void init() {
        textProcessor = new TextProcessor();
    }

    @Test
    public void createSentenceListSizeShouldBeFive() {

        String text = "Declarative sentence. Interrogative sentence? Exclamatory sentence! Unfinished sentence... Another one \"exclamatory\"!";

        String fourth = " Unfinished sentence...";

        List<String> stringList = textProcessor.createSentencesList(text);

        assertEquals("List size should be equal to 5", 5, stringList.size());

        assertEquals("The fourth sentence should be: \"Unfinished sentence...\"", fourth, stringList.get(3));

    }


    @Test
    public void testFindUniqueWordShouldFindCats() {

        String text = "There are three cats. There are three rats? There are three hats!";
        String expectedWord ="cats";

        String resultWord = textProcessor.findUniqueWord(text);

        assertNotNull("Returned word should not be null", resultWord);
        assertEquals(expectedWord, resultWord);
    }

    @Test
    public void testFindUniqueWordShouldReturnNull() {

        String text = "There are three cats. There are three rats? There are three cats again!";

        String resultWord = textProcessor.findUniqueWord(text);

        assertNull("Returned word should be null", resultWord);

    }

    @Test
    public void removeSubSequenceShouldRemoveTextBetweenComas() {

        String text = "There are, three, cats. There are three rats, ,two, dogs, and one cat. There are three hats.";

        String expectedText = "There are cats. There are three rats and one cat. There are three hats.";

        assertEquals(expectedText, textProcessor.removeSubSequenceFromEachSentence(text, ',', ','));

    }

    @Test
    public void removeSubSequenceShouldRemoveTextFromHToA() {

        String text = "There are, three, cats. There are three rats, ,two, dogs, and one cat. There are three hats.";

        String expectedText = "Tts. Tt. Tts.";

        assertEquals(expectedText, textProcessor.removeSubSequenceFromEachSentence(text, 'h', 'a'));

    }

    @Test
    public void removeSubSequenceShouldNotChangeText() {

        String text = "There are, three, cats. There are three rats, ,two, dogs, and one cat. There are three hats.";

        String expectedText = "There are, three, cats. There are three rats, ,two, dogs, and one cat. There are three hats.";

        assertEquals(expectedText, textProcessor.removeSubSequenceFromEachSentence(text, 'd', 'h'));

    }
}