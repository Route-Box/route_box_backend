package rootbox.rootboxApp.api.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import rootbox.rootboxApp.global.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findById(Long id);

    public Optional<User> findByNickname(String nickname);

    public Optional<User> findBySocialLoginUid(String socialId);
}
