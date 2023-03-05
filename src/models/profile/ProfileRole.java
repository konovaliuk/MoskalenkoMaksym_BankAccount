package models.profile;


import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum ProfileRole {
    User ("user"),
    Admin ("admin"),
    SuperAdmin ("superadmin");

    private final String sqlName;

    public static Map<String, ProfileRole> sqlToValue;
    static {
        sqlToValue = Stream.of(values()).collect(Collectors.toMap(ProfileRole::toSqlName, Function.identity()));
    }

    ProfileRole(String value) {
        sqlName = value;
    }

    public String toSqlName() {
        return sqlName;
    }

}
