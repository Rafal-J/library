package com.library.bookrental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(name = "Book.findBookByTitleAuthorAndYear",
        query = "SELECT * FROM BOOKS WHERE title = :TITLE AND author = :AUTHOR and year_of_publishing = :YEAROFPUBLISHING",
        resultClass = Book.class

)

@Table(name = "BOOKS")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "Book_Id", unique = true)
    public long id;

    @Column(name = "Title")
    public String title;

    @Column(name = "Author")
    public String author;

    @Column(name = "Year_Of_Publishing")
    public int yearOfPublishing;

    @JsonIgnore
    @OneToMany(
            targetEntity = SpecimenOfBook.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )

    public List<SpecimenOfBook> specimenOfBooks = new ArrayList<>();

    public Book(String title, String author, int yearOfPublishing) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
    }
}
