package com.nq.repository;

import com.nq.po.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 22:29:00
 */
public interface CustomerQBERepository extends PagingAndSortingRepository<Customer, Long>,
    QueryByExampleExecutor<Customer> {

}
