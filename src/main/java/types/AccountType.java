package main.java.types;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AccountType {
    Credit("credit"),
    Debit("debit"),
    Default("default");

    private final String sqlName;

    private static final Map<String, AccountType> sqlToValue = Stream.of(values()).
            collect(Collectors.toMap(AccountType::toSqlName, Function.identity()));


    AccountType(String value) {
        sqlName = value;
    }

    public String toSqlName() {
        return sqlName;
    }

    public static AccountType fromSqlName(String name) {
        return sqlToValue.get(name);
    }

}