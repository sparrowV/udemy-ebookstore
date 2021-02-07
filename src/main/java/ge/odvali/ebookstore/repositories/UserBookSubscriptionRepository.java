package ge.odvali.ebookstore.repositories;

import ge.odvali.ebookstore.entities.UserBookId;
import ge.odvali.ebookstore.entities.UserBookSubscription;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookSubscriptionRepository extends JpaRepository<UserBookSubscription, UserBookId> {

    @Query(value = "SELECT *" +
            " FROM USER_BOOK_SUBSCRIPTION" +
            " WHERE BOOK_TITLE=:bookTitle ", nativeQuery = true)
    List<UserBookSubscription> findByBookTitle(@Parameter(name = "bookTitle") String bookTitle);
}
