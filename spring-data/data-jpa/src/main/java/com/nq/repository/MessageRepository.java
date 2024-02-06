package com.nq.repository;

import com.nq.po.Customer;
import com.nq.po.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
   List<Message> findByCustomer(Customer customer);
}