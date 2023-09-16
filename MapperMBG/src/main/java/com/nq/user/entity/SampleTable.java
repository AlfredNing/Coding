package com.nq.user.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sample_table")
public class SampleTable {
    /**
     * Ψһ��ʶ��
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * �û�������
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * �û�������
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * ��������
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * �Ƿ񼤻�
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * �˻����
     */
    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    /**
     * �����ʼ���ַ
     */
    @Column(name = "email_address")
    private String emailAddress;

    /**
     * �绰����
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * ע��ʱ���
     */
    @Column(name = "registration_timestamp")
    private Date registrationTimestamp;

    /**
     * tinyint����
     */
    @Column(name = "test_tinyint")
    private Byte testTinyint;

    /**
     * SMALLINT����
     */
    @Column(name = "test_smallint")
    private Short testSmallint;

    /**
     * new����
     */
    @Column(name = "test_new")
    private Integer testNew;

    /**
     * new2����
     */
    @Column(name = "test_new2")
    private Integer testNew2;

    /**
     * ����ͷ��
     */
    @Column(name = "profile_image")
    private byte[] profileImage;

    /**
     * ��ȡΨһ��ʶ��
     *
     * @return id - Ψһ��ʶ��
     */
    public Integer getId() {
        return id;
    }

    /**
     * ����Ψһ��ʶ��
     *
     * @param id Ψһ��ʶ��
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ�û�������
     *
     * @return first_name - �û�������
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * �����û�������
     *
     * @param firstName �û�������
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * ��ȡ�û�������
     *
     * @return last_name - �û�������
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * �����û�������
     *
     * @param lastName �û�������
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * ��ȡ��������
     *
     * @return birth_date - ��������
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * ���ó�������
     *
     * @param birthDate ��������
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * ��ȡ�Ƿ񼤻�
     *
     * @return is_active - �Ƿ񼤻�
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * �����Ƿ񼤻�
     *
     * @param isActive �Ƿ񼤻�
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * ��ȡ�˻����
     *
     * @return account_balance - �˻����
     */
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * �����˻����
     *
     * @param accountBalance �˻����
     */
    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    /**
     * ��ȡ�����ʼ���ַ
     *
     * @return email_address - �����ʼ���ַ
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * ���õ����ʼ���ַ
     *
     * @param emailAddress �����ʼ���ַ
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * ��ȡ�绰����
     *
     * @return phone_number - �绰����
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * ���õ绰����
     *
     * @param phoneNumber �绰����
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * ��ȡע��ʱ���
     *
     * @return registration_timestamp - ע��ʱ���
     */
    public Date getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    /**
     * ����ע��ʱ���
     *
     * @param registrationTimestamp ע��ʱ���
     */
    public void setRegistrationTimestamp(Date registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    /**
     * ��ȡtinyint����
     *
     * @return test_tinyint - tinyint����
     */
    public Byte getTestTinyint() {
        return testTinyint;
    }

    /**
     * ����tinyint����
     *
     * @param testTinyint tinyint����
     */
    public void setTestTinyint(Byte testTinyint) {
        this.testTinyint = testTinyint;
    }

    /**
     * ��ȡSMALLINT����
     *
     * @return test_smallint - SMALLINT����
     */
    public Short getTestSmallint() {
        return testSmallint;
    }

    /**
     * ����SMALLINT����
     *
     * @param testSmallint SMALLINT����
     */
    public void setTestSmallint(Short testSmallint) {
        this.testSmallint = testSmallint;
    }

    /**
     * ��ȡnew����
     *
     * @return test_new - new����
     */
    public Integer getTestNew() {
        return testNew;
    }

    /**
     * ����new����
     *
     * @param testNew new����
     */
    public void setTestNew(Integer testNew) {
        this.testNew = testNew;
    }

    /**
     * ��ȡnew2����
     *
     * @return test_new2 - new2����
     */
    public Integer getTestNew2() {
        return testNew2;
    }

    /**
     * ����new2����
     *
     * @param testNew2 new2����
     */
    public void setTestNew2(Integer testNew2) {
        this.testNew2 = testNew2;
    }

    /**
     * ��ȡ����ͷ��
     *
     * @return profile_image - ����ͷ��
     */
    public byte[] getProfileImage() {
        return profileImage;
    }

    /**
     * ���ø���ͷ��
     *
     * @param profileImage ����ͷ��
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