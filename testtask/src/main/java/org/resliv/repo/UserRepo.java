package org.resliv.repo;

import org.resliv.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {
    User findByChatId(long id);
}
