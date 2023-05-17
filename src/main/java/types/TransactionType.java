package types;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TransactionType {
    Transfer("transfer"),
    Deposit("deposit"),
    Withdraw("withdraw");

    private static final Map<String, TransactionType> sqlToValue = Stream.of(values()).
            collect(Collectors.toMap(TransactionType::toSqlName, Function.identity()));
    private final String sqlName;


    TransactionType(String value) {
        sqlName = value;
    }

    public static TransactionType fromSqlName(String name) {
        return sqlToValue.get(name);
    }

    public String toSqlName() {
        return sqlName;
    }

}
