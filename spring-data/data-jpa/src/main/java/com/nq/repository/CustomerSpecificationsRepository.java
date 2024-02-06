
package com.nq.repository;

import com.nq.po.Customer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author Alfred.Ning
 * @since 2024年02月06日 22:29:00
 */
public interface CustomerSpecificationsRepository extends CrudRepository<Customer, Long>,
    JpaSpecificationExecutor<Customer> {

}
