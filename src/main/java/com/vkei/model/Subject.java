package com.vkei.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subject", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Subject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjectEasy")
    @JsonIgnore
    private Set<User> easy;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "subjectHard")
    @JsonIgnore
    private Set<User> hard;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id.equals(subject.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
