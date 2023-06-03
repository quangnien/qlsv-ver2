package com.example.demo.constant;

/**
 * @author NienNQ
 * @created 2023 - 03 - 05 1:12 PM
 * @project qlsv
 */
public class MasterDataExceptionConstant {

    /* Common */
    public static final String COMMON_EMAIL_WRONG_FORMAT = "Bạn phải nhập định dạng email@gmail.com!";
    public static final String COMMON_EMAIL_IS_EXIST = "Email đã được dùng!";
    public static final String E_COMMON_NOT_CONFIRM_PASSWORD = "ConfirmPassword mustn't null!";
    public static final String E_COMMON_NOT_PASSWORD_OLD= "Mật khẩu cũ không được bỏ trống!";
    public static final String E_COMMON_NOT_PASSWORD_NEW = "Mật khẩu mới không được bỏ trống null!";
    public static final String E_COMMON_NOT_MATCH_PASSWORD = "Sai mật khẩu!";
    public static final String E_COMMON_NOT_EQUAL_CONFIRM_PASSWORD = "Mật khẩu không đúng với mật khẩu nhập lại!";

    /* Khoa */
    public static final String E_KHOA_NOT_FOUND_KHOA = "Khoa không tồn tại!";
    public static final String E_KHOA_DUPLICATE_MA_KHOA = "Mã khoa đã tồn tại, hãy nhập một mã khoa khác!";
    public static final String E_KHOA_IS_USED = "Khoa đã được dùng!";

    /* Lop */
    public static final String E_LOP_NOT_FOUND_LOP = "Lớp không tồn tại!";
    public static final String E_LOP_DUPLICATE_MA_LOP = "Mã lớp đã tồn tại, hãy nhập một mã lớp khác!";
    public static final String E_LOP_IS_USED = "Lớp đã được sử dụng!";

    /* Chuyen Nganh */
    public static final String E_CHUYENNGANH_NOT_FOUND_CHUYENNGANH = "Chuyên ngành không tồn tại!";
    public static final String E_CHUYENNGANH_DUPLICATE_MA_CHUYENNGANH = "Mã chuyên ngành đã tồn tại, hãy nhập một mã chuyên ngành khác!";
    public static final String E_CHUYENNGANH_IS_USED = "Chuyên ngành đã được sử dụng!";

    /* Day Of Week */
    public static final String E_DAYOFWEEK_NOT_FOUND_DAYOFWEEK = "Day of week không tồn tại!";
    public static final String E_DAYOFWEEK_DUPLICATE_MA_DAYOFWEEK = "Mã day of week đã tồn tại, hãy nhập một mã day of week khác!";
    public static final String E_DAYOFWEEK_IS_USED = "Day of week đã được sử dụng!";

    /* CTDT */
    public static final String E_CTDT_NOT_FOUND_CTDT = "Chương trình đào tạo không tồn tại!";
    public static final String E_CTDT_DUPLICATE_MA_CTDT = "Mã chương trình đào tạo đã tồn tại, hãy nhập một mã chương trình đào tạo khác!";
    public static final String E_CTDT_IS_USED = "Chương trình đào tạo đã được sử dụng!";

    /* Sinh Vien */
    public static final String E_SINHVIEN_NOT_FOUND_SINHVIEN = "Sinh viên không tồn tại!";
    public static final String E_SINHVIEN_DUPLICATE_MA_SINHVIEN = "Mã sinh viên đã tồn tại, hãy nhập một mã sinh viên khác!";
    public static final String E_SINHVIEN_IS_USED = "Sinh viên đã được sử dụng!";

    /* Giang Vien */
    public static final String E_GIANGVIEN_NOT_FOUND_GIANGVIEN = "Giảng viên không tồn tại!";
    public static final String E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN = "Mã giảng viên đã tồn tại, hãy nhập một mã giảng viên mới";
    public static final String E_GIANGVIEN_IS_USED = "Giang Vien đã được sử dụng!";

