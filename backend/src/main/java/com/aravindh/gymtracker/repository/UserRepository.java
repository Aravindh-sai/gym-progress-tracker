    package com.aravindh.gymtracker.repository;
    import org.springframework.data.jpa.repository.JpaRepository;
    import com.aravindh.gymtracker.entity.User;

    public interface UserRepository
        extends JpaRepository<User, Long> {
            boolean existsByEmail(String email);
            User findByEmail(String email);
}
