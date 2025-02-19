package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < source.length(); i++) {
            if (delimiters.contains(source.charAt(i))) {
                result.add(source.substring(0, i));
                source = source.substring(i + 1);
                i = 0;
            }
        }
        return result;
    }
}
