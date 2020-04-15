package hiber.service;

import hiber.model.Car;
import hiber.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void add(User user);
    List<User> listUsers();
    User getUserByCar(String name, int series);
    Car getCarByNameAndSeries(String name, int series);
}
