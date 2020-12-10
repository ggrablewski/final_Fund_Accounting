package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Fund;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface FundRepository extends JpaRepository<Fund, Long> {



}

//    @Query("Select distinct b From Book b Left Join fetch b.authors a join b.publisher p")
//    List<Book> findAll();
//
//    @Query("Select distinct b From Book b Left Join fetch b.authors a join b.publisher p Where b.id = :id")
//    List<Book> findOneByIdWithFetchedAuthors(@Param("id") Long id);
//
//    @Query("SELECT b FROM Book b WHERE b.rating < :min AND b.rating > :max")
//    List<Book> findListByRating(@Param("min") Long min, @Param("max") Long max);
//
//    @Query("SELECT b FROM Book b WHERE b.publisher = :publisher")
//    List<Book> findListByPublisher(@Param("publisher") Publisher publisher);
//
//
//    List<Book> findAllByOrderByTitleDesc();
//
//    @Query("Select b from Book b Where b.title = :title")
//    List<Book> findByTitle(@Param("title") String title);
//
//    List<Book> findAllByRatingOrderByTitleAsc(int rating);
//
//    List<Book> findAllByTitle(String title);
//
//    @Query("Select b from Book b Where b.category = :category")
//    List<Book> findByCategory(@Param("category") Category category);
//
//    List<Book> findAllByCategory(Category category);
//
//    List<Book> findAllByCategoryId(Long id);