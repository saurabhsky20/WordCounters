package com.flyer.service;

import com.flyer.model.WordFrequency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CounterServiceTest {

    @Test
    @DisplayName("null text should return null queue")
    public void test_when_text_is_null(){

        CounterService cs = new CounterService();
        PriorityQueue<WordFrequency> q = cs.getWordFrequency(null);
        assertNull(q);
    }

    @Test
    @DisplayName("empty or whitespace only text should return null queue")
    public void test_when_text_is_empty_or_contains_just_whitespaces(){

        CounterService cs = new CounterService();
        //when text is empty
        PriorityQueue<WordFrequency> q = cs.getWordFrequency("");
        assertNull(q);

        //when text is empty
        q = cs.getWordFrequency("   ");
        assertNull(q);
    }

    @Test
    @DisplayName("valid text with just alphabets should return descending frequency ordered queue")
    public void test_when_text_is_valid(){

        CounterService cs = new CounterService();
        //when text is empty
        PriorityQueue<WordFrequency> q = cs.getWordFrequency("this is is some something that is this");
        assertNotNull(q);
        WordFrequency wf = q.poll();
        while(!q.isEmpty()){
            WordFrequency temp = q.poll();
            //asserting that the previous word prequency is greater than the current one
            assertTrue(temp.getFrequency()<= wf.getFrequency());
            wf = temp;
        }
    }

    @Test
    @DisplayName("valid text with alphabets and more than 1 space between words should return descending frequency ordered queue")
    public void test_when_text_is_valid_with_more_then_one_space_between_words(){

        CounterService cs = new CounterService();
        //when text is empty
        PriorityQueue<WordFrequency> q = cs.getWordFrequency("this is    is   some something that is this");
        assertNotNull(q);
        WordFrequency wf = q.poll();
        assertTrue(!wf.getWord().isEmpty());
        while(!q.isEmpty()){
            WordFrequency temp = q.poll();
            assertTrue(!temp.getWord().isEmpty());
            //asserting that the previous word prequency is greater than the current one
            assertTrue(temp.getFrequency()<= wf.getFrequency());
            wf = temp;
        }
    }

    @Test
    @DisplayName("valid text with special characters other than space should return descending frequency ordered queue ignoring the special characters")
    public void test_when_text_is_valid_with_special_characters(){

        CounterService cs = new CounterService();
        //when text is empty
        PriorityQueue<WordFrequency> q = cs.getWordFrequency("this ## is is ## some. something that is this");
        assertNotNull(q);
        WordFrequency wf = q.poll();
        assertTrue(!wf.getWord().contains("##"));
        assertTrue(!wf.getWord().contains("some."));

        while(!q.isEmpty()){
            WordFrequency temp = q.poll();
            //asserting the special character words are not part of the word list
            assertTrue(!temp.getWord().contains("##"));
            assertTrue(!temp.getWord().contains("some."));
            //asserting that the previous word prequency is greater than the current one
            assertTrue(temp.getFrequency()<= wf.getFrequency());
            wf = temp;
        }
    }


}
