package com.refresh;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void main (String[] args) {
        User peter = new User("Peter", 32, "peter@gmail.com");
        User raze = new User("raze", 20, "raze@yahoo.fr");
        User phoenix = new User("phoenix", 30, "phoenix@gmail.com");
        User omen = new User("Omen", 45, "omen@yahoo.fr");
        User skye = new User("skye", 42, "skye@gmail.com");

        List<User> users = List.of(peter, raze, phoenix, omen, skye);
        List<String> seniors = users.stream().filter(user -> user.age() > 25).map(user -> user.name() + "(" + user.age() + ")").sorted().toList();
        seniors.forEach(System.out::println);

        OptionalDouble averageOpt = users.stream().filter(user -> user.age() > 25).mapToInt(User::age).average();
        System.out.println(averageOpt.isPresent() ? String.format("Moyenne: %.1f",averageOpt.getAsDouble()) : "Aucun senior");

        TreeSet<User> usersAge = users.stream().sorted(Comparator.comparing(User::age)).collect(Collectors.toCollection(TreeSet::new));
        usersAge.forEach(System.out::println);

        Map<String, List<User>> userByEmail = users.stream().collect(Collectors.groupingBy(user -> user.email().split("@")[1]));
        System.out.println(userByEmail);
    }
}
