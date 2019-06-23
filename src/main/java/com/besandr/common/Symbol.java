package com.besandr.common;

/**
 * This class represents an abstract symbol which can be a letter,
 * a punctuation and a white space
 */
abstract class Symbol implements TextElement {

    private char value;

    Symbol(char value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }
}
