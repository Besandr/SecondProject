package com.besandr.common;

/**
 * This class encapsulates creation of particular
 * text elements
 */
class TextElementFactory {

    TextElement getTextElement(TextElement.TextElementType type, String content) {

        switch (type) {
            case SENTENCE:
                return new Sentence(content);
            case WORD:
                return new Word(content);
            case LETTER:
                return new Letter(content.charAt(0));
            case PUNCTUATION:
                return new Punctuation(content.charAt(0));
            case WHITE_SPACE:
                return new WhiteSpace(' ');
            default:
                throw new IllegalArgumentException("Unknown type of text element");
        }

    }
}
