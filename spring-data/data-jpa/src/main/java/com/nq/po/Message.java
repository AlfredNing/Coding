package com.nq.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alfred.Ning
 * @since 2024年02月07日 07:51:00
 */
@Entity
@Table(name = "jpa_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "message_id")
  private Long id;

  private String info;

  public Message(String info) {
    this.info = info;
  }

  @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Override
  public String toString() {
    return "Message{" +
        "id=" + id +
        ", info='" + info + '\'' +
        ", customer_id=" + customer.getId() +
        '}';
  }
}
