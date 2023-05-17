package types;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AccountType {
    Credit("credit"),
    Debit("debit"),
    Default("default");

    private static final Map<String, AccountType> sqlToValue = Stream.of(values()).
            collect(Collectors.toMap(AccountType::toSqlName, Function.identity()));
    private final String sqlName;


    AccountType(String value) {
        sqlName = value;
    }

    public static AccountType fromSqlName(String name) {
        return sqlToValue.get(name);
    }

    public String toSqlName() {
        return sqlName;
    }

}