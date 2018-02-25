package com.library.bookrental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "READERS")
@NoArgsConstructor
@Getter
@Setter
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "Reader_ID")
    public long id;

    @Column(name = "First_Name")
    public String firstName;

    @Column(name = "Last_Name")
    public String lastName;

    @Column(name = "Created")
    public LocalDate created;

    @JsonIgnore
    @OneToMany (
            targetEntity = Hire.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Hire> hires = new ArrayList<>();

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = LocalDate.now();
    }
}