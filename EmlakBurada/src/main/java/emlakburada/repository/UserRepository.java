package emlakburada.repository;

import emlakburada.model.Advert;
import emlakburada.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    private static List<User> userList = new ArrayList<>();


    static {
        userList.add(prepareUser(1, "isim1",null));
        userList.add(prepareUser(2, "isim2",null));
        userList.add(prepareUser(3, "isim3",null));
        userList.add(prepareUser(4, "isim4",null));
    }


    public List<User> fetchAllUsers() {
        System.out.println( userList.get(0).getFavoriIlanlar());
        return userList;
    }

    private static User prepareUser(int userNo, String isim, Advert fav) {
        User user = new User();
        user.setUserNo(userNo);
        user.setIsim(isim);
        user.setFavoriIlanlar(Collections.singleton(fav));
        return user;
    }

    public User saveUser(User user) {
        userList.add(user);
        return user;
    }

    public User findUserById(int userNo) {
        return userList.stream().filter(user -> user.getUserNo() == userNo).findAny().orElse(new User());
    }


}
