package com.wuxianggujun.toolbox.entiy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Word {
    public List<String> words = new ArrayList<>();

    public Word() {
        String file = "a.txt";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                if (!temp.equals(""))
                    words.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> search(String keyWord, Collection<String> stringCollection, BiFunction<String, String, Boolean> matcher) {
        return (stringCollection == null || matcher == null) ? Collections.emptyList() : stringCollection.stream().filter(t -> matcher.apply(t, keyWord)).collect(Collectors.toList());
    }
}