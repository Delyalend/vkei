package com.vkei.repository;

import com.vkei.dto.UserRegistrationDto;
import com.vkei.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Optional<User> findByLogin(String login) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);

        Root<User> root = cr.from(User.class);
        root.fetch("friends", JoinType.LEFT);
        cr.select(root).where(cb.equal(root.get("login"), login));

        TypedQuery<User> query = em.createQuery(cr);
        List<User> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(resultList.get(0));
        }
    }

    @Override
    @Transactional
    public Optional<User> findByRegistrationDto(UserRegistrationDto dto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);

        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.or(
                cb.equal(root.get("login"), dto.getLogin()),
                cb.equal(root.get("mail"), dto.getMail())));


        TypedQuery<User> query = em.createQuery(cr);
        List<User> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }

    @Override
    public Optional<User> findById(Long id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        root.fetch("friends", JoinType.LEFT);
        cr.select(root).where(cb.equal(root.get("id"),id));

        TypedQuery<User> query = em.createQuery(cr);
        List<User> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }

    }

    @Override
    @Transactional
    public Long save(String login, String mail, String password) {
        User user = User.builder()
                .login(login)
                .mail(mail)
                .password(password)
                .build();
        em.persist(user);
        return user.getId();

    }

    @Override
    @Transactional
    public List<User> findAll() {

        EntityGraph<User> userGraph = em.createEntityGraph(User.class);
        userGraph.addAttributeNodes("friends");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);

        Root<User> root = cr.from(User.class);
        cr.select(root);

        TypedQuery<User> query = em.createQuery(cr).setHint("javax.persistence.loadgraph",userGraph);
        return query.getResultList();

    }
}
