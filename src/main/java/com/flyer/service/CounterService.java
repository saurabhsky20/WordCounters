package com.flyer.service;

import com.flyer.model.WordFrequency;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CounterService {

    public PriorityQueue<WordFrequency> getWordFrequency(String text){
        //if text is null, empty or has just white spaces, we do not have anything to count
        if(text == null || text.trim().length() == 0) return null;

        //removing the special characters from the text except spaces
        String cleanedText = text.replaceAll("[^a-zA-Z0-9]", "");
        //split the text based on spaces to get an array of words
        String[] words = cleanedText.split(" ");

        Map<String, Integer> frequencies = new HashMap<>();

        for(String word: words){
            //add frequency of each word in the hashmap ignoring the empty strings and ignoring the case('Case' and 'case' are treated as same)
            if(word.length()>0){
                frequencies.put(word.toLowerCase(), frequencies.getOrDefault(word.toLowerCase(), 0)+1);
            }

        }

        //creating comparator to sort the WordFrequencies priorityQueue in descending order of frequency
        Comparator<WordFrequency> frequencyComparator = new Comparator<WordFrequency>() {
            @Override
            public int compare(WordFrequency o1, WordFrequency o2) {
                return o2.getFrequency() - o1.getFrequency();
            }
        };

        PriorityQueue<WordFrequency> wordFrequencies = new PriorityQueue<>(frequencyComparator);

        for(String word:frequencies.keySet()){
            WordFrequency wf = new WordFrequency(word, frequencies.get(word));
            wordFrequencies.add(wf);
        }

        return wordFrequencies;
    }
}
