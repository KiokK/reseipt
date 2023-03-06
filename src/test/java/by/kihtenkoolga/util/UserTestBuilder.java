package by.kihtenkoolga.util;

import by.kihtenkoolga.model.User;

public class UserTestBuilder {

    public static User USERid1 = user1withNullId().id(1L).build();
    public static User USERid2 = new User(2L, "Viva", "Vovo", "+375330007623", "asddiu@gmail.com");
    public static User USERid3 = new User(3L, "Chery", "Apple", "+375330900023", "diasdu@gmail.com");
    public static User USERid4 = user4withNullId().id(4L).build();

    public static User.UserBuilder user1withNullId() {
        return User.builder()
                .name("Bob")
                .surname("Biba")
                .phoneNumber("+375330987623")
                .email("dsadiu@gmail.com");
    }
    public static User.UserBuilder user4withNullId() {
        return User.builder()
                .name("Chery")
                .surname("Pen")
                .phoneNumber("+375330900023")
                .email("CherryPen@gmail.com");
    }

}
