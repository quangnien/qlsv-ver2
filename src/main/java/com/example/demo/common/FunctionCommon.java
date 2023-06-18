package com.example.demo.common;

import com.example.demo.entity.GiangVienEntity;
import com.example.demo.entity.SinhVienEntity;
import com.example.demo.payload.request.SignupRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Admin
 * @created 5/31/2023 2:27 PM
 * @project demo
 */
@Component
public class FunctionCommon {

    public SignupRequest createUserAccountTemp(Object object){
        SignupRequest objectTemp = new SignupRequest();
        ModelMapper modelMapper = new ModelMapper();
        if(object instanceof SinhVienEntity){
            SinhVienEntity result = new SinhVienEntity();
            result = modelMapper.map(object, SinhVienEntity.class);
            objectTemp.setUsername(result.getMaSV());
            objectTemp.setPassword(result.getPassword());
            objectTemp.setEmail(result.getEmail());
            objectTemp.setUserId(result.getId());
            objectTemp.setUserFullName(result.getHoSV() + " " + result.getTenSV());

            Set<String> roles = new HashSet<>();
            roles.add("SINHVIEN");
            objectTemp.setRole(roles);
        }
        else if(object instanceof GiangVienEntity){
            GiangVienEntity result = new GiangVienEntity();
            result = modelMapper.map(object, GiangVienEntity.class);
            objectTemp.setUsername(result.getMaGV());
            objectTemp.setPassword(result.getPassword());
            objectTemp.setEmail(result.getEmail());
            objectTemp.setUserId(result.getId());
            objectTemp.setUserFullName(result.getHoGV() + " " + result.getTenGV());

            Set<String> roles = new HashSet<>();
            roles.add("GIANGVIEN");
            objectTemp.setRole(roles);
        }
        return objectTemp;
    }

    public static boolean isValidEmailFormat(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@gmail.com";
        return email.matches(regex);
    }

    public static double roundToHalf(float num) {
        float floor = (float) Math.floor(num);
        float diff = num - floor;
        if (diff <= 0.25) {
            return floor;
        } else if (diff <= 0.75) {
            return floor + 0.5;
        } else {
            return floor + 1;
        }
    }

    public List<String> convertListToSetToList(List<String> result){
        // Convert List to Set
        Set<String> resultSet = new HashSet<>(result);

        // Convert Set back to List
        List<String> resultList = new ArrayList<>(resultSet);

        // Return the List
        return resultList;
    }

    // Hàm chuyển đổi object thành mảng byte
    public byte[] convertObjectToBytes(ReturnObject returnObject) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsBytes(returnObject);
        } catch (Exception e) {
            // Xử lý nếu có lỗi xảy ra trong quá trình chuyển đổi
            e.printStackTrace();
            return null; // hoặc thực hiện xử lý khác tùy ý
        }
    }
}
