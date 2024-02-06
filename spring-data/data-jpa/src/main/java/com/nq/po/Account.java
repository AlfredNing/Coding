package com.nq.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author Alfred.Ning
 * @since 2024年02月07日 00:55:00
 */
@Entity
@Table(name = "tb_account")
@Data
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id")
  private Long id;


  @Column(name = "account_name")
  private String accountName;

  @OneToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;


}
