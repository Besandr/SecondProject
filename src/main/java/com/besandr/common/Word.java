package com.besandr.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Word implements TextElement {

    private List<Letter> letters;

    Word(String wordString) {
        letters = getSymbols(wordString);
    }

    private List<Letter> getSymbols(String wordString) {

        List<Letter> symbols = new ArrayList<>();

        TextElementFactory factory = new TextElementFactory();

        for (char c : wordString.toCharArray()) {
            symbols.add((Letter) factory.getTextElement(TextElementType.LETTER, String.valueOf(c)));
        }

        return symbols;
    }

    @Override
    public String toString(){
        return letters.stream().map(TextElement::toString).collect(Collectors.joining());
    }

}
