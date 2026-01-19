package com.gutotech.loteriasapi.consumer;

import com.gutotech.loteriasapi.util.SSLHelper;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Default implementation of HttpConnectionService using SSLHelper
 */
@Service
public class SSLHttpConnectionService implements HttpConnectionService {

    @Override
    public Document get(String url) throws IOException {
        return SSLHelper.getConnection(url).get();
    }
}

