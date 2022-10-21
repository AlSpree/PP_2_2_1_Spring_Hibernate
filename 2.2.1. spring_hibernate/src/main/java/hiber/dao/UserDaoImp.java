package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getUser(String model, int carSeries) {
      List<User> userList;
      String sqlCommand = "select * from users u \n" +
              "where u.car_id in (select id from cars c \n" +
              "where c.model = :modelPram and c.series = :carSeriesParam)";

      userList = sessionFactory.getCurrentSession().createSQLQuery(sqlCommand)
              .setParameter("modelPram", model)
              .setParameter("carSeriesParam", carSeries)
              .addEntity(User.class)
              .getResultList();

      userList.forEach(System.out::println);
      return userList;
   }

}
