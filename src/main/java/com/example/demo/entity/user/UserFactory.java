package com.example.demo.entity.user;

public class UserFactory {
    public UserInterface createUser(ERole userType) {
        switch (userType) {
            case ROLE_ADMIN:
                return new AdminEntity();
            case ROLE_GIANGVIEN:
                return new GiangVienEntity();
            case ROLE_SINHVIEN:
                return new SinhVienEntity();
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}
