--Tạo database
CREATE DATABASE CC00770XASM3
GO

--Sử dụng database
USE CC00770XASM3
GO
--Tạo bảo người dùng
CREATE TABLE Users
(
UserName NVARCHAR(255)PRIMARY KEY,
DisplayName NVARCHAR(MAX) NOT NULL
)
GO

--Tạo bảng lưu avatar
CREATE TABLE Avatar
(
UserName NVARCHAR(255)PRIMARY KEY,
Avatar IMAGE
CONSTRAINT UserName FOREIGN KEY (UserName) REFERENCES Users(UserName)
)
GO

--Tạo bảng tin nhắn
CREATE TABLE MessageDetail
(
MessageID INT PRIMARY KEY IDENTITY(1,1),
FromUser NVARCHAR(255),
ToUser NVARCHAR(255),
DateCreate SMALLDATETIME NOT NULL,
[Content] NVARCHAR(MAX),
MessageType NVARCHAR(255) NOT NULL,
CONSTRAINT FromUser FOREIGN KEY (FromUser) REFERENCES Users(UserName),
CONSTRAINT ToUser FOREIGN KEY (ToUser) REFERENCES Users(UserName)
)

--Chèn user Server vào danh sách user (tránh lỗi constraint)
INSERT Users(UserName, DisplayName)
VALUES(N'Server', N'Server')
GO
