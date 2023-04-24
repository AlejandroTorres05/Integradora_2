package model;

import java.util.ArrayList;

/**
 * This interface contains the main
 * behavior of a search engine from this
 * project
 * */

public interface SearchEngineInterface <E>{

    /**
     * This method will be the main method of the
     * search engines. Because, this method will be
     * able to filter and return the required elements
     * */
    String searchEngine (ArrayList<E> array, int filter);
}
