package com.vkei.model.meta;

import com.vkei.model.Subject;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.List;
import java.util.Set;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subject.class)
public class User_ {

    public static volatile SingularAttribute<Subject, Long> id;
    public static volatile SingularAttribute<Subject, String> mail;
    public static volatile SingularAttribute<Subject, String> password;
    public static volatile SingularAttribute<Subject, String> login;
    public static volatile SingularAttribute<Subject, String> location;
    public static volatile SingularAttribute<Subject, List> friends;
    public static volatile SingularAttribute<Subject, Set> subjectEasy;
    public static volatile SingularAttribute<Subject, Set> subjectHard;

    public static final String ID = "id";
    public static final String MAIL = "mail";
    public static final String PASSWORD = "password";
    public static final String LOGIN = "login";
    public static final String LOCATION = "location";
    public static final String FRIENDS = "friends";
    public static final String SUBJECT_EASY = "subjectEasy";
    public static final String SUBJECT_HARD = "subjectHard";
}
