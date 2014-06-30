package com.etlx.metadata;

public class Column {

    private String name;
    private String dataType;
    private String constraint;
    private Integer length;
    private Integer precision;

    public Column(final String name, final String dataType, final String constraint, final Integer len, final Integer precision) {
        this.name = name.toUpperCase();
        this.dataType = dataType;
        this.constraint = constraint;
        this.length = len;
        this.precision = precision;
    }

    @Override
    public String toString() {
        return getName() + " " + getConstraint() + " " + getDataType() + "(" + getLength() + "," + getPrecision() + ")";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the dataType
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @return the constraint
     */
    public String getConstraint() {
        return constraint;
    }

    /**
     * @return the length
     */
    public Integer getLength() {
        return length;
    }

    /**
     * @return the precision
     */
    public Integer getPrecision() {
        return precision;
    }
}