//package com.ning.entity;
//
//import com.baomidou.mybatisplus.annotations.TableId;
//import com.baomidou.mybatisplus.annotations.TableName;
//import com.baomidou.mybatisplus.enums.IdType;
//
///**
// * @author Alfred.Ning
// * @since 2023年09月16日 22:12:00
// */
//public class Employee {
//
//  @TableId(type = IdType.AUTO)
//  private Integer id;
//  private String lastName;
//  private String email;
//  private Integer gender;
//  private Integer age;
//
//  public Integer getId() {
//    return id;
//  }
//
//  public void setId(Integer id) {
//    this.id = id;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
//
//  public void setLastName(String lastName) {
//    this.lastName = lastName;
//  }
//
//  public String getEmail() {
//    return email;
//  }
//
//  public void setEmail(String email) {
//    this.email = email;
//  }
//
//  public Integer getGender() {
//    return gender;
//  }
//
//  public void setGender(Integer gender) {
//    this.gender = gender;
//  }
//
//  public Integer getAge() {
//    return age;
//  }
//
//  public void setAge(Integer age) {
//    this.age = age;
//  }
//
//  @Override
//  public String toString() {
//    return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email
//        + ", gender=" + gender + ", age="
//        + age + "]";
//  }
//
//}