    /* Sinh Vien */
    public static final String E_ADMIN_NOT_FOUND_ADMIN = "Admin không tồn tại!";

    /* Mon Hoc */
    public static final String E_MONHOC_NOT_FOUND_MONHOC = "Môn học không tồn tại!";
    public static final String E_MONHOC_DUPLICATE_MA_MONHOC = "Mã môn học đã tồn tại, hãy nhập một mã môn học mới!";
    public static final String E_MONHOC_IS_USED = "Môn học đã được sử dụng!";

    /* Mon Hoc Tien Quyet */
    public static final String E_MONHOCTIENQUYET_NOT_FOUND_MONHOCTIENQUYET = "Môn học tiên quyết không tồn tại!";

    /* Ds Lop Tc */
    public static final String E_DSLOPTC_NOT_FOUND_DSLOPTC = "Danh sách lớp tín chỉ không tồn tại!";
    public static final String E_DSLOPTC_DUPLICATE_MA_DSLOPTC = "Mã Ds LopTc đã tồn tại, hãy nhập một mã Ds LopTc mới!";
    public static final String E_DSLOPTC_FULL_SLOT = "Lop TC is full, please choose another one!";
    public static final String E_DSLOPTC_IS_USED = "Lop TC is used!";
    public static final String E_DSLOPTC_NGOAI_TIME_DK = "Đã kết thúc thời gian đăng ký môn, không thể hủy đăng ký!";

    /* Chi Tiet Lop Tc */
    public static final String E_CHITIETLOPTC_NOT_FOUND_DSLOPTC = "Chi Tiết Lớp Tc không tồn tại!";
    public static final String E_CHITIETLOPTC_DUPLICATE_MA_LOPTC_TIET_THU = "(Ma lop Tc, Tiet, Thu) đã tồn tại!";
    public static final String E_CHITIETLOPTC_IS_USED = "Chi Tiet Lop TC is used!";

    /* Diem */
    public static final String E_DIEM_NOT_FOUND_DSLOPTC = "Điểm không tồn tại!";
    public static final String E_DIEM_DUPLICATE_MASV_MALOPTC = "(Ma sinh vien, Ma lop tin chi) đã tồn tại!";
    public static final String E_DIEM_IS_USED = "Diem is used!";
    public static final String E_DIEM_LIST_MAlOPTC_NULL = "Bạn chưa lựa chọn lớp tín chỉ để hủy đăng ký!";

    /* TKB */
    public static final String E_TKB_NOT_FOUND_DATE_BEGIN = "Ngày bắt đầu không để bị trống!";
    public static final String E_TKB_NOT_FOUND_DATE_END = "Ngày kết thúc không để bị trống!";

    /* Ke Hoach Nam */
    public static final String E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM = "Kết hoạch năm không tồn tại!";
    public static final String E_KEHOACHNAM_DUPLICATE_MA_KEHOACHNAM = "Mã kết hoạch năm đã tồn tại, hãy nhập một mã khác!";
    public static final String E_KEHOACHNAM_IS_USED = "Ke Hoach Nam is used!";

