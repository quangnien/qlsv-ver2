package com.example.demo.entity.user;
import java.util.Date;

public interface UserInterface {

	String sayHello();

	public String getId();

	public void setId(String id);

	public String getHo();

	public void setHo(String ho);

	public String getTen();

	public void setTen(String ten);

	public String getDiaChi();

	public void setDiaChi(String diaChi);

	public String getPhai();

	public void setPhai(String phai);

	public Date getNgaySinh();

	public void setNgaySinh(Date ngaySinh);

	public String getSdt();

	public void setSdt(String sdt);

	public int getTrangThai();

	public void setTrangThai(int trangThai);

	public String getHinhAnh();

	public void setHinhAnh(String hinhAnh);

	public String getEmail();

	public void setEmail(String email);

	public String getPassword();

	public void setPassword(String password);
}
