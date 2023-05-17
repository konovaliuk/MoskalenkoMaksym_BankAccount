package types;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AccountStatus {
    Processing("processing"),
    Open("open"),
    Closed("closed");

    private final String sqlName;

    private static final Map<String, AccountStatus> sqlToValue = Stream.of(values()).
            collect(Collectors.toMap(AccountStatus::toSqlName, Function.identity()));


    AccountStatus(String value) {
        sqlName = value;
    }

    public String toSqlName() {
        return sqlName;
    }

    public static AccountStatus fromSqlName(String name) {
        return sqlToValue.get(name);
    }
}
