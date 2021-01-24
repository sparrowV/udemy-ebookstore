package ge.odvali.ebookstore.entities;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="EBOOK")
@SequenceGenerator(sequenceName = "ebook_seq",name = "ebookSeq")
public class EBook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ebookSeq")
    @Hidden
    private Long id;

    private String title;
    private String authors; //comma separated list of authors
    @ManyToMany(mappedBy = "eBooks")
    @Hidden
    private List<User> owners;  //the list of the Users who own this book

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }
}
