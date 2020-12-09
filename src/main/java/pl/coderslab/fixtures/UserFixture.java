package pl.coderslab.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserGroup;
import pl.coderslab.service.UserService;

import java.util.Arrays;
import java.util.List;

@Component
public class UserFixture {

        private UserService userService;

        private List<User> userList = Arrays.asList(
                new User(null, "000000000", "Test1234", UserGroup.ADMIN),
                new User(null, "000000111", "Test1234", UserGroup.SUPERUSER),
                new User(null, "000000222", "Test1234", UserGroup.TRADE_AGENT),
                new User(null, "000000333", "Test1234", UserGroup.TRADE_CAPTURE),
                new User(null, "000000444", "Test1234", UserGroup.PRICING),
                new User(null, "000000555", "Test1234", UserGroup.CORPORATE_ACTIONS),
                new User(null, "000000666", "Test1234", UserGroup.EXPENSES),
                new User(null, "000000777", "Test1234", UserGroup.FUND_ACCOUNTING),
                new User(null, "000000888", "Test1234", UserGroup.TAX) );

        @Autowired
        public UserFixture(UserService userService) {
            this.userService = userService;
        }

        public void loadIntoDB() {
            for (User user : userList) {
                userService.save(user);
            }
        }
}
