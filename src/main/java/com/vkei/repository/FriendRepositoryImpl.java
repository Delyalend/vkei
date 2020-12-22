package com.vkei.repository;

import com.vkei.dto.FriendDto;
import com.vkei.model.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class FriendRepositoryImpl implements FriendRepository {

    //language=sql
    private final String add_friend_to_user =
            "insert into user_friendship values(?,?)";

    private EntityManager em;

    @Autowired
    public FriendRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addFriendToUser(Long friendId, Long userId) {
        em.createNativeQuery(add_friend_to_user)
                .setParameter(1, userId)
                .setParameter(2, friendId).executeUpdate();
    }

    @Override
    @Transactional
    public List<FriendDto> findFriendsByUserId(Long userId, int firstResult, int maxResult) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> userRoot = cq.from(User.class);
        cq.select(userRoot).where(cb.equal(userRoot.get(User_.ID), userId));

        Set<User> friends = em.createQuery(cq)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .getResultList()
                .get(0)
                .getFriends();

        List<FriendDto> friendDtos = new ArrayList<>();


        friends.forEach(
                friend -> {
                    FriendDto friendDto = new FriendDto(friend.getId(),
                            friend.getMail(),
                            friend.getLogin());
                    friendDtos.add(friendDto);
                }
        );

        return friendDtos;
    }
}
