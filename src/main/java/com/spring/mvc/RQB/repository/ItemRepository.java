package com.spring.mvc.RQB.repository;

import com.spring.mvc.RQB.entities.Item;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "itemRepository")
public interface ItemRepository extends JpaRepository<Item, Integer>{
    @Transactional
    @Modifying
    @Query(value = "UPDATE Item SET name=?2, symbol=?3, classify_id=?4 WHERE id=?1", nativeQuery = true)
    public void update(@Param("id") Integer id, @Param("name") String name, @Param("symbol") String symbol, @Param("classify_id") Integer classify_id);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE Item SET prePrice=?2, price=?3, transactionDate=?4, quantity=?5 WHERE id=?1", nativeQuery = true)
    public void updatePrice(@Param("id") Integer id, @Param("prePrice") BigDecimal prePrice, @Param("price") BigDecimal price, @Param("transactionDate") Date transactionDate, @Param("quantity") Long quantity);

}