package com.demien.sparktest.domain;

public class Param implements IPersistable {

    private Long id;
    private String name;
    private String dataType;
    private Item item;

    public Param() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Param param = (Param) o;

        if (id != null ? !id.equals(param.id) : param.id != null) return false;
        if (name != null ? !name.equals(param.name) : param.name != null) return false;
        if (dataType != null ? !dataType.equals(param.dataType) : param.dataType != null) return false;
        return item != null ? item.equals(param.item) : param.item == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }
}
