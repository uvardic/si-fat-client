package fat.client.resource;

public class DatabaseInfo {

    private final String server;

    private final String name;

    private final String username;

    private final String password;

    private DatabaseInfo(Builder builder) {
        this.server = builder.server;
        this.name = builder.name;
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getServer() {
        return server;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {

        private String server;

        private String name;

        private String username;

        private String password;

        public Builder server(String server) {
            this.server = server;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public DatabaseInfo create() {
            return new DatabaseInfo(this);
        }

    }

}
