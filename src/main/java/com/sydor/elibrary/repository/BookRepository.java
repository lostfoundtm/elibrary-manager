package com.sydor.elibrary.repository;

import com.sydor.elibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByName(String name);

    @Query("select b.name from Book b group by b.name order by b.name")
    List<String> findDistinctNamesOrderByName();
}
