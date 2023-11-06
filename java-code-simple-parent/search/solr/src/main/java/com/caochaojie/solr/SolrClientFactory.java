package com.caochaojie.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * @author caochaojie
 * 2023/2/22
 * @description
 */
public class SolrClientFactory {

    private static final String SOLR_URL = "http://localhost:8983/solr";

    private static SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).build();

    public static SolrClient getInstance() {
        return solrClient;
    }
}
