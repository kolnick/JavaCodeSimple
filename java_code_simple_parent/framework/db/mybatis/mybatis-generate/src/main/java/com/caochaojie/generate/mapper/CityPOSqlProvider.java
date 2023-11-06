package com.caochaojie.generate.mapper;

import com.caochaojie.generate.entity.CityPO;
import org.apache.ibatis.jdbc.SQL;

public class CityPOSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public String insertSelective(CityPO record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("city");
        
        if (record.getId() != null) {
            sql.VALUES("ID", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("Name", "#{name,jdbcType=CHAR}");
        }
        
        if (record.getCountrycode() != null) {
            sql.VALUES("CountryCode", "#{countrycode,jdbcType=CHAR}");
        }
        
        if (record.getDistrict() != null) {
            sql.VALUES("District", "#{district,jdbcType=CHAR}");
        }
        
        if (record.getPopulation() != null) {
            sql.VALUES("Population", "#{population,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public String updateByPrimaryKeySelective(CityPO record) {
        SQL sql = new SQL();
        sql.UPDATE("city");
        
        if (record.getName() != null) {
            sql.SET("Name = #{name,jdbcType=CHAR}");
        }
        
        if (record.getCountrycode() != null) {
            sql.SET("CountryCode = #{countrycode,jdbcType=CHAR}");
        }
        
        if (record.getDistrict() != null) {
            sql.SET("District = #{district,jdbcType=CHAR}");
        }
        
        if (record.getPopulation() != null) {
            sql.SET("Population = #{population,jdbcType=INTEGER}");
        }
        
        sql.WHERE("ID = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}