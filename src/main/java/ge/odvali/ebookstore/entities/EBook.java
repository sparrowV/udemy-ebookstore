package ge.odvali.ebookstore.entities;


import io.swagger.v3.oas.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="EBOOK")
@SequenceGenerator(sequenceName = "ebook_seq",name = "ebookSeq")
public class EBook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ebookSeq")

    private Long id;

    private String title;
    private String authors; //comma separated list of authors
    @ManyToMany(mappedBy = "eBooks")
    private List<User> owners;  //the list of the Users who own this book
}
