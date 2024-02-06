
package com.nq.repository;

import com.nq.po.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 22:29:00
 */
public interface CustomerQueryDSLRepository extends CrudRepository<Customer, Long>
//    QuerydslPredicateExecutor<Customer>

{

}
