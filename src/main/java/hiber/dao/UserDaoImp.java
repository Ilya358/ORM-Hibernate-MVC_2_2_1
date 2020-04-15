package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String name, int series) {
      Car car = getCarByNameAndSeries(name, series);
      Query query = sessionFactory.getCurrentSession().createQuery("from User where car = :car");
      query.setParameter("car", car);
      return (User) query.getSingleResult();
   }

   @Override
   public Car getCarByNameAndSeries(String name, int series) {
      Query query = sessionFactory.getCurrentSession().createQuery("from Car where name = :name" +
              " and series = :series");
      query.setParameter("name", name);
      query.setParameter("series", series);
      return (Car) query.getSingleResult();
   }

}
