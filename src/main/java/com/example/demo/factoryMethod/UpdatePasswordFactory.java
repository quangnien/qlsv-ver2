package com.example.demo.factoryMethod;

import com.example.demo.enumdef.RoleEnum;
import com.example.demo.strategy.StrategyUpdatePassword;
import com.example.demo.strategy.UpdatePasswordAdmin;
import com.example.demo.strategy.UpdatePasswordGiangVien;
import com.example.demo.strategy.UpdatePasswordSinhVien;

public class UpdatePasswordFactory {
    public static StrategyUpdatePassword createStrategyUpdatePW(String userRole){
        if(userRole.equals(RoleEnum.GIANGVIEN)) return new UpdatePasswordGiangVien();
        else if(userRole.equals(RoleEnum.SINHVIEN)) return new UpdatePasswordSinhVien();
        else if(userRole.equals(RoleEnum.ADMIN)) return new UpdatePasswordAdmin();
        else throw new IllegalArgumentException();
    }
}
