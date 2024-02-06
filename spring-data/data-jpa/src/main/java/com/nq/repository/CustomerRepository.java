package com.nq.repository;

import com.nq.po.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 22:29:00
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

}
