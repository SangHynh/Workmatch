-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               11.3.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table works.candidate
CREATE TABLE IF NOT EXISTS `candidate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `dob` date NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdcxytaubmielyryuyetjxmwg1` (`city_id`),
  CONSTRAINT `FKdcxytaubmielyryuyetjxmwg1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table works.candidate: ~10 rows (approximately)
INSERT INTO `candidate` (`id`, `address`, `dob`, `full_name`, `phone`, `city_id`) VALUES
	(5, 'Hóc Môn', '2001-10-12', 'Huỳnh Văn Sang', '0343564321', 58),
	(6, '.', '1999-02-01', 'Tèo', '0123321123', 20),
	(7, 's', '2003-10-11', 'Thân Thị Đẹt', '02480008000', 20),
	(8, '1', '1996-02-03', 'Tí', '02439718200', 9),
	(9, 'a', '2001-01-01', 'Hoa', '02873008282', 17),
	(10, '123', '1989-10-02', 'Alibaba', '02480008001', 18),
	(11, 'bình chánh', '2001-10-10', 'Giàu', '02873008282', 58),
	(12, 'hóc môn', '2003-10-10', 'Hiệp', '0123321123', 58),
	(13, '1', '2004-10-01', 'Lộc', '02480008000', 38),
	(14, 'bà điểm', '2005-01-08', 'Anh', '02473008888', 58);

-- Dumping structure for table works.candidate_skill
CREATE TABLE IF NOT EXISTS `candidate_skill` (
  `more_infos` varchar(1000) DEFAULT NULL,
  `skill_level` enum('FRESHER','INTERN','JUNIOR','LEADER','MIDDLE','SENIOR') NOT NULL,
  `can_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`can_id`,`skill_id`),
  KEY `FKb7cxhiqhcah7c20a2cdlvr1f8` (`skill_id`),
  CONSTRAINT `FKb0m5tm3fi0upa3b3kjx3vrlxs` FOREIGN KEY (`can_id`) REFERENCES `candidate` (`id`),
  CONSTRAINT `FKb7cxhiqhcah7c20a2cdlvr1f8` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table works.candidate_skill: ~0 rows (approximately)

