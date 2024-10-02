package com.stevengoh.purchase_sales_stock.modules.users.repositories;

import com.stevengoh.purchase_sales_stock.base.BaseRepository;
import com.stevengoh.purchase_sales_stock.modules.users.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
