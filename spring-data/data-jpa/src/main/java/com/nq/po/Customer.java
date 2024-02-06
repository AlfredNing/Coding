package com.nq.po;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 10:37:00
 */
@Entity
@Table(name = "jpa_customer")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Long id;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "customer_address")
  private String address;

  @Version
  private Integer version;

  @CreatedBy
  private String createdBy;

  @LastModifiedBy
  private String modifiedBy;

  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date creatDate = new Date();


  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date modifiedDate = new Date();
  /**
   * 一对一
   */
  @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "account_id")
  private Account account;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id")
  private List<Message> messages;

  /**
   * joinColumns :本表的外键名称 inverseJoinColumns :关联的表外键名称
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "tb_customer_role",
      joinColumns = {@JoinColumn(name = "cid")},
      inverseJoinColumns = {@JoinColumn(name = "r_id")})
  private List<Role> roles;
}
