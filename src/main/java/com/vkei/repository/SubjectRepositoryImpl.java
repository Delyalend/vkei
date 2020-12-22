package com.vkei.repository;

import com.vkei.exception.SubjectExistsInOppositeCategoryException;
import com.vkei.exception.SubjectIsAlreadyExistsInCollectionException;
import com.vkei.model.Subject;
import com.vkei.model.User;
import com.vkei.model.meta.Subject_;
import com.vkei.model.meta.User_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class SubjectRepositoryImpl implements SubjectRepository {

    private EntityManager em;

    @Autowired
    public SubjectRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Long register(Subject subject) {
        em.persist(subject);
        return subject.getId();
    }

    @Override
    @Transactional
    public Optional<Subject> findByTitle(String title) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);
        Root<Subject> root = cq.from(Subject.class);
        cq.select(root).where(cb.equal(root.get(Subject_.TITLE), title));

        TypedQuery<Subject> query = em.createQuery(cq);

        List<Subject> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }

    @Override
    @Transactional
    public List<Subject> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);
        Root<Subject> root = cq.from(Subject.class);
        cq.select(root);
        TypedQuery<Subject> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void addEasySubjectToUser(Long subjectId, Long userId) throws SubjectIsAlreadyExistsInCollectionException {
        Subject subject = em.find(Subject.class, subjectId);

        EntityGraph<User> entityGraph = em.createEntityGraph(User.class);
        entityGraph.addAttributeNodes(User_.SUBJECT_EASY);
        entityGraph.addAttributeNodes(User_.SUBJECT_HARD);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get(User_.ID), userId));
        TypedQuery<User> query = em.createQuery(cq).setHint("javax.persistence.loadgraph", entityGraph);

        User user = query.getResultList().get(0);

        Set<Subject> subjectHard = user.getSubjectHard();
        Set<Subject> subjectEasy = user.getSubjectEasy();

        if (subjectEasy.contains(subject)) {
            throw new SubjectIsAlreadyExistsInCollectionException(subject.getTitle());
        }

        if (subjectHard.contains(subject)) {
            throw new SubjectExistsInOppositeCategoryException(subject.getTitle());
        }
        user.getSubjectEasy().add(subject);
    }

    @Override
    @Transactional
    public void addHardSubjectToUser(Long subjectId, Long userId) throws SubjectIsAlreadyExistsInCollectionException {
        Subject subject = em.find(Subject.class, subjectId);

        EntityGraph<User> entityGraph = em.createEntityGraph(User.class);
        entityGraph.addAttributeNodes(User_.SUBJECT_EASY);
        entityGraph.addAttributeNodes(User_.SUBJECT_HARD);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get(User_.ID), userId));
        TypedQuery<User> query = em.createQuery(cq).setHint("javax.persistence.loadgraph", entityGraph);

        User user = query.getResultList().get(0);

        Set<Subject> subjectHard = user.getSubjectHard();
        Set<Subject> subjectEasy = user.getSubjectEasy();

        if (subjectHard.contains(subject)) {
            throw new SubjectIsAlreadyExistsInCollectionException(subject.getTitle());
        }

        if (subjectEasy.contains(subject)) {
            throw new SubjectExistsInOppositeCategoryException(subject.getTitle());
        }
        user.getSubjectHard().add(subject);

    }
}
