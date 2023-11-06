package com.caochaojie.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author caochaojie
 * 2023/2/22
 * @description
 */
public class SolrMain {
    public static void main(String[] args) {
        try {
            update();
        } catch (SolrServerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void add() throws IOException {

        // 创建 Solr 客户端
        String urlString = "http://192.168.243.128:6016/solr/appLog";
        SolrClient solr = new HttpSolrClient.Builder(urlString).build();

        // 创建一个实体类，该类映射到 Solr 文档的字段
        class Book {
            @Field
            public String id;
            @Field
            public String title;
            @Field
            public String description;
        }

        // 创建一个 Solr 文档
        Book book = new Book();
        book.id = "book1";
        book.title = "Solr in Action";
        book.description = "This is a book about Solr";

        // 将文档添加到 Solr 索引
        try {
            UpdateResponse response = solr.addBean(book);
            System.out.println(response);

            // 提交更新
            solr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 关闭 Solr 客户端
        solr.close();
    }

    public static void update() throws SolrServerException, IOException {
        String basePath = "http://192.168.243.128:6016/solr/appLog";
        HttpSolrClient client = new HttpSolrClient.Builder(basePath).build();
        // 新增或更新。solrJ定义的标准中，新增的文档类型都是SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        // SolrInputDocument 就是用于描述一个文档的对象。通过field+value描述一个文档
        document.addField("id", "001");
        document.addField("zh_all", "测试SolrJ新增数据");
        UpdateResponse response = client.add(document);
        System.out.println("status:" + response.getStatus());
        System.out.println("QTime:" + response.getQTime());
        // Solr服务中，数据写操作，也是有事务。Web管理平台，默认一次操作一提交。SOLRJ默认回滚
        client.commit();
        client.close();
    }

    public static void delete() throws SolrServerException, IOException {
        String basePath = "http://192.168.243.128:6016/solr/appLog";
        HttpSolrClient client = new HttpSolrClient.Builder(basePath).build();
        // 新增或更新。solrJ定义的标准中，新增的文档类型都是SolrInputDocument
        SolrInputDocument document = new SolrInputDocument();
        client.deleteById("001");
        client.commit();
        client.close();
    }

    @Test
    public void query() throws SolrServerException, IOException {
        String basePath = "http://192.168.243.128:6016/solr/appLog";
        HttpSolrClient client = new HttpSolrClient.Builder(basePath).build();
        // 新增或更新。solrJ定义的标准中，新增的文档类型都是SolrInputDocument
        SolrQuery query = new SolrQuery("*:*");
        query.setQuery("zh_all:测试");
        query.setSort("id", SolrQuery.ORDER.desc);
        //分页
        query.setStart(0);
        query.setRows(10);
        // 开启高亮
        query.setHighlight(true);

        QueryResponse queryRes = client.query(query);
        NamedList<Object> header = queryRes.getHeader();
        Iterator<Map.Entry<String, Object>> iterator = header.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next);
        }
        // 获取响应体，包括numFound和查询结果集合，start返回的结果是SolrDocumentList类型的对象
        SolrDocumentList results = queryRes.getResults();
        for (SolrDocument result : results) {
            System.out.printf("id:" + result.getFieldValue("id"));
            System.out.println("zh_all" + result.getFieldValue("zh_all"));
        }
        client.commit();
        client.close();
    }


}
