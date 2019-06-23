package com.besandr.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class represents sentence as a composite part of whole text
 */
public class Sentence implements TextElement {

    private List<TextElement> elements;

    Sentence(String sentenceString) {
        elements = getWordsAndPunctuation(sentenceString);
    }

    /**
     * Parses the given string with a regular expression,
     * creates a list with text elements which can be a word, a punctuation
     * and a white space, and fills this list with founded in the sentence elements
     * @param sentenceString - the given sentence string
     * @return - the created list which elements and its order corresponds with parts
     * of the given sentence
     */
    private List<TextElement> getWordsAndPunctuation(String sentenceString) {

        List<TextElement> elements = new ArrayList<>();

        String sentenceElementPattern = "(\\s?)([\\p{Alpha}]+)([,;\\-\"\\s!.?]+)";

        Pattern pattern = Pattern.compile(sentenceElementPattern, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(sentenceString);

        TextElementFactory factory = new TextElementFactory();

        while (matcher.find()) {

            if (!matcher.group(1).isEmpty()) {
                elements.add(factory.getTextElement(TextElementType.WHITE_SPACE, ""));
            }

            elements.add(factory.getTextElement(TextElementType.WORD, matcher.group(2)));

            addPunctuationToList(matcher.group(3), elements);

        }

        return elements;
    }

    /**
     * Parses the given string which contains punctuation and white spaces
     * and adds founded symbols to the given list
     * @param punctuationsString - string with punctuation
     * @param elements - list which must have added elements
     */
    private void addPunctuationToList(String punctuationsString, List<TextElement> elements) {

        String punctuationPattern = "(\\s?)([,;\\-\"!.?]?)";

        Pattern pattern = Pattern.compile(punctuationPattern);
        Matcher matcher = pattern.matcher(punctuationsString);

        TextElementFactory factory = new TextElementFactory();
        while (matcher.find()) {

            if (!matcher.group(1).isEmpty()) {
                elements.add(factory.getTextElement(TextElementType.WHITE_SPACE, ""));
            }

            if (!matcher.group(2).isEmpty()) {

                TextElement punctuation = factory.getTextElement(TextElementType.PUNCTUATION, matcher.group(2));
                elements.add(punctuation);

            }
        }
    }


    @Override
    public String toString(){
        return elements.stream().map(TextElement::toString).collect(Collectors.joining());
    }

}
