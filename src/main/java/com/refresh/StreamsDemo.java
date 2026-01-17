package com.refresh;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void main (String[] args) {
        RecordsDemo.User peter = new RecordsDemo.User("Peter", 32, "peter@gmail.com");
        RecordsDemo.User raze = new RecordsDemo.User("raze", 20, "raze@yahoo.fr");
        RecordsDemo.User phoenix = new RecordsDemo.User("phoenix", 30, "phoenix@gmail.com");
        RecordsDemo.User omen = new RecordsDemo.User("Omen", 45, "omen@yahoo.fr");
        RecordsDemo.User skye = new RecordsDemo.User("skye", 42, "skye@gmail.com");

        List<RecordsDemo.User> users = List.of(peter, raze, phoenix, omen, skye);
        List<String> seniors = users.stream().filter(user -> user.age() > 25).map(user -> user.name() + "(" + user.age() + ")").sorted().toList();
        seniors.forEach(System.out::println);

        OptionalDouble averageOpt = users.stream().filter(user -> user.age() > 25).mapToInt(RecordsDemo.User::age).average();
        System.out.println(averageOpt.isPresent() ? String.format("Moyenne: %.1f",averageOpt.getAsDouble()) : "Aucun senior");

        TreeSet<RecordsDemo.User> usersAge = users.stream().sorted(Comparator.comparing(RecordsDemo.User::age)).collect(Collectors.toCollection(TreeSet::new));
        usersAge.forEach(System.out::println);

        Map<String, List<RecordsDemo.User>> userByEmail = users.stream().collect(Collectors.groupingBy(user -> user.email().split("@")[1]));
        System.out.println(userByEmail);
    }
}
