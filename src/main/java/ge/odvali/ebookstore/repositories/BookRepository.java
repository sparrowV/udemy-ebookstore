package ge.odvali.ebookstore.repositories;

import ge.odvali.ebookstore.entities.EBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<EBook, Long> {
}
