package com.vkei.repository;

import com.vkei.dto.FriendDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FriendRepositoryImpl implements FriendRepository {


    //language=sql
    private final String get_friend_info =
            "select u.id as id, u.login as login, u.mail as mail " +
                    "from public.user u " +
                    "left join user_friendship uf on u.id = uf.second_user_id " +
                    "where uf.first_user_id = ?";

    //language=sql
    private final String add_friend_to_user =
            "insert into user_friendship values(?,?)";

    private EntityManager em;

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
    public List<FriendDto> findFriendsByUserId(Long userId) {

        Query query = em.createNativeQuery(get_friend_info, Tuple.class);
        List<Tuple> friendsInfo = query
                .setParameter(1, userId)
                .getResultList();

        List<FriendDto> friendDtos = new ArrayList<>();
        friendsInfo.forEach(
                friendInfo -> {
                    FriendDto friendDto = new FriendDto(
                            ((Integer) friendInfo.get("id")).longValue(),
                            (String) friendInfo.get("mail"),
                            (String) friendInfo.get("login"));
                    friendDtos.add(friendDto);
                }
        );
        return friendDtos;
    }
}
