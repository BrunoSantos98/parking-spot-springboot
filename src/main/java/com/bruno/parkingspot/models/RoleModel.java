package com.bruno.parkingspot.models;

import com.bruno.parkingspot.enums.RoleName;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Table(name = "TB_ROLE")
public class RoleModel implements GrantedAuthority, Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Byte id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private RoleName roleName;

    public RoleModel(){

    }

    public RoleModel(RoleModel roleModel) {
        this.id = roleModel.id;
        this.roleName = roleModel.roleName;
    }

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }
}
