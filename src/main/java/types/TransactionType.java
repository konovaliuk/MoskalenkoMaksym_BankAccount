package main.java.types;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TransactionType {
    Transfer("transfer"),
    Deposit("deposit"),
    Withdraw("withdraw");

    private final String sqlName;

    private static final Map<String, TransactionType> sqlToValue = Stream.of(values()).
            collect(Collectors.toMap(TransactionType::toSqlName, Function.identity()));


    TransactionType(String value) {
        sqlName = value;
    }

    public String toSqlName() {
        return sqlName;
    }

    public static TransactionType fromSqlName(String name) {
        return sqlToValue.get(name);
    }

}
