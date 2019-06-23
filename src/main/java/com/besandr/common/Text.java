package com.besandr.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The OOD representation of Text. Consist of {@code Sentence},
 * {@code Word}, {@code Letter}, {@code Punctuation} and a
 * {@code WhiteSpace}
 */
public class Text {

    private List<Sentence> sentences;

    Text(String textString) {
        sentences = createSentencesList(textString);
    }

    /**
     * Parses the given text string, finds sentences and fill a created list
     * with them
     * @param textString - string which contains text
     * @return - list with founded sentences
     */
    List<Sentence> createSentencesList(String textString) {

        List<Sentence> sentences = new ArrayList<>();

        String sentencePattern = "\\s?[\\p{Alpha},:;\\-\"\\s]+[.?!]+";

        Pattern pattern = Pattern.compile(sentencePattern, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(textString);

        TextElementFactory factory = new TextElementFactory();

        while (matcher.find()) {

            Sentence sentence = (Sentence) factory.getTextElement(TextElement.TextElementType.SENTENCE, matcher.group(0));

            sentences.add(sentence);

        }

        return sentences;
    }

    @Override
    public String toString(){
        return sentences.stream().map(TextElement::toString).collect(Collectors.joining());
    }
}
