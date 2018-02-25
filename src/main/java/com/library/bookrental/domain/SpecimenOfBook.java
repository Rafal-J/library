package com.library.bookrental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.bookrental.domain.constans.SpecimenStatus;
import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name =  "SpecimenOfBook.findSpecimensOfBookAvailable",
        query = "SELECT * FROM SPECIMENS_OF_BOOK WHERE BOOK_ID = :BOOKID AND (STATUS = 3 OR STATUS = 4)",
        resultClass = SpecimenOfBook.class
)

@Entity
@Table(name = "SPECIMENS_OF_BOOK")
@NoArgsConstructor
@Getter
@Setter
public class SpecimenOfBook {
    @Id
    @NotNull
    @Column(name = "Specimen_Of_Book_Id", unique = true)
    public long id;

    @Column(name = "Status")
    public SpecimenStatus specimenStatus;

    @ManyToOne
    @JoinColumn(name = "Book_id")
    public Book book;

    @JsonIgnore
    @OneToMany(
            targetEntity = Hire.class,
            mappedBy = "specimenOfBook",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Hire> hires = new ArrayList<>();

    public SpecimenOfBook(Long id, SpecimenStatus specimenStatus, Book book) {
        this.id = id;
        this.specimenStatus = specimenStatus;
        this.book = book;
    }
}
