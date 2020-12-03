package com.vkei.repository;

import com.vkei.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FriendRepositoryImpl implements FriendRepository {

    private EntityManager em;

    public FriendRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addFriendToUser(Long friendId, Long userId) {
        User userProxy = em.getReference(User.class, userId);
        User friendProxy = em.getReference(User.class, friendId);
        userProxy.getFriends().add(friendProxy);
    }

    //TODO:доделать
    @Override
    public List<User> findFriendsByUserId(Long userId) {
        EntityGraph<User> userGraph = em.createEntityGraph(User.class);
        userGraph.addAttributeNodes("friends");

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("id"), userId));

        TypedQuery<User> query = em.createQuery(cr).setHint("javax.persistence.loadgraph", userGraph);
        return query.getResultList().get(0).getFriends();
    }
}