-- Dumping structure for table works.city
CREATE TABLE IF NOT EXISTS `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table works.city: ~63 rows (approximately)
INSERT INTO `city` (`id`, `name`) VALUES
	(1, 'An Giang'),
	(2, 'Bà Rịa - Vũng Tàu'),
	(3, 'Bắc Giang'),
	(4, 'Bắc Kạn'),
	(5, 'Bạc Liêu'),
	(6, 'Bắc Ninh'),
	(7, 'Bến Tre'),
	(8, 'Bình Định'),
	(9, 'Bình Dương'),
	(10, 'Bình Phước'),
	(11, 'Bình Thuận'),
	(12, 'Cà Mau'),
	(13, 'Cần Thơ'),
	(14, 'Cao Bằng'),
	(15, 'Đà Nẵng'),
	(16, 'Đắk Lắk'),
	(17, 'Đắk Nông'),
	(18, 'Điện Biên'),
	(19, 'Đồng Nai'),
	(20, 'Đồng Tháp'),
	(21, 'Gia Lai'),
	(22, 'Hà Giang'),
	(23, 'Hà Nam'),
	(24, 'Hà Nội'),
	(25, 'Hà Tĩnh'),
	(26, 'Hải Dương'),
	(27, 'Hải Phòng'),
	(28, 'Hậu Giang'),
	(29, 'Hòa Bình'),
	(30, 'Hưng Yên'),
	(31, 'Khánh Hòa'),
	(32, 'Kiên Giang'),
	(33, 'Kon Tum'),
	(34, 'Lai Châu'),
	(35, 'Lâm Đồng'),
	(36, 'Lạng Sơn'),
	(37, 'Lào Cai'),
	(38, 'Long An'),
	(39, 'Nam Định'),
	(40, 'Nghệ An'),
	(41, 'Ninh Bình'),
	(42, 'Ninh Thuận'),
	(43, 'Phú Thọ'),
	(44, 'Phú Yên'),
	(45, 'Quảng Bình'),
	(46, 'Quảng Nam'),
	(47, 'Quảng Ngãi'),
	(48, 'Quảng Ninh'),
	(49, 'Quảng Trị'),
	(50, 'Sóc Trăng'),
	(51, 'Sơn La'),
	(52, 'Tây Ninh'),
	(53, 'Thái Bình'),
	(54, 'Thái Nguyên'),
	(55, 'Thanh Hóa'),
	(56, 'Thừa Thiên Huế'),
	(57, 'Tiền Giang'),
	(58, 'TP. Hồ Chí Minh'),
	(59, 'Trà Vinh'),
	(60, 'Tuyên Quang'),
	(61, 'Vĩnh Long'),
	(62, 'Vĩnh Phúc'),
	(63, 'Yên Bái');

-- Dumping structure for table works.company
CREATE TABLE IF NOT EXISTS `company` (
  `comp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `about` varchar(2000) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `comp_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `web_url` varchar(255) DEFAULT NULL,
  `city_id` bigint(20) NOT NULL,
  PRIMARY KEY (`comp_id`),
  KEY `FK11ll1ewpdxjg9bm3jtg858qn8` (`city_id`),
  CONSTRAINT `FK11ll1ewpdxjg9bm3jtg858qn8` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table works.company: ~16 rows (approximately)
INSERT INTO `company` (`comp_id`, `about`, `address`, `comp_name`, `phone`, `web_url`, `city_id`) VALUES
	(3, 'FPT là tập đoàn công nghệ hàng đầu tại Việt Nam, chuyên cung cấp các dịch vụ công nghệ thông tin và viễn thông.', '17 Phố Duy Tân, Cầu Giấy', 'FPT Corporation', '02473008888', 'https://www.fpt.com.vn', 24),
	(4, 'VNG là công ty hàng đầu về giải pháp công nghệ tại Việt Nam, với các sản phẩm tiêu biểu như Zalo, Zing MP3.', '60 Trường Sơn, Phường 2, Tân Bình', 'VNG Corporation', '02873008282', 'https://www.vng.com.vn', 58),
	(5, 'Tinh Vân cung cấp các giải pháp phần mềm và dịch vụ công nghệ tiên tiến cho các doanh nghiệp.', 'Số 2, ngõ 73, Nguyễn Thái Học', 'Tinh Vân Group', '02439718200', 'https://www.tinhvan.com', 24),
	(6, 'VNPT là công ty viễn thông lớn của Việt Nam, cung cấp các dịch vụ điện thoại, internet và giải pháp công nghệ.', '57 Huỳnh Thúc Kháng, Đống Đa', 'VNPT Group', '02480008000', 'https://www.vnpt.com.vn', 24),
	(7, 'Bidv cung cấp các dịch vụ công nghệ cho ngành ngân hàng, bao gồm các giải pháp tài chính điện tử.', '35 Hồ Tùng Mậu, Cầu Giấy', 'Bidv Technology Solutions', '02422207979', 'https://www.bidv.com.vn', 24),
	(8, 'Tigo chuyên cung cấp các giải pháp phần mềm cho các doanh nghiệp vừa và nhỏ tại Việt Nam.', '30-32 Đường Lê Lợi, Quận 1', 'Tigo Việt Nam', '02862504700', 'https://www.tigo.com.vn', 58),
	(9, 'HanoiTech cung cấp các dịch vụ giải pháp phần mềm và công nghệ tiên tiến cho doanh nghiệp.', 'Số 10, ngõ 81, Phố Nguyễn Khang, Cầu Giấy', 'HanoiTech', '02438928888', 'https://www.hanoitech.com.vn', 24),
	(10, 'Momo là một ứng dụng ví điện tử nổi tiếng tại Việt Nam, cung cấp các dịch vụ thanh toán và chuyển tiền online.', 'Số 5, ngõ 23, Lê Thánh Tông', 'Momo', '02473048888', 'https://www.momo.vn', 24),
	(11, 'Tavico cung cấp các giải pháp phần mềm quản lý doanh nghiệp và thương mại điện tử tại Việt Nam.', '23 Lê Lợi, Quận 1', 'Tavico', '02838351268', 'https://www.tavico.vn', 58),
	(12, 'Alibaba là một công ty thương mại điện tử hàng đầu, cung cấp các dịch vụ kết nối người bán và người mua.', '123 Lê Duẩn, Quận 1', 'Alibaba Việt Nam', '02873000000', 'https://www.alibaba.vn', 58),
	(13, 'FPT Software là công ty phần mềm thuộc Tập đoàn FPT, chuyên cung cấp các giải pháp phần mềm cho các doanh nghiệp.', 'FPT Tower, 17 Duy Tân, Cầu Giấy', 'FPT Software', '02473018585', 'https://www.fpt-software.com', 24),
	(14, 'EI Vietnam là công ty chuyên cung cấp các giải pháp phần mềm ERP cho doanh nghiệp tại Việt Nam.', 'Số 12, Nguyễn Thị Minh Khai, Quận 1', 'EI Vietnam', '02873095555', 'https://www.eivietnam.vn', 1),
	(15, 'Chuyên lừa đảo', 'Lầu 3, Tòa nhà SaigonTel, Lô 46, CVPM, Quang Trung, P, Quận 12', 'Công Ty TNHH Thương Mại Và Dịch Vụ Nina', '02837154879', 'https://nina.vn', 58),
	(16, 'Techcombank cung cấp các giải pháp ngân hàng hiện đại với công nghệ tiên tiến.', '15 Phan Chu Trinh, Hoàn Kiếm', 'Techcombank', '02439410940', 'https://www.techcombank.com.vn', 24),
	(17, 'VNSoft chuyên cung cấp các dịch vụ tư vấn và phát triển phần mềm cho các doanh nghiệp.', '17 Nguyễn Cảnh Chân, Quận 1', 'VNSoft', '02838236999', 'https://www.vnsoft.com.vn', 58),
	(18, 'Thế Giới Di Động là chuỗi cửa hàng bán lẻ điện thoại và các thiết bị điện tử tại Việt Nam.', '235 Phan Đình Phùng, Quận Phú Nhuận', 'Thế Giới Di Động', '19002091', 'https://www.thegioididong.com', 58);

-- Dumping structure for table works.job
CREATE TABLE IF NOT EXISTS `job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_desc` varchar(255) NOT NULL,
  `job_name` varchar(255) NOT NULL,
  `company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`job_id`),
  KEY `FKbaqlvluu78phmo9ld89um7wnm` (`company`),
  CONSTRAINT `FKbaqlvluu78phmo9ld89um7wnm` FOREIGN KEY (`company`) REFERENCES `company` (`comp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table works.job: ~113 rows (approximately)
INSERT INTO `job` (`job_id`, `job_desc`, `job_name`, `company`) VALUES
	(3, 'Lương nghìn đô la mỹ tho', 'Backend Developer', 13),
	(4, 'Phát triển giao diện người dùng cho các ứng dụng web.', 'Frontend Developer', 3),
	(5, 'Chịu trách nhiệm cho các dịch vụ phía máy chủ và cơ sở dữ liệu.', 'Backend Developer', 3),
	(6, 'Xây dựng ứng dụng full-stack từ frontend đến backend.', 'Full Stack Developer', 3),
	(7, 'Lập kế hoạch và quản lý phát triển sản phẩm.', 'Product Manager', 3),
	(8, 'Thiết kế giao diện người dùng và tối ưu trải nghiệm người dùng.', 'UI/UX Designer', 3),
	(9, 'Kiểm thử và đảm bảo chất lượng phần mềm.', 'QA Engineer', 3),
	(10, 'Quản lý chiến lược marketing cho sản phẩm và thương hiệu.', 'Marketing Manager', 3),
	(11, 'Phát triển phần mềm theo yêu cầu và quy trình.', 'Software Engineer', 4),
	(12, 'Xây dựng giao diện người dùng tương tác và mượt mà.', 'Frontend Developer', 4),
	(13, 'Phát triển API và quản lý dữ liệu phía máy chủ.', 'Backend Developer', 4),
	(14, 'Phân tích và trực quan hóa dữ liệu để đưa ra quyết định kinh doanh.', 'Data Scientist', 4),
	(15, 'Xây dựng và duy trì hệ thống hạ tầng và CI/CD.', 'DevOps Engineer', 4),
	(16, 'Chịu trách nhiệm cho việc phát triển và tối ưu hóa sản phẩm.', 'Product Owner', 4),
	(17, 'Phân tích yêu cầu kinh doanh và chuyển hóa thành các giải pháp kỹ thuật.', 'Business Analyst', 4),
	(18, 'Phát triển và duy trì các trang web và ứng dụng web.', 'Web Developer', 5),
	(19, 'Phát triển ứng dụng Java cho các hệ thống lớn.', 'Java Developer', 5),
	(20, 'Kiểm tra và đảm bảo chất lượng phần mềm.', 'QA Tester', 5),
	(21, 'Quản lý các chiến dịch marketing online.', 'Digital Marketer', 5),
	(22, 'Phát triển ứng dụng cho hệ điều hành Android.', 'Android Developer', 5),
	(23, 'Xây dựng hệ thống xử lý dữ liệu quy mô lớn.', 'Data Engineer', 5),
	(24, 'Quản lý và tối ưu các hệ thống đám mây.', 'Cloud Engineer', 5),
	(25, 'Phát triển các tính năng giao diện người dùng web.', 'Frontend Engineer', 6),
	(26, 'Phát triển hệ thống API và cơ sở dữ liệu phía máy chủ.', 'Backend Engineer', 6),
	(27, 'Chịu trách nhiệm lên kế hoạch và phát triển sản phẩm.', 'Product Manager', 6),
	(28, 'Thiết kế các giao diện người dùng trực quan cho ứng dụng.', 'UI Designer', 6),
	(29, 'Xây dựng kiến trúc hệ thống phần mềm và đảm bảo hiệu suất.', 'Software Architect', 6),
	(30, 'Thiết kế và quản lý hệ thống trên đám mây.', 'Cloud Architect', 6),
	(31, 'Phát triển ứng dụng di động cho cả Android và iOS.', 'Mobile Developer', 6),
	(32, 'Phân tích các yêu cầu hệ thống và đề xuất các giải pháp kỹ thuật.', 'System Analyst', 7),
	(33, 'Tham gia phát triển phần mềm và bảo trì hệ thống.', 'Software Developer', 7),
	(34, 'Phát triển các giao diện người dùng đẹp mắt cho ứng dụng.', 'Frontend Developer', 7),
	(35, 'Xây dựng và duy trì các dịch vụ phía máy chủ.', 'Backend Developer', 7),
	(36, 'Quản lý và tối ưu hóa cơ sở dữ liệu.', 'Database Administrator', 7),
	(37, 'Phân tích và tối ưu quy trình kinh doanh.', 'Business Analyst', 7),
	(38, 'Kiểm tra phần mềm để đảm bảo chất lượng.', 'QA Engineer', 7),
	(39, 'Hỗ trợ kỹ thuật cho hệ thống IT trong công ty.', 'IT Support Specialist', 8),
	(40, 'Phát triển phần mềm đáp ứng yêu cầu người dùng.', 'Software Engineer', 8),
	(41, 'Thiết kế các sản phẩm đáp ứng nhu cầu của người sử dụng.', 'Product Designer', 8),
	(42, 'Tối ưu hóa trải nghiệm người dùng qua giao diện.', 'UI/UX Designer', 8),
	(43, 'Đảm bảo an ninh mạng cho các hệ thống phần mềm.', 'Security Analyst', 8),
	(44, 'Xây dựng và duy trì hệ thống hạ tầng đám mây.', 'Cloud Engineer', 8),
	(45, 'Phân tích và khai thác thông tin từ dữ liệu lớn.', 'Data Scientist', 8),
	(46, 'Phát triển phần mềm và hệ thống ứng dụng.', 'Software Engineer', 9),
	(47, 'Phân tích dữ liệu và báo cáo kết quả cho các bộ phận.', 'Data Analyst', 9),
	(48, 'Xây dựng giao diện người dùng và tối ưu hóa trải nghiệm.', 'Frontend Developer', 9),
	(49, 'Xây dựng và duy trì hệ thống API và cơ sở dữ liệu.', 'Backend Developer', 9),
	(50, 'Lập kế hoạch và giám sát tiến độ các dự án.', 'Project Manager', 9),
	(51, 'Đảm bảo an ninh cho hệ thống và dữ liệu.', 'Security Engineer', 9),
	(52, 'Thiết kế và quản lý các giải pháp đám mây.', 'Cloud Engineer', 9),
	(53, 'Thiết kế giao diện và cải thiện trải nghiệm người dùng.', 'UI/UX Designer', 10),
	(54, 'Phát triển ứng dụng di động cho Android và iOS.', 'Mobile App Developer', 10),
	(55, 'Xây dựng chiến lược phát triển sản phẩm.', 'Product Manager', 10),
	(56, 'Phát triển phần mềm với các công nghệ mới nhất.', 'Software Engineer', 10),
	(57, 'Quản lý các hệ thống và hạ tầng công ty.', 'System Administrator', 10),
	(58, 'Duy trì hệ thống tự động hóa và quy trình CI/CD.', 'DevOps Engineer', 10),
	(59, 'Đánh giá và cải tiến quy trình kinh doanh.', 'Business Analyst', 10),
	(60, 'Phát triển giao diện người dùng cho các ứng dụng web.', 'Frontend Developer', 11),
	(61, 'Xây dựng các API và hệ thống backend.', 'Backend Developer', 11),
	(62, 'Phát triển cả frontend và backend cho ứng dụng.', 'Full Stack Developer', 11),
	(63, 'Tổ chức và triển khai các chiến dịch marketing.', 'Marketing Specialist', 11),
	(64, 'Kiểm thử và đảm bảo chất lượng phần mềm.', 'Quality Assurance', 11),
	(65, 'Xây dựng hệ thống xử lý dữ liệu hiệu quả.', 'Data Engineer', 11),
	(66, 'Thiết kế các sản phẩm dễ sử dụng và phù hợp với thị trường.', 'Product Designer', 11),
	(67, 'Xây dựng và phát triển các ứng dụng web.', 'Web Developer', 12),
	(68, 'Phát triển các ứng dụng Java cho các hệ thống lớn.', 'Java Developer', 12),
	(69, 'Thiết kế các giải pháp đám mây cho doanh nghiệp.', 'Cloud Solutions Architect', 12),
	(70, 'Tìm kiếm cơ hội và phát triển kinh doanh.', 'Business Development Manager', 12),
	(71, 'Kiểm tra chất lượng sản phẩm phần mềm.', 'QA Engineer', 12),
	(72, 'Quản lý và tối ưu hóa các cơ sở dữ liệu.', 'Database Administrator', 12),
	(73, 'Thiết kế và xây dựng kiến trúc hệ thống phần mềm.', 'System Architect', 12),
	(74, 'Phát triển phần mềm cho các ứng dụng doanh nghiệp.', 'Software Engineer', 13),
	(75, 'Thiết kế giao diện cho các trang web doanh nghiệp.', 'Web Designer', 13),
	(76, 'Phát triển ứng dụng cho các nền tảng di động.', 'Mobile Developer', 13),
	(77, 'Phân tích các yêu cầu kinh doanh và xây dựng giải pháp.', 'Business Analyst', 13),
	(78, 'Xây dựng các tính năng frontend cho ứng dụng web.', 'Frontend Developer', 13),
	(79, 'Quản lý cơ sở dữ liệu và phát triển API.', 'Backend Developer', 13),
	(80, 'Lập kế hoạch và triển khai các sản phẩm công nghệ.', 'Product Manager', 13),
	(81, 'Xây dựng và duy trì hệ thống tự động hóa cho quy trình phát triển.', 'DevOps Engineer', 14),
	(82, 'Thiết kế các hệ thống phần mềm phức tạp và các giải pháp kiến trúc.', 'Software Architect', 14),
	(83, 'Chịu trách nhiệm cho chiến lược marketing và truyền thông.', 'Marketing Manager', 14),
	(84, 'Phân tích dữ liệu và cung cấp các báo cáo chi tiết cho các bộ phận.', 'Data Scientist', 14),
	(85, 'Quản lý hệ thống và cơ sở hạ tầng công ty.', 'System Engineer', 14),
	(86, 'Lập kế hoạch và theo dõi tiến độ các dự án công nghệ.', 'Project Manager', 14),
	(87, 'Kiểm tra và bảo đảm chất lượng phần mềm trước khi triển khai.', 'QA Specialist', 14),
	(88, 'Lập trình và phát triển phần mềm cho các ứng dụng mới.', 'Software Engineer', 15),
	(89, 'Quản lý và bảo trì các hệ thống mạng và cơ sở dữ liệu.', 'System Administrator', 15),
	(90, 'Phát triển giao diện người dùng cho các sản phẩm phần mềm.', 'Frontend Developer', 15),
	(91, 'Phát triển các ứng dụng di động cho cả Android và iOS.', 'Mobile App Developer', 15),
	(92, 'Xây dựng và triển khai các giải pháp đám mây.', 'Cloud Engineer', 15),
	(93, 'Chịu trách nhiệm cho việc phát triển và quản lý các sản phẩm công nghệ.', 'Product Owner', 15),
	(94, 'Quản lý và tối ưu hóa các hệ thống dữ liệu lớn.', 'Data Engineer', 15),
	(95, 'Phát triển và duy trì các ứng dụng web phức tạp.', 'Web Developer', 16),
	(96, 'Xây dựng các API và hệ thống backend.', 'Backend Developer', 16),
	(97, 'Quản lý cơ sở dữ liệu và các hệ thống lưu trữ dữ liệu.', 'Database Administrator', 16),
	(98, 'Phát triển các phần mềm tùy chỉnh theo yêu cầu của khách hàng.', 'Software Engineer', 16),
	(99, 'Tạo ra các giao diện người dùng đẹp mắt và dễ sử dụng.', 'UI/UX Designer', 16),
	(100, 'Kiểm tra các sản phẩm phần mềm và báo cáo lỗi.', 'QA Tester', 16),
	(101, 'Triển khai các chiến lược marketing kỹ thuật số.', 'Digital Marketing Specialist', 16),
	(102, 'Lên kế hoạch và giám sát quá trình phát triển sản phẩm.', 'Product Manager', 17),
	(103, 'Phát triển các tính năng giao diện người dùng cho ứng dụng.', 'Frontend Engineer', 17),
	(104, 'Phát triển các dịch vụ phía máy chủ và cơ sở dữ liệu.', 'Backend Engineer', 17),
	(105, 'Phân tích nhu cầu kinh doanh và cung cấp giải pháp kỹ thuật.', 'Business Analyst', 17),
	(106, 'Phát triển phần mềm cho các ứng dụng doanh nghiệp.', 'Software Engineer', 17),
	(107, 'Phát triển ứng dụng di động cho các nền tảng Android và iOS.', 'Mobile Developer', 17),
	(108, 'Thiết kế và triển khai các giải pháp đám mây cho doanh nghiệp.', 'Cloud Architect', 17),
	(109, 'Phát triển giao diện người dùng đẹp mắt cho các ứng dụng web.', 'Frontend Developer', 18),
	(110, 'Xây dựng và tối ưu hóa các hệ thống API.', 'Backend Developer', 18),
	(111, 'Kiểm tra và đảm bảo chất lượng phần mềm trước khi ra mắt.', 'QA Engineer', 18),
	(112, 'Thiết kế các sản phẩm đáp ứng nhu cầu của khách hàng.', 'Product Designer', 18),
	(113, 'Tìm kiếm cơ hội kinh doanh và mở rộng thị trường.', 'Business Development Manager', 18),
	(114, 'Xây dựng và duy trì các hệ thống CI/CD cho quy trình phát triển.', 'DevOps Engineer', 18),
	(115, 'Xây dựng kiến trúc hệ thống phần mềm cho các ứng dụng lớn.', 'Software Architect', 18);

-- Dumping structure for table works.job_skill
CREATE TABLE IF NOT EXISTS `job_skill` (
  `more_infos` varchar(1000) DEFAULT NULL,
  `skill_level` enum('FRESHER','INTERN','JUNIOR','LEADER','MIDDLE','SENIOR') NOT NULL,
  `job_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`job_id`,`skill_id`),
  KEY `FKj33qbbf3vk1lvhqpcosnh54u1` (`skill_id`),
  CONSTRAINT `FK9ix4wg520ii2gu2felxdhdup6` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `FKj33qbbf3vk1lvhqpcosnh54u1` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table works.job_skill: ~0 rows (approximately)

