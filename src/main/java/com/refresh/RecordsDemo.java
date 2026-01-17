package com.refresh;

import static java.util.Objects.requireNonNull;

public class RecordsDemo {

    public record User(String name, int age, String email) implements Comparable<User> {

        @Override
        public int compareTo(User o) {
            return this.age - o.age;
        }

    }  // Getters auto: name(), age()

    public record Sale(User buyer, double amount) {
        public Sale {
            requireNonNull(buyer);
            if(amount < 0) throw new IllegalArgumentException();
        }
    }  // Compact constructor implicite


    public static void main (String[] args) {
        // Usage
        User user = new User("Alice", 30, "alice@email.com");
        User youngUser = new User("Peter", 20, "peter@email.com");
        Sale sale = new Sale(user, 99.99);

        System.out.println(sale);
        assert user.name().equals("Alice");
        assert sale.toString().equals("Sale[buyer=User[name=Alice, age=30, email=alice@email.com], amount=99.99]");
        assert user.equals(new User("Alice", 30, "alice@email.com"));

        System.out.println(PatternDemo.processSale(sale));
        System.out.println(PatternDemo.processSale(user));
        System.out.println(PatternDemo.processSale(youngUser));
        System.out.println(PatternDemo.processSale(null));
        System.out.println(PatternDemo.processSale("coco lapin"));

    }
}
