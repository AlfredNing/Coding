package com.nq;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author Alfred.Ning
 * @since 2024年01月14日 11:23:00
 */
public class SendRejectionMail implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) {
    System.out.println("请假拒绝了，发送邮件...");
  }
}
