package com.gutotech.loteriasapi.consumer;

import org.jsoup.nodes.Document;

/**
 * Interface for HTTP connection abstraction
 */
public interface HttpConnectionService {

    /**
     * Makes a GET request and returns the HTML/JSON document
     * @param url URL to make the request
     * @return Document with the response
     * @throws Exception in case of connection error
     */
    Document get(String url) throws Exception;
}

