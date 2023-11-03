GO
USE master
USE master

GO
CREATE DATABASE AMShop
ON PRIMARY
(
	NAME = AMShop,
	FILENAME = N'D:\08_Project\Microsoft SQL Server Management Studio\AMShop\AMShop.mdf',
	SIZE = 32 MB,
	MAXSIZE = 1024 MB,
	FILEGROWTH = 1 MB
)
LOG ON
(
	NAME = AMShop_LOG,
	FILENAME = N'D:\08_Project\Microsoft SQL Server Management Studio\AMShop\AMShop.ldf',
	SIZE = 32 MB,
	MAXSIZE = 1024 MB,
	FILEGROWTH = 1 MB
)

GO
USE AMShop
------------------------------------------------------------
CREATE TABLE CuaHang
(
	MaCuaHang nvarchar(16) NOT NULL,
	TenCuaHang nvarchar(64),
	DiaChi nvarchar(128),
	SoDienThoai nvarchar(10)
)

ALTER TABLE CuaHang
ADD CONSTRAINT PK_CuaHang PRIMARY KEY (MaCuaHang)
------------------------------------------------------------
CREATE TABLE ChucVu
(
	ChucVu nvarchar(32) NOT NULL
)

ALTER TABLE ChucVu
ADD CONSTRAINT PK_ChucVu PRIMARY KEY (ChucVu)
------------------------------------------------------------
CREATE TABLE NhanVien
(
	MaNhanVien nvarchar(10) NOT NULL,
	HoTen nvarchar(64),
	SoDienThoai nvarchar(10),
	ChucVu nvarchar(32) NOT NULL,
	NgaySinh date,
	CanCuocCongDan nvarchar(12),
	GioiTinh bit,
	DiaChi nvarchar(128),
	NgayBatDauLam date,
	NgayKetThucLam date,
	Luong decimal
)

ALTER TABLE NhanVien
ADD CONSTRAINT PK_NhanVien PRIMARY KEY (MaNhanVien),
	CONSTRAINT FK_NhanVien_ChucVu FOREIGN KEY (ChucVu) REFERENCES ChucVu(ChucVu)
------------------------------------------------------------
CREATE TABLE TaiKhoan
(
	TenDangNhap nvarchar(10) NOT NULL,
	MatKhau nvarchar(32)
)

ALTER TABLE TaiKhoan
ADD CONSTRAINT PK_TaiKhoan PRIMARY KEY (TenDangNhap),
	CONSTRAINT FK_TaiKhoan_NhanVien FOREIGN KEY (TenDangNhap) REFERENCES NhanVien(MaNhanVien)
------------------------------------------------------------
CREATE TABLE NhomKhachHang
(
	NhomKhachHang nvarchar(32) NOT NULL
)

ALTER TABLE NhomKhachHang
ADD CONSTRAINT PK_NhomKhachHang PRIMARY KEY (NhomKhachHang)
------------------------------------------------------------
CREATE TABLE KhachHang
(
	MaKhachHang nvarchar(10) NOT NULL,
	HoTen nvarchar(64),
	SoDienThoai nvarchar(10),
	NhomKhachHang nvarchar(32) NOT NULL,
	DiaChi nvarchar(128),
)

ALTER TABLE KhachHang
ADD CONSTRAINT PK_KhachHang PRIMARY KEY (MaKhachHang),
	CONSTRAINT FK_KhachHang_NhomKhachHang FOREIGN KEY (NhomKhachHang) REFERENCES NhomKhachHang(NhomKhachHang)
------------------------------------------------------------
CREATE TABLE NhaSanXuat
(
	NhaSanXuat nvarchar(64) NOT NULL,
)

ALTER TABLE NhaSanXuat
ADD CONSTRAINT PK_NhaSanXuat PRIMARY KEY (NhaSanXuat)
------------------------------------------------------------
CREATE TABLE DanhMuc
(
	DanhMuc nvarchar(16) NOT NULL,
)

ALTER TABLE DanhMuc
ADD CONSTRAINT PK_DanhMuc PRIMARY KEY (DanhMuc)
------------------------------------------------------------
CREATE TABLE GioiTinh
(
	GioiTinh nvarchar(4) NOT NULL,
)

ALTER TABLE GioiTinh
ADD CONSTRAINT PK_GioiTinh PRIMARY KEY (GioiTinh)
------------------------------------------------------------
CREATE TABLE MauSac
(
	MauSac nvarchar(8) NOT NULL,
)

ALTER TABLE MauSac
ADD CONSTRAINT PK_MauSac PRIMARY KEY (MauSac)
------------------------------------------------------------
CREATE TABLE KichThuoc
(
	KichThuoc nvarchar(8) NOT NULL,
)

ALTER TABLE KichThuoc
ADD CONSTRAINT PK_KichThuoc PRIMARY KEY (KichThuoc)
------------------------------------------------------------
CREATE TABLE ChatLieu
(
	ChatLieu nvarchar(32) NOT NULL,
)