-- Dumping structure for table works.skill
CREATE TABLE IF NOT EXISTS `skill` (
  `skill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `skill_description` varchar(255) DEFAULT NULL,
  `skill_name` varchar(255) DEFAULT NULL,
  `type` enum('SOFT_SKILL','TECHNICAL_SKILL','UNSPECIFIC') DEFAULT NULL,
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table works.skill: ~52 rows (approximately)
INSERT INTO `skill` (`skill_id`, `skill_description`, `skill_name`, `type`) VALUES
	(1, 'Ngôn ngữ lập trình dùng cho phát triển front-end và back-end', 'JavaScript', 'TECHNICAL_SKILL'),
	(2, 'Ngôn ngữ đánh dấu dùng để tạo ra các trang web.', 'HTML', 'TECHNICAL_SKILL'),
	(3, 'Ngôn ngữ định kiểu dùng để mô tả cách thức trình bày tài liệu.', 'CSS', 'TECHNICAL_SKILL'),
	(4, 'Thư viện JavaScript để xây dựng giao diện người dùng.', 'React', 'TECHNICAL_SKILL'),
	(5, 'Môi trường chạy JavaScript dùng cho lập trình phía máy chủ.', 'Node.js', 'TECHNICAL_SKILL'),
	(6, 'Framework ứng dụng web cho Node.js.', 'Express.js', 'TECHNICAL_SKILL'),
	(7, 'Cơ sở dữ liệu NoSQL lưu trữ dữ liệu dưới định dạng giống JSON.', 'MongoDB', 'TECHNICAL_SKILL'),
	(8, 'Ngôn ngữ truy vấn có cấu trúc dùng cho quản lý cơ sở dữ liệu quan hệ.', 'SQL', 'TECHNICAL_SKILL'),
	(9, 'Hệ thống kiểm soát phiên bản để quản lý sự thay đổi của mã nguồn.', 'Git', 'TECHNICAL_SKILL'),
	(10, 'Dịch vụ lưu trữ kho Git trên nền tảng web.', 'GitHub', 'TECHNICAL_SKILL'),
	(11, 'Ngôn ngữ lập trình bậc cao dùng cho phát triển phần mềm đa năng.', 'Python', 'TECHNICAL_SKILL'),
	(12, 'Framework web dành cho phát triển ứng dụng web bằng Python.', 'Django', 'TECHNICAL_SKILL'),
	(13, 'Framework ứng dụng web viết bằng ngôn ngữ Ruby.', 'Ruby on Rails', 'TECHNICAL_SKILL'),
	(14, 'Ngôn ngữ lập trình hướng đối tượng dùng để xây dựng các ứng dụng.', 'Java', 'TECHNICAL_SKILL'),
	(15, 'Ngôn ngữ lập trình kịch bản phía máy chủ dành cho phát triển web.', 'PHP', 'TECHNICAL_SKILL'),
	(16, 'Framework web dùng để xây dựng các ứng dụng trang đơn động.', 'Angular', 'TECHNICAL_SKILL'),
	(17, 'Framework JavaScript để phát triển giao diện người dùng.', 'Vue.js', 'TECHNICAL_SKILL'),
	(18, 'Ngôn ngữ lập trình siêu ngữ nghĩa của JavaScript, hỗ trợ kiểu dữ liệu tĩnh.', 'TypeScript', 'TECHNICAL_SKILL'),
	(19, 'Tiền xử lý CSS giúp nâng cao khả năng tái sử dụng mã và dễ dàng duy trì.', 'SASS', 'TECHNICAL_SKILL'),
	(20, 'Công cụ đóng gói module cho JavaScript và tài nguyên web.', 'Webpack', 'TECHNICAL_SKILL'),
	(21, 'Framework kiểm thử JavaScript để kiểm tra mã nguồn.', 'Jest', 'TECHNICAL_SKILL'),
	(22, 'Công cụ để đóng gói các ứng dụng và phụ thuộc vào các container.', 'Docker', 'TECHNICAL_SKILL'),
	(23, 'Hệ thống tự động hóa triển khai, mở rộng và quản lý các container.', 'Kubernetes', 'TECHNICAL_SKILL'),
	(24, 'Dịch vụ điện toán đám mây cung cấp cơ sở hạ tầng và dịch vụ đám mây.', 'AWS', 'TECHNICAL_SKILL'),
	(25, 'Dịch vụ đám mây của Microsoft hỗ trợ phát triển và triển khai ứng dụng.', 'Azure', 'TECHNICAL_SKILL'),
	(26, 'Nền tảng đám mây của Google cung cấp các dịch vụ tính toán, lưu trữ và phân tích.', 'Google Cloud', 'TECHNICAL_SKILL'),
	(27, 'Giao diện lập trình ứng dụng, cho phép các ứng dụng giao tiếp với nhau.', 'API', 'TECHNICAL_SKILL'),
	(28, 'Kiến trúc API sử dụng HTTP để giao tiếp và tuân theo nguyên lý REST.', 'RESTful API', 'TECHNICAL_SKILL'),
	(29, 'Ngôn ngữ truy vấn API cho phép người dùng yêu cầu dữ liệu chính xác cần thiết.', 'GraphQL', 'TECHNICAL_SKILL'),
	(30, 'Giao thức ủy quyền dùng để bảo mật các API và ứng dụng.', 'OAuth', 'TECHNICAL_SKILL'),
	(31, 'Công nghệ mã hóa JSON Web Token dùng để xác thực và trao đổi thông tin bảo mật.', 'JWT', 'TECHNICAL_SKILL'),
	(32, 'Phương pháp quản lý dự án linh hoạt, tập trung vào việc cải tiến liên tục.', 'Agile', 'SOFT_SKILL'),
	(33, 'Mô hình quản lý dự án Agile, chia công việc thành các sprint nhỏ.', 'Scrum', 'SOFT_SKILL'),
	(34, 'Phương pháp quản lý công việc theo bảng Kanban, giúp tối ưu hóa quy trình làm việc.', 'Kanban', 'SOFT_SKILL'),
	(35, 'Kỹ năng giải quyết vấn đề hiệu quả trong công việc.', 'Problem-solving', 'SOFT_SKILL'),
	(36, 'Kỹ năng làm việc nhóm hiệu quả, hợp tác và giao tiếp với đồng nghiệp.', 'Teamwork', 'SOFT_SKILL'),
	(37, 'Kỹ năng giao tiếp rõ ràng và hiệu quả trong môi trường làm việc.', 'Communication', 'SOFT_SKILL'),
	(38, 'Kỹ năng tư duy phản biện để đưa ra các quyết định đúng đắn trong công việc.', 'Critical thinking', 'SOFT_SKILL'),
	(39, 'Kỹ năng quản lý thời gian để hoàn thành công việc đúng hạn.', 'Time management', 'SOFT_SKILL'),
	(40, 'Kỹ năng lãnh đạo, giúp nhóm làm việc hiệu quả và đạt được mục tiêu.', 'Leadership', 'SOFT_SKILL'),
	(41, 'Kỹ năng đưa ra quyết định đúng đắn trong các tình huống công việc.', 'Decision making', 'SOFT_SKILL'),
	(42, 'Kỹ năng chăm sóc khách hàng và giải quyết vấn đề của khách hàng.', 'Customer service', 'SOFT_SKILL'),
	(43, 'Kỹ năng giải quyết xung đột trong nhóm hoặc giữa các thành viên.', 'Conflict resolution', 'SOFT_SKILL'),
	(44, 'Kỹ năng đàm phán để đạt được thỏa thuận giữa các bên.', 'Negotiation', 'SOFT_SKILL'),
	(45, 'Kỹ năng thích nghi với môi trường thay đổi nhanh chóng.', 'Adaptability', 'SOFT_SKILL'),
	(46, 'Kỹ năng sáng tạo để tìm ra giải pháp mới và cải tiến quy trình.', 'Creativity', 'SOFT_SKILL'),
	(47, 'Kỹ năng thuyết trình và trình bày ý tưởng rõ ràng và thuyết phục.', 'Presentation', 'SOFT_SKILL'),
	(48, 'Kỹ năng quản lý và giải quyết các xung đột trong công việc.', 'Conflict management', 'SOFT_SKILL'),
	(49, 'Kỹ năng đồng cảm với người khác, hiểu và chia sẻ cảm xúc của họ.', 'Empathy', 'SOFT_SKILL'),
	(50, 'Khả năng tự động viên bản thân để đạt được mục tiêu.', 'Self-motivation', 'SOFT_SKILL'),
	(51, 'Khả năng làm nhiều công việc cùng lúc một cách hiệu quả.', 'Multitasking', 'SOFT_SKILL'),
	(52, 'Kỹ năng chú ý đến từng chi tiết nhỏ trong công việc.', 'Attention to detail', 'SOFT_SKILL');

-- Dumping structure for table works.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` enum('ADMIN','CANDIDATE','COMPANY') NOT NULL,
  `candidate_id` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKqb6fpgwjnm36j8oaf44g9nwf5` (`candidate_id`),
  UNIQUE KEY `UKcamlvklbluxpcvlps0s8vs8wp` (`company_id`),
  CONSTRAINT `FK6vsk58fajqrd15ci6fu2u60vm` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`),
  CONSTRAINT `FKbwv4uspmyi7xqjwcrgxow361t` FOREIGN KEY (`company_id`) REFERENCES `company` (`comp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table works.users: ~27 rows (approximately)
INSERT INTO `users` (`id`, `email`, `password`, `role`, `candidate_id`, `company_id`) VALUES
	(5, 'sang@gmail.com', '$2a$10$u5GK0ST0iIkLAKeKY9Ti9.b.Tu8b0TJUfLpqRMZwvUxOdrvdB.VvG', 'ADMIN', NULL, NULL),
	(6, 'sang1@gmail.com', '$2a$10$s8lu/wzCBVoUtzfTVTV7Zex2O.53bpi9xadOXqnG5gvX9ve7wkoUK', 'CANDIDATE', 5, NULL),
	(7, 'info@fpt.com.vn', '$2a$10$ZMX2HmkA02e3DqA6ukm4euulvnV/VOm4WNmfVuEdHkDsqbKYSks2S', 'COMPANY', NULL, 3),
	(8, 'contact@vng.com.vn', '$2a$10$SSB5ORApv2CRZgiy4fpqm.VA3K2NgWx8dInSWlxNQd9z6DUH4aYv2', 'COMPANY', NULL, 4),
	(9, 'info@tinhvan.com', '$2a$10$50U1EMB7zyG3gqYTjfFSLuMj4Vqw/9LmNbVnh23w6hqwDQkPJcWSe', 'COMPANY', NULL, 5),
	(10, 'contact@vnpt.com.vn', '$2a$10$wc688QPfUcgg0c.SFp9zT.lMGsEGpR2zs6Q5i/Askwip/8gUZtQ..', 'COMPANY', NULL, 6),
	(11, 'info@bidv.com.vn', '$2a$10$3Mme1xBJVR3AZvGEwEaT6Owbo24Q8qSUJCKuQn0q9AzICsAa4dpYG', 'COMPANY', NULL, 7),
	(12, 'contact@tigo.com.vn', '$2a$10$mG8x3AAy1kvcW2N3XEmbw.OaSu7r6Oa8BJw7zFO9g4gnt8wdmzVsO', 'COMPANY', NULL, 8),
	(13, 'support@hanoitech.com.vn', '$2a$10$YqpNnAUxTuvTjZIFhycBjuwjW/VV6RGBJd2m3PIj2MY8yX5n1is/m', 'COMPANY', NULL, 9),
	(14, 'info@momo.vn', '$2a$10$zHxBurwqVNvCGO.dJW.kv.KHN3w.XEmwrV.cM1NiJMpuX.9HFdaqO', 'COMPANY', NULL, 10),
	(15, 'contact@tavico.vn', '$2a$10$9SJf7MD/lTN1gwnlMZtVNuO45t13nfTtxxtl8gOaZlVhQAAaY0qkK', 'COMPANY', NULL, 11),
	(16, 'info@alibaba.vn', '$2a$10$9hraxGzIZxgCMX8h36/nOuXoOAZyIkUqSQ.tR88d7EZPgNDt/0RUS', 'COMPANY', NULL, 12),
	(17, 'info@fsoft.com.vn', '$2a$10$ldB5T4Qe7EHHQZ4rtACDyOLowBvvl3Doh80W340hk3oKV7/pVKujG', 'COMPANY', NULL, 13),
	(18, 'contact@eivietnam.vn', '$2a$10$8hKou3gutATwK0OXafdhgu3WcNj/OI5XWvatzfWcf2VQ5v9A/wjYe', 'COMPANY', NULL, 14),
	(19, 'hrnina.nina@gmail.com', '$2a$10$uq188xuxrFmYsBCZvElUTOjlCJuWgcOSMt.e3mXuudx2Sasw.lvdq', 'COMPANY', NULL, 15),
	(20, 'info@techcombank.vn', '$2a$10$Na9bBXjupJZI.uEFd/s9kuQlJ3WYlL2hQjdetBv1PdJ/zvheKJnHq', 'COMPANY', NULL, 16),
	(21, 'info@vnsoft.com.vn', '$2a$10$.uOTLct.bxJz7BGGmZNvhes6Fmd1YRasJNvJPmXOP.ZitsqB2oJTu', 'COMPANY', NULL, 17),
	(22, 'support@thegioididong.com', '$2a$10$sKlM0jGbXGLqS2s4GDFg3uv4xJv7ddR0LcCWN8J425D3R0HvpzsNy', 'COMPANY', NULL, 18),
	(23, 'teo@gmail.com', '$2a$10$7AxG7ookajF5yPkxrlfClebPB/P7rJ0NXIJGtrpGPCy1HsjG8eTLe', 'CANDIDATE', 6, NULL),
	(24, 'det@gmail.com', '$2a$10$k53ORvBkkQeX8V.fIlCzy.SWmjBN4ETjeVO6EUZ7hzCSaZ4bRzk8.', 'CANDIDATE', 7, NULL),
	(25, 'ti@gmail.com', '$2a$10$HwGWb05LJcsF6ipWsS0H.OLZrJxJ0QW4JJYQDxNRVBrW2kdoCYF8u', 'CANDIDATE', 8, NULL),
	(26, 'hoa@gmail.com', '$2a$10$kmrQ8tT/2vQukQE5/Ko2hOZDv8xKk16b0.pKS0Vn56Hsd7LgY7DeC', 'CANDIDATE', 9, NULL),
	(27, 'blabla@gmail.com', '$2a$10$XYHh6a8otIa.2PmaePKEveUegmrABHURs2OkbyeMFSkMt.iOC0yBy', 'CANDIDATE', 10, NULL),
	(28, 'giau@gmail.com', '$2a$10$OjPCP9esTNaTNaERcZXqDeFrVD0fNXzdWsNdlByt5SBiXwcqm9TTu', 'CANDIDATE', 11, NULL),
	(29, 'hiep@gmail.com', '$2a$10$Q3XwpAVlpnZpzg04ADT/luUbZ7.0YclDmJCUTn9G5NtCQwhfMdpi6', 'CANDIDATE', 12, NULL),
	(30, 'loc@gmail.com', '$2a$10$ntplE8S3gdm6kVA2UK9ITeE7V.xNqU6SAlJbTLfW2LBkPtB29GLsS', 'CANDIDATE', 13, NULL),
	(31, 'anh@gmail.com', '$2a$10$7Yw43myL49uXYz.XKOGKJefmmGhxM5njpUazIdmAFeOJhrbj5Q9Um', 'CANDIDATE', 14, NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
