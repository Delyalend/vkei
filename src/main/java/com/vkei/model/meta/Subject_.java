package com.vkei.model.meta;

import com.vkei.model.Subject;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Set;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subject.class)
public abstract class Subject_ {

    public static volatile SingularAttribute<Subject, Long> id;
    public static volatile SingularAttribute<Subject, String> title;
    public static volatile SingularAttribute<Subject, Set> easy;
    public static volatile SingularAttribute<Subject, Set> hard;

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String EASY = "easy";
    public static final String HARD = "hard";
}
