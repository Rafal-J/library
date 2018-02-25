package com.library.bookrental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@NamedNativeQuery(name = "Hire.wasHired",
        query = "SELECT * from hires where reader_id = :READERID and specimen_of_book_id = :SPECIMENOFBOOKID and return_date is null",
        resultClass = Hire.class
)

@Entity
@Table(name = "HIRES")
@NoArgsConstructor
@Getter
@Setter
public class Hire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "Hire_Id", unique = true)
    public long id;

    @Column(name = "Hire_Date")
    public LocalDate hireDate;

    @Column(name = "Return_Date")
    public LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "Reader_Id")
    public Reader reader;

    @ManyToOne
    @JoinColumn(name = "specimenOfBook_id")
    SpecimenOfBook specimenOfBook;

    public Hire(Reader reader, SpecimenOfBook specimenOfBook) {
        this.reader = reader;
        this.hireDate = LocalDate.now();
        this.specimenOfBook = specimenOfBook;
    }
}