package org.example.systemegestionmedicale.Repository;

import org.example.systemegestionmedicale.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
