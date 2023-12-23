package com.chaitanyaallu.productcatalog.repositories;

import com.chaitanyaallu.productcatalog.models.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

}
