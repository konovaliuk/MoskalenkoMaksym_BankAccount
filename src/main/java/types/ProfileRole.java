package types;


import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum ProfileRole {
    User("user"),
    Admin("admin"),
    SuperAdmin("superadmin");

    private static final Map<String, ProfileRole> sqlToValue = Stream.of(values()).
            collect(Collectors.toMap(ProfileRole::toSqlName, Function.identity()));
    private final String sqlName;


    ProfileRole(String value) {
        sqlName = value;
    }

    public static ProfileRole fromSqlName(String name) {
        return sqlToValue.get(name);
    }

    public String toSqlName() {
        return sqlName;
    }

}
