package ge.odvali.ebookstore.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserNotification {
    @EmbeddedId
    private UserNotificationID userNotificationID;

    public UserNotificationID getUserNotificationID() {
        return userNotificationID;
    }

    public void setUserNotificationID(UserNotificationID userNotificationID) {
        this.userNotificationID = userNotificationID;
    }

    public UserNotification() {
    }
}
