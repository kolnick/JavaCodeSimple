package com.caochaojie.solr;

import com.caochaojie.solr.entity.Product;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author caochaojie
 * 2023/2/22
 * @description
 */
public class SolrIndexer {
    private static final SolrClient solrClient = SolrClientFactory.getInstance();

    public static void addProduct(Product product) throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", product.getId());
        doc.addField("name", product.getName());
        doc.addField("category", product.getCategory());
        doc.addField("price", product.getPrice());
        UpdateResponse response = solrClient.add("products", doc);
        solrClient.commit("products");
    }

}
