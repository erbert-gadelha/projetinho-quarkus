package org.ebgr.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="user_tb" , uniqueConstraints=@UniqueConstraint(columnNames={"login"})/**/)
public class UserEntity extends PanacheEntity {

    public String name;
    @Column(name="login", unique=true)
    public String login;
    public String password;

    public UserEntity () { }

    public UserEntity (String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public void setName(String name) {this.name = name;}
    public void setLogin(String login) {this.login = login;}
    public void setPassword(String password) {this.password = password;}

    public String getName() {return name;}
    public String getLogin() {return login;}
    public String getPassword() {return password;}

}
