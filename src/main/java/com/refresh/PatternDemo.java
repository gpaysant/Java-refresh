package com.refresh;

public class PatternDemo {

    public static void main (String [] args) {

    }

    public static String processSale (Object obj) {
        return switch (obj) {
            case RecordsDemo.Sale s -> "Vente: %s payé %.2f".formatted(s.buyer().name(), s.amount());
            case RecordsDemo.User u when u.age() > 25 -> "Acheter senior : "  + u.name();
            case null -> "Pas de vente";
            default -> "Inconnu";
        };
    }
}
