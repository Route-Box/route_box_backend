package rootbox.rootboxApp.api.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import rootbox.rootboxApp.global.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserSocialId(String userId);

    void deleteByUserSocialId(String userId);
}
