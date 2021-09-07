package com.generation.org.apidemo;

import com.generation.org.apidemo.model.User;
import com.generation.org.apidemo.repository.UserRepository;
import com.generation.org.apidemo.service.UserService;
import com.generation.org.apidemo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class UserServiceImplTest
{

    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void getUserReturnsFindByIdFromUserRepository()
    {
        /// Arrange
        String name = "Santiago";
        String id = "1";
        User user = new User( name, id );
        when( userRepository.findById( id ) ).thenReturn( Optional.of( user ) );

        /// Act
        userService = new UserServiceImpl( userRepository );
        User foundUser = userService.getUser( id );

        // Asserts
        assertThat( foundUser.getId() ).isEqualTo( id );
        assertThat( foundUser.getName() ).isEqualTo( name );
    }

    @Test
    public void getUserReturnsNullWhenUserNotFound()
    {
        /// Arrange
        String id = "1";
        when( userRepository.findById( id ) ).thenReturn( Optional.empty() );

        /// Act
        userService = new UserServiceImpl( userRepository );
        User foundUser = userService.getUser( id );

        // Asserts
        assertThat( foundUser ).isNull();
    }
}
