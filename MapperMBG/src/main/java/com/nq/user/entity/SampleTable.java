package com.nq.user.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sample_table")
public class SampleTable {
    /**
     * 唯一标识符
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户的名字
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * 用户的姓氏
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * 出生日期
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * 是否激活
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * 账户余额
     */
    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    /**
     * 电子邮件地址
     */
    @Column(name = "email_address")
    private String emailAddress;

    /**
     * 电话号码
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 注册时间戳
     */
    @Column(name = "registration_timestamp")
    private Date registrationTimestamp;

    /**
     * tinyint测试
     */
    @Column(name = "test_tinyint")
    private Byte testTinyint;

    /**
     * SMALLINT测试
     */
    @Column(name = "test_smallint")
    private Short testSmallint;

    /**
     * new测试
     */
    @Column(name = "test_new")
    private Integer testNew;

    /**
     * new2测试
     */
    @Column(name = "test_new2")
    private Integer testNew2;

    /**
     * 个人头像
     */
    @Column(name = "profile_image")
    private byte[] profileImage;

    /**
     * 获取唯一标识符
     *
     * @return id - 唯一标识符
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置唯一标识符
     *
     * @param id 唯一标识符
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户的名字
     *
     * @return first_name - 用户的名字
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 设置用户的名字
     *
     * @param firstName 用户的名字
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 获取用户的姓氏
     *
     * @return last_name - 用户的姓氏
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置用户的姓氏
     *
     * @param lastName 用户的姓氏
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 获取出生日期
     *
     * @return birth_date - 出生日期
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 设置出生日期
     *
     * @param birthDate 出生日期
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 获取是否激活
     *
     * @return is_active - 是否激活
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否激活
     *
     * @param isActive 是否激活
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取账户余额
     *
     * @return account_balance - 账户余额
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * 设置账户余额
     *
     * @param accountBalance 账户余额
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * 获取电子邮件地址
     *
     * @return email_address - 电子邮件地址
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * 设置电子邮件地址
     *
     * @param emailAddress 电子邮件地址
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * 获取电话号码
     *
     * @return phone_number - 电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置电话号码
     *
     * @param phoneNumber 电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取注册时间戳
     *
     * @return registration_timestamp - 注册时间戳
     */
    public Date getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    /**
     * 设置注册时间戳
     *
     * @param registrationTimestamp 注册时间戳
     */
    public void setRegistrationTimestamp(Date registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    /**
     * 获取tinyint测试
     *
     * @return test_tinyint - tinyint测试
     */
    public Byte getTestTinyint() {
        return testTinyint;
    }

    /**
     * 设置tinyint测试
     *
     * @param testTinyint tinyint测试
     */
    public void setTestTinyint(Byte testTinyint) {
        this.testTinyint = testTinyint;
    }

    /**
     * 获取SMALLINT测试
     *
     * @return test_smallint - SMALLINT测试
     */
    public Short getTestSmallint() {
        return testSmallint;
    }

    /**
     * 设置SMALLINT测试
     *
     * @param testSmallint SMALLINT测试
     */
    public void setTestSmallint(Short testSmallint) {
        this.testSmallint = testSmallint;
    }

    /**
     * 获取new测试
     *
     * @return test_new - new测试
     */
    public Integer getTestNew() {
        return testNew;
    }

    /**
     * 设置new测试
     *
     * @param testNew new测试
     */
    public void setTestNew(Integer testNew) {
        this.testNew = testNew;
    }

    /**
     * 获取new2测试
     *
     * @return test_new2 - new2测试
     */
    public Integer getTestNew2() {
        return testNew2;
    }

    /**
     * 设置new2测试
     *
     * @param testNew2 new2测试
     */
    public void setTestNew2(Integer testNew2) {
        this.testNew2 = testNew2;
    }

    /**
     * 获取个人头像
     *
     * @return profile_image - 个人头像
     */
    public byte[] getProfileImage() {
        return profileImage;
    }

    /**
     * 设置个人头像
     *
     * @param profileImage 个人头像
     */
    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "SampleTable{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", birthDate=" + birthDate +
            ", isActive=" + isActive +
            ", accountBalance=" + accountBalance +
            ", emailAddress='" + emailAddress + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", registrationTimestamp=" + registrationTimestamp +
            ", testTinyint=" + testTinyint +
            ", testSmallint=" + testSmallint +
            ", testNew=" + testNew +
            ", testNew2=" + testNew2 +
            ", profileImage=" + Arrays.toString(profileImage) +
            '}';
    }
}