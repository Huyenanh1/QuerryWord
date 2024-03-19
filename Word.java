package a1_2101140008;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Word {
    public static Set<String> stopWords;
    public String keyword;
    public String text;
    public String suffix_part;
    public String prefix_part;

    public Word(String keyword) throws FileNotFoundException {
        this.keyword = keyword;
        stopWords = new HashSet<>();
        File file = new File("stopwords.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            stopWords.add(sc.nextLine());
        }

        if (keyword.equals("")){
            text=""; prefix_part=""; suffix_part="";
        }
        Pattern digit = Pattern.compile("\\d");
        Matcher digit_match = digit.matcher(keyword);
        if (digit_match.find()){
            text=""; prefix_part=""; suffix_part="";
        }

        Pattern text_part = Pattern.compile("[A-Za-z-]+");
        Matcher text_match = text_part.matcher(keyword);
        if (keyword.matches("[$%#@!&*=+^]+") && !text_match.find()){
            text=""; prefix_part=""; suffix_part="";
        }
        if (!digit_match.find() && text_match.find()) {
            text = text_match.group(0);
            prefix_part = keyword.substring(0, text_match.start());
            suffix_part = keyword.substring(text_match.end());
        }
    }

    //Returns true if the word is a keyword
    public boolean isKeyword(){
        if (keyword.matches(".*\\s.*")){
            return false;
        }
        for (String current : stopWords) {
            if (current.equalsIgnoreCase(keyword)) {
                return false;
            }
        }
        return !Objects.equals(text, "");
    }

    //Returns the prefix part of the word
    public String getPrefix() {
        return prefix_part;
    }


    //Returns the suffix part of the word
    public String getSuffix(){
        return suffix_part;
    }


    public String getText() throws FileNotFoundException {
        if (!Word.createWord(keyword).isKeyword()) {
            text = keyword;
        }
        return text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        try {
            return (Word.createWord(keyword).getText()).equalsIgnoreCase(Word.createWord(word.keyword).getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return keyword;
    }

    public static Word createWord(String rawText) throws FileNotFoundException {
        return new Word(rawText);
    }
    public static boolean loadStopWords(String fileName) throws FileNotFoundException {
        if (fileName.equals("stopwords.txt")) {
            stopWords = new HashSet<>();
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                stopWords.add(sc.nextLine());
            }
            return true;
        }
        return false;
    }

}
