package ge.odvali.ebookstore.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserBookSubscription {
    @EmbeddedId
    private UserBookId userBookId;

    public UserBookId getUserBookId() {
        return userBookId;
    }

    public void setUserBookId(UserBookId userBookId) {
        this.userBookId = userBookId;
    }
}
