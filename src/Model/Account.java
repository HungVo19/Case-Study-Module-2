package Model;

import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;
import resources.AsciiArtTable;

import java.io.Serializable;

public class Account implements Serializable {
    public static Long INDEX = Long.valueOf(0);
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String tel;
    private Role role;

    public Account(String username, String password) {
        this.id = Long.valueOf(++INDEX);
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, Role role) {
        this.id = Long.valueOf(++INDEX);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(String username, String password, String name, String email, String tel, Role role) {
        this.id = Long.valueOf(++INDEX);
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.role = role;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void display() {
        String[] headers = { "Username", "Password", "Name", "Email", "Tel"};
        Object[][] data = {
                {this.username, this.password, this.name, this.email, this.tel},
        };
        System.out.println(FlipTableConverters.fromObjects(headers, data));
        }


}
