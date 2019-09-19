package ua.yakovenko.model.entity;

import java.util.List;
import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private Long accountMoney;
    private boolean active;
    private Set<Exhibition> exhibitions;
    private List<Exhibition> boughtTickets;
    private Role role;

    //Builder
    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            User.this.id = id;
            return this;
        }

        public Builder username(String username) {
            User.this.username = username;
            return this;
        }

        public Builder password(String password) {
            User.this.password = password;
            return this;
        }

        public Builder accountMoney(Long accountMoney) {
            User.this.accountMoney = accountMoney;
            return this;
        }

        public Builder active(Boolean active) {
            User.this.active = active;
            return this;
        }

        public Builder exhibitions(Set<Exhibition> exhibitions) {
            User.this.exhibitions = exhibitions;
            return this;
        }

        public Builder boughtTickets(List<Exhibition> boughtTickets) {
            User.this.boughtTickets = boughtTickets;
            return this;
        }

        public Builder role(Role role) {
            User.this.role = role;
            return this;
        }

        public User build() {
            return User.this;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Long accountMoney) {
        this.accountMoney = accountMoney;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Exhibition> getExhibitions() {
        return exhibitions;
    }

    public void setExhibitions(Set<Exhibition> exhibitions) {
        this.exhibitions = exhibitions;
    }

    public List<Exhibition> getBoughtTickets() {
        return boughtTickets;
    }

    public void setBoughtTickets(List<Exhibition> boughtTickets) {
        this.boughtTickets = boughtTickets;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
