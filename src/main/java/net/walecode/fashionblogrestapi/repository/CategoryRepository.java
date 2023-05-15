package net.walecode.fashionblogrestapi.repository;


import net.walecode.fashionblogrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}