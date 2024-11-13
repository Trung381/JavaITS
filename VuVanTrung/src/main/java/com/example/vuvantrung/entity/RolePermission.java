package com.example.vuvantrung.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "role_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "permission_id", nullable = false)
    private Integer permissionId;

    @Column(name = "can_write")
    private Boolean canWrite;

    @Column(name = "can_view")
    private Boolean canView;

    @Column(name = "can_approve")
    private Boolean canApprove;

    @Column(name = "can_delete")
    private Boolean canDelete;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructors
    public RolePermission() {}

    public RolePermission(Integer roleId, Integer permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

}

//package com.example.vuvantrung.entity;
//
//import jakarta.persistence.*;
//        import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.BatchSize;
//
//import java.util.Date;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "role_permission")
//public class RolePermission {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id")
//    @BatchSize(size = 10)  // Load các Role theo nhóm 10
//    private Role role;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "permission_id")
//    @BatchSize(size = 10)  // Load các Permission theo nhóm 10
//    private Permission permission;
//
//    private Boolean isWrite;
//    private Boolean isView;
//    private Boolean isApproval;
//    private Boolean isDecision;
//    private Boolean deleted;
//
//    @Column(name = "created_at", nullable = false, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//
//    @Column(name = "updated_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date updatedAt;
//}
