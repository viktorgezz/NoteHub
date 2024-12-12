package ru.viktorgezz.model;

public class Account {
    private long id;
    private String login;
    private String password;

    public Account(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {
        private long id;
        private String login;
        private String password;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Account build() {
            if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
                throw new RuntimeException();
            }
            return new Account(this);
        }
    }
}
