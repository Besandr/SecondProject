package com.besandr.individual;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code TextProcessor} contains method for processing a text
 * according to received individual task
 */

class TextProcessor {

    /**
     * Finds such a word in a first sentence of a given text which is
     * absent in all the others sentences.
     * @param text - the given text
     * @return - the unique word
     */
    String findUniqueWord(String text) {

        List<String> sentences = createSentencesList(text);

        if (sentences.isEmpty()) {
            return null;
        }

        String wordPattern = "\\b[\\p{Alpha}]+\\b";

        String firstSentence = sentences.get(0);
        Pattern pattern = Pattern.compile(wordPattern);
        Matcher matcher = pattern.matcher(firstSentence);

        // iterate through the words of first sentence
        while (matcher.find()) {

            String word = matcher.group(0);

            boolean isWordFound = true;

            // iterating through all text sentences (except first) and trying to find current word
            for (int i = 1; i < sentences.size(); i++) {

                if (sentences.get(i).contains(word)) {
                    // the current word has found
                    // break the sentences loop and try to find another word
                    isWordFound = false;
                    break;
                }

            }

            if (isWordFound) {
                // the target word was found
                return word;
            }
        }

        // the target word wasn't found
        return null;
    }

    /**
     * Removes the longest subsequence between given symbols in each
     * sentence of a given text
     * @param text - the given text
     * @param start - the first symbol of sequence have to be removed
     * @param end - the last symbol of sequence have to be removed
     * @return - the processed text
     */
    String removeSubSequenceFromEachSentence(String text, char start, char end) {

        List<String> sentences = createSentencesList(text);

        Pattern sequencePattern = Pattern.compile(start + ".*" + end);

        // Iterating through all text sentences
        for (int i = 0; i < sentences.size(); i++) {

            Matcher matcher = sequencePattern.matcher(sentences.get(i));

            if (matcher.find()) {
                // A current sentence has a target subsequence of symbols.
                // Removing it by replacing with "" string and replacing
                // the modified sentence string itself in the sentences list
                sentences.set(i, matcher.replaceFirst(""));
            }

        }

        return String.join("", sentences);
    }

    List<String> createSentencesList(String textString) {

        List<String> sentences = new ArrayList<>();

//        String sentencePattern = "\\s?[\\p{Alpha},;\\-\"\\s]+[.?!]";
        String sentencePattern = "[^.?!]+[.?!]+";

        Pattern pattern = Pattern.compile(sentencePattern, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(textString);

        while (matcher.find()) {

            sentences.add(matcher.group(0));

        }

        return sentences;
    }

}
