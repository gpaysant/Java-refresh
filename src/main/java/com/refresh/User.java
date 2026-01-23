package com.refresh;

public record User(String name, int age, String email) implements Comparable<User> {

    @Override
    public int compareTo(User o) {
        return this.age - o.age;
    }

}