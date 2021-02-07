package ge.odvali.ebookstore.repositories;

import ge.odvali.ebookstore.entities.UserNotification;
import ge.odvali.ebookstore.entities.UserNotificationID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, UserNotificationID> {
}
