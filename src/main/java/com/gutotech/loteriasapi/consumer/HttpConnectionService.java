package com.gutotech.loteriasapi.consumer;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Interface for HTTP connection abstraction
 */
public interface HttpConnectionService {

    /**
     * Makes a GET request and returns the HTML/JSON document
     * @param url URL to make the request
     * @return Document with the response
     * @throws IOException in case of connection error
     */
    Document get(String url) throws IOException;
}

