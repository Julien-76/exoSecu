package be.technifutur.exoSecu.services;

import be.technifutur.exoSecu.dtos.UserDTO;
import be.technifutur.exoSecu.dtos.VoyageDTO;
import be.technifutur.exoSecu.entities.User;
import be.technifutur.exoSecu.exceptions.PasswordNotValidException;
import be.technifutur.exoSecu.mapper.Mapper;
import be.technifutur.exoSecu.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    private final Mapper mapper;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.repository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }

    public List<UserDTO> getAllUsers() {
        return repository.findAll().stream().map(mapper::toUserDto).collect(Collectors.toList());
    }

    public boolean insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User uInserted = this.repository.save(user);
        return this.repository.existsById(uInserted.getId());
    }

    public void checkPassword(User user, String password) throws PasswordNotValidException {
        if (!this.passwordEncoder.matches(user.getPassword(), password)) {
            throw new PasswordNotValidException();
        }
    }

}
