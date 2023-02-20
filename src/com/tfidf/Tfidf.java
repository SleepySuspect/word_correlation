package com.tfidf;

import java.io.*;
import java.util.*;

//利用tfidf算法分析单词的相关性
//https://blog.csdn.net/weixin_43758551/article/details/113918690
public class Tfidf {
    public static void main(String[] args) {

        //句子
        List<Sentence> sentences = getSentences();

        //所有词
        List<List<Word>> allWord = getWords(sentences);
//        for (List<Word> words:
//                allWord) {
//            for (Word word :
//                    words) {
//                System.out.println(word);
//            }
//            System.out.println("&&&&&&&&&&&&&&&&&&&");
//        }

        //词集
        List<String> vocabulary = getVocabulary(allWord);
//        System.out.println(vocabulary.toString());
//        System.out.println(vocabulary.size());

        //词频向量化
        double[][] wordFrequency = getWordVector(allWord, vocabulary);
//        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
//        System.out.println(Arrays.toString(wordFrequency[0]));
//        System.out.println(Arrays.toString(wordFrequency[3]));

        //最终结果
        double[][] res = new double[sentences.size()][sentences.size()];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res.length; j++) {
                res[i][j] = cal(wordFrequency[i], wordFrequency[j]);
            }
        }

        //输出相似度结果
        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }


    }

    /**
     * 从文件中读取需要计算的句子
     * @return
     */
    public static List<Sentence> getSentences(){
        String property = System.getProperty("user.dir");
        String fileName = property + "\\src\\data.txt";
        ArrayList<Sentence> sentences = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            int id = 0;
            while (sc.hasNextLine()) {  //按行读取字符串
                String line = sc.nextLine();
                Sentence sentence = new Sentence(id, line);
                sentences.add(sentence);
                id ++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return sentences;
    }

    /**
     * 根据句子获取单词
     * @param sentences
     * @return
     */
    public static List<List<Word>> getWords(List<Sentence> sentences){
        List<List<Word>> allWord = new ArrayList<>();
        for (Sentence sentence:
             sentences) {
            ArrayList<Word> words = new ArrayList<>();
            int id = 0;
            String text = sentence.getText();
            String[] s = text.split(" ");
            for (int i = 0; i < s.length; i++) {
                if (s[i].endsWith(",")){
                    s[i] = s[i].substring(0, s[i].length() - 1);
                }
                words.add(new Word(id, s[i]));
                id ++;
            }
            allWord.add(words);
        }
        return allWord;
    }

    /**
     * 获取词集
     * List<List<Word>> allWord
     * @return
     */
    public static List<String> getVocabulary(List<List<Word>> allWord){
        ArrayList<String> vocabulary = new ArrayList<>();
        for (List<Word> words:
                allWord) {
            System.out.println("!@");
            for (Word word :
                    words) {
                if (!vocabulary.contains(word.getText())){
                    vocabulary.add(word.getText());
                }
            }
        }
        return vocabulary;
    }

    /**
     * 获取词频  转换为向量
     * @param allWord
     * @param vocabulary
     * @return
     */
    public static double[][] getWordVector(List<List<Word>> allWord, List<String> vocabulary){
        double[][] wordVector = new double[allWord.size()][vocabulary.size()];
        for (int i = 0; i < allWord.size(); i++) {

            //当前句子的所有词
            ArrayList<String> wordList = new ArrayList<>();
            for (int j = 0; j < allWord.get(i).size(); j++) {
                wordList.add(allWord.get(i).get(j).getText());
            }
            System.out.println(wordList.toString());

            String[] vocabulary_arr = new String[vocabulary.size()];
            for (int j = 0; j < vocabulary.size(); j++) {
                vocabulary_arr[j] = vocabulary.get(j);
            }

            for (int j = 0; j < vocabulary_arr.length; j++) {
                int count = 0;
                for (int k = 0; k < allWord.get(i).size(); k++) {
                    if (vocabulary_arr[j].equals(allWord.get(i).get(k).getText())){
                        count ++;
                    }
                }
                wordVector[i][j] = count;
            }
        }
        for (double[] sentenceVector:
            wordVector) {
            System.out.println(Arrays.toString(sentenceVector));
        }
        return wordVector;
    }

    /**
     * 根据词频向量计算相似度
     * @param x
     * @param y
     * @return
     */
    public static double cal(double[] x, double[] y){
        double numerator = 0.0;   //分子
        double a = 0, b = 0;
        for (int i = 0; i < x.length; i++) {
            numerator += x[i] * y[i];
            a += x[i] * x[i];
            b += y[i] * y[i];
        }
        return numerator / (Math.sqrt(a) * Math.sqrt(b));
    }

}
