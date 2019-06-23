package com.besandr.common;

/**
 * This class represents a white space
 */
public class WhiteSpace extends Symbol implements TextElement {

    private char value = ' ';

    protected WhiteSpace(char value) {
        super(value);
    }

    @Override
    public String toString(){
        return String.valueOf(value);
    }

}
