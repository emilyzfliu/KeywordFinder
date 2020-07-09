package com.example.keywordfinder.functionality;
import java.util.*;

public class TextTask {
    ArrayList<String> text;
    HashMap<String, Integer> tracker;
    HashMap<Integer, HashSet<String>> freq;
    int minLength = 7;
    ArrayList<String> display;
    ArrayList<String> fillIn;
    int sentencePointer;

    public TextTask(String in) {
        //initialize input text into text list
        text = new ArrayList<String>();
        tracker = new HashMap<String, Integer>();
        display = new ArrayList<String>();
        fillIn = new ArrayList<String>();
        String[] words = in.split(" ");
        for (String n: words) {
            text.add(n);
        }
        initializeTracker();
        freq = new HashMap<Integer, HashSet<String>>();
        sortTracker();
        makeDisplay();

        sentencePointer = 0;
    }


    private boolean isLetter(char c) {
        return c>='a' && c<='z';
    }

    //strip string of all punctuation.
    private String stripPunctuation(String s) {
        s = s.toLowerCase();
        String ret = "";
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (isLetter(c)) ret+=Character.toString(c);
        }
        return ret;
    }

    //initialize hashmap that tracks word frequency
    private void putInTracker(String s) {
        if (!tracker.containsKey(s)) tracker.put(s, 0);
        tracker.put(s, tracker.get(s)+1);
    }
    private void initializeTracker() {
        for (int i=0; i<text.size(); i++) {
            String s = text.get(i);
            putInTracker(stripPunctuation(s));
        }
    }

    private void addFrequency(int i, String s) {
        if (!freq.containsKey(i)) freq.put(i,  new HashSet<String>());
        freq.get(i).add(s);
    }

    private void sortTracker() {
        for (String s:tracker.keySet()) {
            addFrequency(tracker.get(s), s);
        }
    }

    private boolean unique(String s) {
        return freq.get(1).contains(s);
    }

    public void makeDisplay() {
        for (String s: text) {
            if (stripPunctuation(s).length()>=minLength && unique (stripPunctuation(s))) {
                fillIn.add(s);
                display.add("!!BLANK!!");
            }
            else display.add(s);
        }
    }

    public ArrayList<String> display() {
        return display;
    }

    public ArrayList<String> getWords() {
        return fillIn;
    }

    public ArrayList<String> currentSentence() {
        ArrayList<String> ret = new ArrayList<String>();
        String s = display.get(sentencePointer);
        while (sentencePointer<display.size() && s.charAt(s.length()-1)!='.') {
            ret.add(s);
            sentencePointer++;
            s = display.get(sentencePointer);
        }
        return ret;
    }
}
