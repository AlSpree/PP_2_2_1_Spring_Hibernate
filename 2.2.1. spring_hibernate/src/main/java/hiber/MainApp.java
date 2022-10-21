package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"), new Car("model_1", 2000));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"), new Car("model_2", 2005));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"), new Car("model_3", 2013));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"), new Car("model_1", 2020));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru"), new Car("model_7", 2000));
      userService.add(new User("User6", "Lastname6", "user6@mail.ru"), new Car("model_1", 2020));

      userService.getUser("model_7", 2000);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car info = "+user.getCar());
         System.out.println();
      }

      context.close();
   }
}
