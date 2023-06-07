package com.example.demo.enumdef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NienNQ
 * @created 2023 - 03 - 18 10:43 AM
 * @project qlsv5
 */

public enum XepLoaiEnum {

    A_PLUS("A+", "A+"),
    A("A", "A"),
    B_PLUS("B+", "B+"),
    B("B", "B"),
    C_PLUS("C+", "C+"),
    C("C", "C"),
    D_PLUS("D+", "D+"),
    D("D", "D"),
    F("F", "F");

    private String value;

    private String name;

    XepLoaiEnum(String value, String name) {
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
        for (XepLoaiEnum en : XepLoaiEnum.values()) {
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
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.A_PLUS.getValue()), XepLoaiEnum.A_PLUS.getName(), XepLoaiEnum.A_PLUS.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.A.getValue()), XepLoaiEnum.A.getName(), XepLoaiEnum.A.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.B_PLUS.getValue()), XepLoaiEnum.B_PLUS.getName(), XepLoaiEnum.B_PLUS.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.B.getValue()), XepLoaiEnum.B.getName(), XepLoaiEnum.B.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.C_PLUS.getValue()), XepLoaiEnum.C_PLUS.getName(), XepLoaiEnum.C_PLUS.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.C.getValue()), XepLoaiEnum.C.getName(), XepLoaiEnum.C.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.D_PLUS.getValue()), XepLoaiEnum.D_PLUS.getName(), XepLoaiEnum.D_PLUS.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.D.getValue()), XepLoaiEnum.D.getName(), XepLoaiEnum.D.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.F.getValue()), XepLoaiEnum.F.getName(), XepLoaiEnum.F.getName()));
        return list;
    }

    public static List<Select2Dto> getSelect2ComboList1Layer() {
        List<Select2Dto> list = new ArrayList<>();
        list.add(new Select2Dto("", "", ""));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.A_PLUS.getValue()), XepLoaiEnum.A_PLUS.getName(), XepLoaiEnum.A_PLUS.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.A.getValue()), XepLoaiEnum.A.getName(), XepLoaiEnum.A.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.B_PLUS.getValue()), XepLoaiEnum.B_PLUS.getName(), XepLoaiEnum.B_PLUS.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.B.getValue()), XepLoaiEnum.B.getName(), XepLoaiEnum.B.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.C_PLUS.getValue()), XepLoaiEnum.C_PLUS.getName(), XepLoaiEnum.C_PLUS.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.C.getValue()), XepLoaiEnum.C.getName(), XepLoaiEnum.C.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.D_PLUS.getValue()), XepLoaiEnum.D_PLUS.getName(), XepLoaiEnum.D_PLUS.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.D.getValue()), XepLoaiEnum.D.getName(), XepLoaiEnum.D.getName()));
        list.add(new Select2Dto(String.valueOf(XepLoaiEnum.F.getValue()), XepLoaiEnum.F.getName(), XepLoaiEnum.F.getName()));
        return list;
    }

    public static List<String> getComboList() {
        List<String> list = new ArrayList<>();
        list.add(XepLoaiEnum.F.getName());
        list.add(XepLoaiEnum.D.getName());
        list.add(XepLoaiEnum.D_PLUS.getName());
        list.add(XepLoaiEnum.C.getName());
        list.add(XepLoaiEnum.C_PLUS.getName());
        list.add(XepLoaiEnum.B.getName());
        list.add(XepLoaiEnum.B_PLUS.getName());
        list.add(XepLoaiEnum.A.getName());
        list.add(XepLoaiEnum.A_PLUS.getName());
        return list;
    }
}