    /* Thong Ke */
    public static final String E_THONGKEDIEM_NOT_FOUND_THONGKEDIEM = "Invalid input!";

//    /* Common */
//    public static final String COMMON_EMAIL_WRONG_FORMAT = "You entered the wrong format for email@gmail.com!";
//    public static final String COMMON_EMAIL_IS_EXIST = "email is already in use!";
//    public static final String E_COMMON_NOT_CONFIRM_PASSWORD = "ConfirmPassword mustn't null!";
//    public static final String E_COMMON_NOT_PASSWORD_OLD= "Old password mustn't null!";
//    public static final String E_COMMON_NOT_PASSWORD_NEW = "New password mustn't null!";
//    public static final String E_COMMON_NOT_MATCH_PASSWORD = "Incorrect password!";
//    public static final String E_COMMON_NOT_EQUAL_CONFIRM_PASSWORD = "Password is'nt equal with ConfirmPassword!";
//
//    /* Khoa */
//    public static final String E_KHOA_NOT_FOUND_KHOA = "Khoa does not exist!";
//    public static final String E_KHOA_DUPLICATE_MA_KHOA = "Ma khoa exists, please input another one!";
//    public static final String E_KHOA_IS_USED = "Khoa is used!";
//
//    /* Lop */
//    public static final String E_LOP_NOT_FOUND_LOP = "Lop does not exist!";
//    public static final String E_LOP_DUPLICATE_MA_LOP = "Ma lop exists, please input another one!";
//    public static final String E_LOP_IS_USED = "Lop is used!";
//
//    /* Sinh Vien */
//    public static final String E_SINHVIEN_NOT_FOUND_SINHVIEN = "Sinh vien does not exist!";
//    public static final String E_SINHVIEN_DUPLICATE_MA_SINHVIEN = "Ma sinh vien exists, please input another one!";
//    public static final String E_SINHVIEN_IS_USED = "Sinh Vien is used!";
//
//    /* Giang Vien */
//    public static final String E_GIANGVIEN_NOT_FOUND_GIANGVIEN = "Giang vien does not exist!";
//    public static final String E_GIANGVIEN_DUPLICATE_MA_GIANGVIEN = "Ma giang vien exists, please input another one!";
//    public static final String E_GIANGVIEN_IS_USED = "Giang Vien is used!";
//
//    /* Sinh Vien */
//    public static final String E_ADMIN_NOT_FOUND_ADMIN = "Admin does not exist!";
//
//    /* Mon Hoc */
//    public static final String E_MONHOC_NOT_FOUND_MONHOC = "Mon hoc does not exist!";
//    public static final String E_MONHOC_DUPLICATE_MA_MONHOC = "Ma mon hoc exists, please input another one!";
//    public static final String E_MONHOC_IS_USED = "Mon Hoc is used!";
//
//    /* Ds Lop Tc */
//    public static final String E_DSLOPTC_NOT_FOUND_DSLOPTC = "Ds Lop Tc does not exist!";
//    public static final String E_DSLOPTC_DUPLICATE_MA_DSLOPTC = "Ma lop Tc exists, please input another one!";
//    public static final String E_DSLOPTC_FULL_SLOT = "Lop TC is full, please choose another one!";
//    public static final String E_DSLOPTC_IS_USED = "Lop TC is used!";
//
//    /* Chi Tiet Lop Tc */
//    public static final String E_CHITIETLOPTC_NOT_FOUND_DSLOPTC = "Chi Tiet Lop Tc does not exist!";
//    public static final String E_CHITIETLOPTC_DUPLICATE_MA_LOPTC_TIET_THU = "(Ma lop Tc, Tiet, Thu) exists, please input another one!";
//    public static final String E_CHITIETLOPTC_IS_USED = "Chi Tiet Lop TC is used!";
//
//    /* Diem */
//    public static final String E_DIEM_NOT_FOUND_DSLOPTC = "Diem does not exist!";
//    public static final String E_DIEM_DUPLICATE_MASV_MALOPTC = "(Ma sinh vien, Ma lop tin chi) exists, please input another one!";
//    public static final String E_DIEM_IS_USED = "Diem is used!";
//
//    /* TKB */
//    public static final String E_TKB_NOT_FOUND_DATE_BEGIN = "Date begin is null!";
//    public static final String E_TKB_NOT_FOUND_DATE_END = "Date end is null!";
//
//    /* Ke Hoach Nam */
//    public static final String E_KEHOACHNAM_NOT_FOUND_KEHOACHNAM = "Ke Hoach Nam does not exist!";
//    public static final String E_KEHOACHNAM_DUPLICATE_MA_KEHOACHNAM = "Ma Ke Hoach Nam exists, please input another one!";
//    public static final String E_KEHOACHNAM_IS_USED = "Ke Hoach Nam is used!";
//
//    /* Thong Ke */
//    public static final String E_THONGKEDIEM_NOT_FOUND_THONGKEDIEM = "Invalid input!";

}
