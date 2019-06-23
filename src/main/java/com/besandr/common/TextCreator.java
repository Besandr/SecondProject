package com.besandr.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Service class which purpose is prepare the source information
 * can be processed as {@code String} by {@code Text} class
 */
class TextCreator {


    Text createText(InputStream textInputStream) {

        String textAsString = convertInputStreamToString(textInputStream);

        textAsString = removeTabulationAndExtraSpaces(textAsString);

        return new Text(textAsString);
    }

    String removeTabulationAndExtraSpaces(String source) {

        return source.replaceAll("\\t|\\s+", " ");

    }

    String convertInputStreamToString(InputStream input) {

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.out.println("Can't get text from Input Stream");
        }

        return null;
    }

}
