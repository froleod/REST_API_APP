package com.ldf.course.repository;
import com.ldf.course.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {
    Cat findByName(String name);
}
