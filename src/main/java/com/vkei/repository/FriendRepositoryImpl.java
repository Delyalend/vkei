package com.vkei.repository;

import com.vkei.model.User;
import com.vkei.model.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    public List<UserInfo> findFriendsByUserId(Long userId) {
//        EntityGraph<User> userGraph = em.createEntityGraph(User.class);
//        userGraph.addAttributeNodes("friends");
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> cr = cb.createQuery(User.class);
//        Root<User> root = cr.from(User.class);
//        cr.select(root).where(cb.equal(root.get("id"), userId));
//
//        TypedQuery<User> query = em.createQuery(cr).setHint("javax.persistence.loadgraph", userGraph);
//        return query.getResultList().get(0).getFriends();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserInfo> cq = cb.createQuery(UserInfo.class);
        Root<User> u = cq.from(User.class);
        cq.select(
                cb.construct(
                        UserInfo.class,
                        u.get("friends")
                )
        ).where(cb.equal(u.get("id"),userId));

        TypedQuery<UserInfo> query = em.createQuery(cq);
        List<UserInfo> resultList = query.getResultList();

        return resultList;
    }
}
