package com.besandr.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class App 
{
    public static void main( String[] args ) {

        new App().go();

    }

    public void go(){

        TextCreator textCreator = new TextCreator();

        File file = new File("text.txt");

        try (FileInputStream fis = new FileInputStream(file)) {

            Text text = textCreator.createText(fis);

            System.out.println(text);

        } catch (FileNotFoundException e) {
            System.out.println("Can't find a file");
        } catch (IOException e) {
            System.out.println("Unfortunately bad things have happen during reading the file");
        }
    }
}
