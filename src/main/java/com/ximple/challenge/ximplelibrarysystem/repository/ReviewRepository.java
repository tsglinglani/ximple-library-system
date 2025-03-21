package com.ximple.challenge.ximplelibrarysystem.repository;

import com.ximple.challenge.ximplelibrarysystem.model.Book;
import com.ximple.challenge.ximplelibrarysystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByBook(Book book);

}
