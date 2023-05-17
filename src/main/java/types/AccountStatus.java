package types;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AccountStatus {
    Processing("processing"),
    Open("open"),
    Closed("closed");

    private static final Map<String, AccountStatus> sqlToValue = Stream.of(values()).
            collect(Collectors.toMap(AccountStatus::toSqlName, Function.identity()));
    private final String sqlName;


    AccountStatus(String value) {
        sqlName = value;
    }

    public static AccountStatus fromSqlName(String name) {
        return sqlToValue.get(name);
    }

    public String toSqlName() {
        return sqlName;
    }
}
