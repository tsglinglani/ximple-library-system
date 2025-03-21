package com.ximple.challenge.ximplelibrarysystem.repository;

import com.ximple.challenge.ximplelibrarysystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findBookByAuthorOrTitleOrGenre(String author, String title, String genre);
}
