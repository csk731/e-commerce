package dev.chaitanyaallu.productcatalog.repositories;

import dev.chaitanyaallu.productcatalog.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceRepository extends JpaRepository<Price, UUID> {
}
