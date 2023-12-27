package dev.chaitanyaallu.productcatalog.repositories;

public interface CustomQueries {
    String FIND_ALL_BY_TITLE="select * from product p where p.product_name= :productName";
    //HQL
    String READ_ALL_BY_TITLE="select p from Product where Product.productName= :productName";
}
