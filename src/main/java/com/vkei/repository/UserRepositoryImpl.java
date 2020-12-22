package com.vkei.repository;

import com.vkei.dto.UserRegistrationDto;
import com.vkei.model.User;
import com.vkei.model.meta.User_;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
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
        root.fetch(User_.FRIENDS, JoinType.LEFT);
        cr.select(root).where(cb.equal(root.get(User_.LOGIN), login));

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
                cb.equal(root.get(User_.LOGIN), dto.getLogin()),
                cb.equal(root.get(User_.MAIL), dto.getMail())));


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
    public Optional<User> findById(Long id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        root.fetch(User_.FRIENDS, JoinType.LEFT);
        cr.select(root).where(cb.equal(root.get(User_.ID), id));

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
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    @Transactional
    public List<User> findAll() {
        EntityGraph<User> entityGraph = em.createEntityGraph(User.class);

        entityGraph.addAttributeNodes(User_.FRIENDS);
        entityGraph.addAttributeNodes(User_.SUBJECT_HARD);
        entityGraph.addAttributeNodes(User_.SUBJECT_EASY);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);

        TypedQuery<User> query = em.createQuery(cq).setHint("javax.persistence.loadgraph",entityGraph);
        return query.getResultList();

    }
}