ALTER TABLE ChatLieu
ADD CONSTRAINT PK_ChatLieu PRIMARY KEY (ChatLieu)
------------------------------------------------------------
CREATE TABLE QuanAo
(
	MaQuanAo nvarchar(8) NOT NULL,
	TenQuanAo nvarchar(64),
	DonGiaNhap decimal,
	DonGiaBan decimal,
	SoLuongTrongKho int,
	NhaSanXuat nvarchar(64) NOT NULL,
	DanhMuc nvarchar(16) NOT NULL,
	GioiTinh nvarchar(4) NOT NULL,
	MauSac nvarchar(8) NOT NULL,
	KichThuoc nvarchar(8) NOT NULL,
	ChatLieu nvarchar(32) NOT NULL,
	HinhAnh varbinary(MAX),
	NgungNhap bit
)

ALTER TABLE QuanAo
ADD CONSTRAINT PK_QuanAo PRIMARY KEY (MaQuanAo),
	CONSTRAINT FK_QuanAo_NhaSanXuat FOREIGN KEY (NhaSanXuat) REFERENCES NhaSanXuat(NhaSanXuat),
	CONSTRAINT FK_QuanAo_DanhMuc FOREIGN KEY (DanhMuc) REFERENCES DanhMuc(DanhMuc),
	CONSTRAINT FK_QuanAo_GioiTinh FOREIGN KEY (GioiTinh) REFERENCES GioiTinh(GioiTinh),
	CONSTRAINT FK_QuanAo_MauSac FOREIGN KEY (MauSac) REFERENCES MauSac(MauSac),
	CONSTRAINT FK_QuanAo_KichThuoc FOREIGN KEY (KichThuoc) REFERENCES KichThuoc(KichThuoc),
	CONSTRAINT FK_QuanAo_ChatLieu FOREIGN KEY (ChatLieu) REFERENCES ChatLieu(ChatLieu)
------------------------------------------------------------
CREATE TABLE HoaDon
(
	MaHoaDon nvarchar(12) NOT NULL,
	MaCuaHang nvarchar(16) NOT NULL,
	MaNhanVien nvarchar(10) NOT NULL,
	MaKhachHang nvarchar(10) NOT NULL,
	ThoiGianLapHoaDon datetime
)

ALTER TABLE HoaDon
ADD CONSTRAINT PK_HoaDon PRIMARY KEY (MaHoaDon),
	CONSTRAINT PK_HoaDon_CuaHang FOREIGN KEY (MaCuaHang) REFERENCES CuaHang(MaCuaHang),
	CONSTRAINT PK_HoaDon_NhanVien FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
	CONSTRAINT PK_HoaDon_KhachHang FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
------------------------------------------------------------
CREATE TABLE ChiTietHoaDon
(
	MaHoaDon nvarchar(12) NOT NULL,
	MaQuanAo nvarchar(8) NOT NULL,
	SoLuong int,
	DonGia decimal
)

ALTER TABLE ChiTietHoaDon
ADD CONSTRAINT FK_ChiTietHoaDon_HoaDon FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
	CONSTRAINT FK_ChiTietHoaDon_QuanAo FOREIGN KEY (MaQuanAo) REFERENCES QuanAo(MaQuanAo),
	CONSTRAINT PK_ChiTietHoaDon PRIMARY KEY (MaHoaDon, MaQuanAo)
	------------------------------------------------------------
CREATE TABLE DonDatHang
(
	MaDonDatHang nvarchar(12) NOT NULL,
	MaNhanVien nvarchar(10) NOT NULL,
	MaKhachHang nvarchar(10) NOT NULL,
	ThoiGianLapHoaDon datetime,
	TrangThaiThanhToan bit
)

ALTER TABLE DonDatHang
ADD CONSTRAINT PK_DonDatHang PRIMARY KEY (MaDonDatHang),
	CONSTRAINT PK_DonDatHang_NhanVien FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
	CONSTRAINT PK_DonDatHang_KhachHang FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
------------------------------------------------------------
CREATE TABLE ChiTietDonDatHang
(
	MaDonDatHang nvarchar(12) NOT NULL,
	MaQuanAo nvarchar(8) NOT NULL,
	SoLuong int
)

ALTER TABLE ChiTietDonDatHang
ADD CONSTRAINT FK_ChiTietDonDatHang_DonDatHang FOREIGN KEY (MaDonDatHang) REFERENCES DonDatHang(MaDonDatHang),
	CONSTRAINT FK_ChiTietDonDatHang_QuanAo FOREIGN KEY (MaQuanAo) REFERENCES QuanAo(MaQuanAo),
	CONSTRAINT PK_ChiTietDonDatHang PRIMARY KEY (MaDonDatHang, MaQuanAo)