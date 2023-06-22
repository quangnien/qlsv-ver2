package com.example.demo.enumdef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 18 10:43 AM
 * @project qlsv5
 */

public enum RoleEnum {

    ADMIN("ADMIN", "ADMIN"),
    GIANGVIEN("GIANGVIEN", "GIANGVIEN"),
    SINHVIEN("SINHVIEN", "SINHVIEN");

    private String value;

    private String name;

    RoleEnum(String value, String name) {
        this.value = value;

        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByValue(String value) {
        for (RoleEnum en : RoleEnum.values()) {
            if (value == en.getValue()) {
                return en.getName();
            }
        }

//        return StringUtils.EMPTY;
        return "";
    }

    public static List<Select2Dto> getSelect2ComboList() {
        List<Select2Dto> list = new ArrayList<>();
        list.add(new Select2Dto("", "", ""));
        list.add(new Select2Dto(String.valueOf(RoleEnum.ADMIN.getValue()), RoleEnum.ADMIN.getName(), RoleEnum.ADMIN.getName()));
        list.add(new Select2Dto(String.valueOf(RoleEnum.GIANGVIEN.getValue()), RoleEnum.GIANGVIEN.getName(), RoleEnum.GIANGVIEN.getName()));
        list.add(new Select2Dto(String.valueOf(RoleEnum.SINHVIEN.getValue()), RoleEnum.SINHVIEN.getName(), RoleEnum.SINHVIEN.getName()));
        return list;
    }

    public static List<Select2Dto> getSelect2ComboList1Layer() {
        List<Select2Dto> list = new ArrayList<>();
        list.add(new Select2Dto("", "", ""));
        list.add(new Select2Dto(String.valueOf(RoleEnum.ADMIN.getValue()), RoleEnum.ADMIN.getName(), RoleEnum.ADMIN.getName()));
        list.add(new Select2Dto(String.valueOf(RoleEnum.GIANGVIEN.getValue()), RoleEnum.GIANGVIEN.getName(), RoleEnum.GIANGVIEN.getName()));
        list.add(new Select2Dto(String.valueOf(RoleEnum.SINHVIEN.getValue()), RoleEnum.SINHVIEN.getName(), RoleEnum.SINHVIEN.getName()));
        return list;
    }

    public static List<String> getComboList() {
        List<String> list = new ArrayList<>();
        list.add(RoleEnum.SINHVIEN.getName());
        list.add(RoleEnum.GIANGVIEN.getName());
        list.add(RoleEnum.ADMIN.getName());
        return list;
    }
}
