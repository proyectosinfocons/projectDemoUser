package com.example.projectdemouser.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.Date;



@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {



    @Column(nullable = false, updatable = false,name = "created")
    @CreatedDate
    private Date created;


    @Column(name = "modified")
    @LastModifiedDate
    private Date modified;


    @Column(name = "last_login")
    @CreationTimestamp
    private Date last_login;


}
