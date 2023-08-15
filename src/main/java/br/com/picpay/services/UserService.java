package br.com.picpay.services;

import br.com.picpay.domain.user.User;
import br.com.picpay.domain.user.UserType;
import br.com.picpay.dtos.UserDTO;
import br.com.picpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT)
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transações");
        if (sender.getBalance().compareTo(amount) < 0)
            throw new Exception("Saldo insuficiente");
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
