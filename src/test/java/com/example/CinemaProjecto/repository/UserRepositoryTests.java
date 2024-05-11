package com.example.CinemaProjecto.repository;

import com.example.CinemaProjecto.models.User;
import com.example.CinemaProjecto.repositories.UserRepository;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("h2")
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void init() {
        user = User.builder()
                .email("user1@email.com")
                .firstName("Emma")
                .lastName("Stone")
                .build();
        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    @DisplayName("Test pentru găsirea utilizatorului după ID")
    public void whenFindById_thenReturnUser() {
        User found = userRepository.findById(user.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Test pentru ștergerea utilizatorului după ID")
    public void whenDeleteById_thenUserShouldBeDeleted() {
        userRepository.deleteById(user.getId());
        boolean exists = userRepository.existsById(user.getId());
        assertThat(exists).isFalse();
    }

    @Test
    public void whenFindByEmail_thenReturnUser() {
        Optional<User> found = userRepository.findByEmail("user1@email.com");
        assertThat(found).isNotNull();
        assertThat(found.isPresent()).isTrue();  // Check that the user was actually found
        assertThat(found.get().getEmail()).isEqualTo("user1@email.com");
    }

}
