package com.caochaojie.solr.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

/**
 * @author caochaojie
 * 2023/2/22
 * @description
 */
@Data
public class Product {
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String category;
    @Field
    private Double price;

    public Product(String id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

}
