package com.example.demo.model;

public class Student {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.id
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.name
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.user_id
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    private Integer userId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.id
     *
     * @return the value of student.id
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.id
     *
     * @param id the value for student.id
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.name
     *
     * @return the value of student.name
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.name
     *
     * @param name the value for student.name
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.user_id
     *
     * @return the value of student.user_id
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.user_id
     *
     * @param userId the value for student.user_id
     *
     * @mbg.generated Fri Aug 28 16:02:56 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}