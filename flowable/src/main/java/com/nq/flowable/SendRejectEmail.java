package com.nq.flowable;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author Alfred.Ning
 * @since 2023年09月25日 08:45:00
 */
public class SendRejectEmail implements JavaDelegate {


  @Override
  public void execute(DelegateExecution execution) {
    System.out.println("拒绝了");
  }
}
