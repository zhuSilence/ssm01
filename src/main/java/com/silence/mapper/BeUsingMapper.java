package com.silence.mapper;

import com.silence.po.BeUsing;

public interface BeUsingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table be_using
     *
     * @mbggenerated Wed Nov 18 20:22:23 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table be_using
     *
     * @mbggenerated Wed Nov 18 20:22:23 CST 2015
     */
    int insert(BeUsing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table be_using
     *
     * @mbggenerated Wed Nov 18 20:22:23 CST 2015
     */
    int insertSelective(BeUsing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table be_using
     *
     * @mbggenerated Wed Nov 18 20:22:23 CST 2015
     */
    BeUsing selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table be_using
     *
     * @mbggenerated Wed Nov 18 20:22:23 CST 2015
     */
    int updateByPrimaryKeySelective(BeUsing record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table be_using
     *
     * @mbggenerated Wed Nov 18 20:22:23 CST 2015
     */
    int updateByPrimaryKey(BeUsing record);
}