package com.tfidf;

import java.util.List;

public class Sentence {
    private Integer id;
    private String text;
    private List<Word> words;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public Sentence(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
