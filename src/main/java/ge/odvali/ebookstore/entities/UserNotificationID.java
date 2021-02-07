package ge.odvali.ebookstore.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserNotificationID implements Serializable {
    private Long userId;
    private String message;

    public UserNotificationID(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public UserNotificationID() {
        
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNotificationID that = (UserNotificationID) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, message);
    }
}
