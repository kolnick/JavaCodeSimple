package com.caochaojie.generate.entity;

public class CityPO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.ID
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.Name
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.CountryCode
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    private String countrycode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.District
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    private String district;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.Population
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    private Integer population;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.ID
     *
     * @return the value of city.ID
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.ID
     *
     * @param id the value for city.ID
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.Name
     *
     * @return the value of city.Name
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.Name
     *
     * @param name the value for city.Name
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.CountryCode
     *
     * @return the value of city.CountryCode
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public String getCountrycode() {
        return countrycode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.CountryCode
     *
     * @param countrycode the value for city.CountryCode
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode == null ? null : countrycode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.District
     *
     * @return the value of city.District
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public String getDistrict() {
        return district;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.District
     *
     * @param district the value for city.District
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.Population
     *
     * @return the value of city.Population
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.Population
     *
     * @param population the value for city.Population
     *
     * @mbg.generated Thu Jun 30 22:23:54 CST 2022
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }
}