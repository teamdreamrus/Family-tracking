package com.example.familyTracking.repositories;

import com.example.familyTracking.model.Friendship;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendshipRepository extends CrudRepository<Friendship,Integer> {

    boolean findAccessById(Integer id);

@Query(value = "SELECT id FROM friendship WHERE ((id1=?1 and id2=?2) or (id2=?2 and id1=?1));",
    nativeQuery = true)
    List<Integer> getIDbyIdId(Integer id1, Integer id2);


@Query(value = "SELECT id FROM friendship WHERE (id1=?1  or id2=?1 );",
        nativeQuery = true)
        List <Integer>findByIds(Integer id1);
/*@Modifying
@Query(value = "UPDATE friendship SET accepted = true WHERE id=?1",
            nativeQuery = true)
    void UpdateAccept(Integer id);
*/


};