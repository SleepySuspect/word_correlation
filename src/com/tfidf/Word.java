package com.tfidf;

import java.util.List;

//句子
public class Word {
    private Integer id;
    private String text;

    public Word(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

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
}
