# Hệ thống Quản lý Đăng ký Leo núi - Mountain Hiking Challenge Registration

## 1. Giới thiệu tổng quan

### Background - Bối cảnh dự án
Trường Đại học FPT đã phát động phong trào "Vì một thế hệ trí thức dám nghĩ và dám làm" được tài trợ bởi Viettel và VNPT. Để hỗ trợ cho sáng kiến này, một chương trình cần được phát triển để quản lý thông tin đăng ký của các sinh viên Đại học FPT.

Chương trình này cho phép sinh viên từ tất cả các cơ sở trên toàn quốc đăng ký và lựa chọn các ngọn núi để chinh phục (danh sách các ngọn núi được cung cấp trong file MountainList.csv đính kèm).

### Mục tiêu dự án
Dự án này là một ứng dụng console được viết bằng Java để quản lý danh sách sinh viên đăng ký tham gia các tour leo núi. Ứng dụng được thiết kế theo phương pháp Lập trình Hướng đối tượng (OOP) với các nguyên tắc: trừu tượng hóa (abstraction), đa hình (polymorphism), đóng gói (encapsulation), và kế thừa (inheritance).

## 2. Chức năng chính của hệ thống

### Hệ thống Authentication & Authorization
- **Đăng ký tài khoản**: Sinh viên có thể tạo tài khoản mới với Student ID
- **Đăng nhập**: Xác thực người dùng với username/password
- **Phân quyền**: Hỗ trợ 2 loại tài khoản (Admin và User)

### Chức năng dành cho Admin (10 tính năng chính):
1. **Add a new Student** - Thêm thông tin đăng ký sinh viên mới
2. **Display all Students** - Xem tất cả sinh viên đã đăng ký
3. **Display all Mountains** - Xem tất cả các núi có thể leo
4. **Find Student by ID** - Tìm sinh viên dựa trên ID
5. **Update Student by ID** - Chỉnh sửa thông tin đăng ký đã có
6. **Delete Student by ID** - Xóa bản ghi đăng ký của sinh viên
7. **Find Student by name** - Tìm sinh viên dựa trên tên
8. **Filter student by Campus** - Hiển thị đăng ký theo từng campus
9. **Show Statistics** - Tạo báo cáo thống kê
10. **Logout** - Đăng xuất khỏi hệ thống

### Chức năng dành cho User (Sinh viên):
1. **View My Information** - Xem thông tin cá nhân
2. **Register for Hiking** - Đăng ký tham gia leo núi (nếu chưa đăng ký)
3. **Update My Information** - Cập nhật thông tin cá nhân
4. **View Mountain List** - Xem danh sách các núi có thể leo
5. **Logout** - Đăng xuất khỏi hệ thống

## 3. Mô hình và Kiến trúc dự án

Dự án được xây dựng dựa trên kiến trúc phân lớp, gần giống với mô hình MVC (Model-View-Controller) để tách biệt các thành phần, giúp mã nguồn dễ quản lý, bảo trì và mở rộng.

```
src
├── controller/  # Điều phối luồng dữ liệu và xử lý input
│   ├── AuthController.java
│   ├── MainController.java
│   ├── MountainController.java
│   ├── StatisticController.java
│   └── StudentController.java
├── model/       # Các đối tượng dữ liệu (Entities)
│   ├── Mountain.java
│   ├── Role.java
│   ├── Statistic.java
│   ├── Student.java
│   └── User.java
├── repository/  # Lớp truy cập dữ liệu (Đọc/ghi file CSV)
│   ├── MountainRepository.java
│   ├── StudentRepository.java
│   └── UserRepository.java
├── service/     # Chứa logic nghiệp vụ chính
│   ├── AuthService.java
│   ├── MountainService.java
│   ├── StatisticsService.java
│   └── StudentService.java
├── utils/       # Các lớp tiện ích
│   ├── Acceptable.java
│   └── Inputer.java
└── view/        # Hiển thị giao diện và dữ liệu cho người dùng
    └── ConsoleView.java
```

### Mô tả các thành phần:

- **`model`**: Định nghĩa các đối tượng cốt lõi của ứng dụng
  - `User.java`: Biểu diễn thông tin người dùng (tài khoản đăng nhập)
  - `Student.java`: Biểu diễn thông tin một sinh viên đăng ký leo núi
  - `Mountain.java`: Biểu diễn thông tin một ngọn núi
  - `Role.java`: Enum định nghĩa vai trò người dùng (ADMIN, USER)
  - `Statistic.java`: Biểu diễn dữ liệu thống kê theo núi

- **`repository`**: Tầng truy cập dữ liệu (Data Access Layer)
  - `UserRepository.java`: Đọc và ghi thông tin người dùng từ/vào file CSV (`users.csv`)
  - `StudentRepository.java`: Đọc và ghi danh sách sinh viên từ/vào file CSV (`students.csv`)
  - `MountainRepository.java`: Đọc danh sách các ngọn núi từ `MountainList.csv`

- **`service`**: Chứa các logic nghiệp vụ phức tạp và điều phối dữ liệu giữa `controller` và `repository`
  - `AuthService.java`: Xử lý logic xác thực người dùng (đăng ký, đăng nhập, kiểm tra vai trò, quản lý session)
  - `StudentService.java`: Quản lý các thao tác nghiệp vụ liên quan đến sinh viên (CRUD operations, validation, tính học phí)
  - `MountainService.java`: Quản lý các thao tác nghiệp vụ liên quan đến núi (hiển thị danh sách, validation mã núi)
  - `StatisticsService.java`: Thực hiện logic tính toán, tổng hợp thống kê từ dữ liệu sinh viên và núi

- **`controller`**: Nhận yêu cầu từ người dùng và điều phối các service
  - `AuthController.java`: Xử lý các yêu cầu liên quan đến xác thực (đăng ký tài khoản cho sinh viên)
  - `MainController.java`: Điều phối luồng chính của ứng dụng, quản lý menu và phân quyền
  - `MountainController.java`: Xử lý các yêu cầu liên quan đến hiển thị thông tin núi
  - `StudentController.java`: Xử lý các yêu cầu CRUD cho sinh viên, cập nhật profile cá nhân
  - `StatisticController.java`: Xử lý các yêu cầu liên quan đến báo cáo thống kê

- **`view`**: Chịu trách nhiệm hiển thị giao diện người dùng
  - `ConsoleView.java`: Hiển thị menu, bảng dữ liệu và thông báo trên console

- **`utils`**: Cung cấp các hàm tiện ích
  - `Inputer.java`: Xử lý việc nhập liệu an toàn từ người dùng với validation
  - `Acceptable.java`: Định nghĩa các regex patterns cho validation dữ liệu

## 4. Quy tắc xác thực dữ liệu

### Thông tin sinh viên cần nhập:

1. **Mã sinh viên**: Chuỗi 8 ký tự duy nhất
   - 2 ký tự đầu: mã campus (SE, HE, DE, QE, CE cho 5 cơ sở của FPTU)
   - 6 ký tự tiếp theo: số
   - Phải là duy nhất trong hệ thống

2. **Họ tên**: Chuỗi không rỗng, độ dài từ 2-20 ký tự

3. **Số điện thoại**: 10 chữ số, thuộc nhà mạng Việt Nam

4. **Email**: Định dạng email hợp lệ (ví dụ: example@domain.com)

5. **Mã núi**: Mã hợp lệ từ danh sách trong file MountainList.csv

6. **Học phí**: 
   - Mặc định: 6,000,000 VND
   - Giảm giá 35% cho số điện thoại thuộc mạng Viettel hoặc VNPT (do tài trợ)

### Mã Campus và địa điểm:
- **CE**: Cần Thơ
- **DE**: Đà Nẵng  
- **HE**: Hà Nội
- **SE**: TP. Hồ Chí Minh
- **QE**: Quy Nhon

## 5. Luồng hoạt động chính

### a. Xác thực người dùng (Authentication)
1. **Khởi động**: Ứng dụng yêu cầu người dùng đăng nhập.
2. **Nhập thông tin**: Người dùng nhập tên đăng nhập và mật khẩu.
3. **Xác thực**: `AuthController` sử dụng `AuthService` để kiểm tra thông tin đăng nhập với dữ liệu từ `UserRepository`.
4. **Phân quyền**: Nếu đăng nhập thành công, hệ thống sẽ xác định vai trò của người dùng (ví dụ: Admin, User) để hiển thị các chức năng phù hợp.
5. **Truy cập chức năng**: Sau khi xác thực thành công, người dùng có thể truy cập các chức năng chính của hệ thống.

### b. Quản lý Sinh viên (CRUD) (Sau khi đăng nhập)
1. **Khởi động**: Load danh sách từ `students.csv` vào bộ nhớ.
2. **Thêm mới**: Nhập và xác thực thông tin, tạo đối tượng Student mới.
3. **Hiển thị**: Lấy danh sách từ service và hiển thị qua ConsoleView.
4. **Cập nhật/Xóa/Tìm kiếm**: Tìm kiếm theo ID hoặc tên, thực hiện thao tác.
5. **Kết thúc**: Lưu tất cả thay đổi về file `students.csv`.

### c. Chức năng Thống kê
1. **Thu thập dữ liệu**: Lấy danh sách sinh viên và núi từ repository.
2. **Xử lý nghiệp vụ**: 
   - Tạo Map lưu trữ Statistic với mountainCode làm khóa.
   - Duyệt danh sách sinh viên, cập nhật thống kê.
   - Tính toán số người tham gia và tổng học phí theo từng núi.
3. **Lọc và sắp xếp**:
   - Loại bỏ núi không có người đăng ký.
   - Sắp xếp giảm dần theo số người tham gia, sau đó theo tổng học phí.
4. **Hiển thị**: Định dạng và hiển thị kết quả thống kê.

## 6. Luồng hoạt động tổng thể (Overall Workflow)

Hệ thống hoạt động theo một luồng tuần tự, bắt đầu từ `Main.java` và điều hướng qua các thành phần chính:

1.  **Khởi động ứng dụng (`Main.java`)**:
    -   Tạo các instance của `ConsoleView`, `UserRepository`, `AuthService`, `AuthController`, `StudentRepository`, `MountainRepository`, `StudentService`, `MountainService`, `StatisticsService`, `StudentController`, `MountainController`, `StatisticController`, và `MainController`.
    -   Khởi tạo `MainController` với các controller và service cần thiết.
    -   Gọi phương thức `run()` của `MainController` để bắt đầu vòng lặp chính của ứng dụng.

2.  **Xác thực người dùng (Authentication Flow)**:
    -   Khi ứng dụng khởi động, `MainController` sẽ điều hướng đến `AuthController` để xử lý quá trình đăng nhập.
    -   `AuthController` yêu cầu `ConsoleView` hiển thị form đăng nhập và nhận thông tin từ người dùng.
    -   `AuthController` gọi `AuthService` để xác thực tên đăng nhập và mật khẩu với dữ liệu từ `UserRepository`.
    -   Nếu đăng nhập thành công, `AuthService` sẽ trả về thông tin người dùng và vai trò của họ. `MainController` sẽ tiếp tục với menu chính dựa trên quyền của người dùng.
    -   Nếu đăng nhập thất bại, người dùng sẽ được yêu cầu thử lại hoặc thoát.

3.  **Hiển thị Menu và Xử lý Input (`ConsoleView` & `MainController`)**:
    -   Sau khi đăng nhập thành công, `MainController` hiển thị menu chính thông qua `ConsoleView` (có thể tùy chỉnh theo vai trò người dùng).
    -   `ConsoleView` nhận lựa chọn từ người dùng thông qua `Inputer.java`.
    -   `MainController` điều phối yêu cầu đến các controller cụ thể (`StudentController`, `MountainController`, `StatisticController`) dựa trên lựa chọn của người dùng và quyền hạn.

4.  **Xử lý Yêu cầu (`Controller`s)**:
    -   Mỗi `Controller` chịu trách nhiệm xử lý một nhóm chức năng nhất định.
    -   `Controller` nhận dữ liệu đầu vào từ người dùng (thông qua `ConsoleView` và `Inputer`).
    -   `Controller` gọi các phương thức nghiệp vụ tương ứng trong `Service` layer.
    -   `Controller` nhận kết quả từ `Service` và yêu cầu `ConsoleView` hiển thị thông báo hoặc dữ liệu cho người dùng.

5.  **Thực thi Logic Nghiệp vụ (`Service`s)**:
    -   `Service` layer chứa toàn bộ logic nghiệp vụ của ứng dụng.
    -   `Service` tương tác với `Repository` layer để truy cập và thao tác với dữ liệu (đọc/ghi từ file CSV).
    -   `Service` thực hiện các quy tắc xác thực dữ liệu, tính toán, và xử lý phức tạp khác.
    -   `Service` trả về kết quả cho `Controller`.

6.  **Truy cập Dữ liệu (`Repository`s)**:
    -   `Repository` layer chịu trách nhiệm đọc và ghi dữ liệu từ các file CSV (`users.csv`, `students.csv`, `MountainList.csv`).
    -   `Repository` chuyển đổi dữ liệu từ định dạng CSV sang đối tượng Java (`User`, `Student`, `Mountain`) và ngược lại.
    -   `Repository` cung cấp các phương thức cơ bản để `Service` có thể thao tác với dữ liệu mà không cần quan tâm đến chi tiết lưu trữ.

7.  **Kết thúc ứng dụng**:
    -   Khi người dùng chọn chức năng thoát, `MainController` sẽ gọi các `Service` để lưu trữ dữ liệu hiện tại vào các file CSV tương ứng trước khi thoát chương trình.

## 7. Hướng dẫn chạy dự án

### Yêu cầu hệ thống:
- Java JDK 8 trở lên
- Terminal/Command Prompt

### Các bước thực hiện:

1. **Chuẩn bị files**:
   ```
   project-root/
   ├── src/
   ├── MountainList.csv
   ├── students.csv (sẽ được tạo tự động)
   └── users.csv (sẽ được tạo tự động)
   ```

2. **Biên dịch dự án**:
   ```bash
   # Mở terminal tại thư mục gốc của dự án
   javac -d out src/**/*.java src/*.java
   ```

3. **Chạy ứng dụng**:
   ```bash
   # Chạy từ thư mục out
   java -cp out Main
   ```

### Lưu ý quan trọng:
- Đảm bảo file `MountainList.csv` có mặt cùng cấp với thư mục `src`
- File `students.csv` và `users.csv` sẽ được tạo tự động khi lưu dữ liệu lần đầu
- Tài khoản Admin mặc định có thể được tạo sẵn trong `users.csv`

### Tài khoản mặc định:
- **Admin**: username/password có thể được cấu hình trong code
- **User**: Sinh viên tự đăng ký tài khoản với Student ID làm username

## 7. Tính năng nổi bật

### Ưu điểm của hệ thống:
- **Kiến trúc rõ ràng**: Áp dụng nguyên tắc SOLID và mô hình MVC
- **Hệ thống Authentication**: Đăng ký/đăng nhập với phân quyền Admin/User
- **Validation mạnh mẽ**: Kiểm tra dữ liệu đầu vào chặt chẽ với regex patterns
- **Tính toán học phí thông minh**: Tự động áp dụng giảm giá 35% cho nhà mạng Viettel/VNPT
- **Thống kê chi tiết**: Báo cáo theo ngọn núi với sắp xếp thông minh
- **Quản lý file CSV**: Lưu trữ dữ liệu persistent với auto-save
- **Giao diện thân thiện**: Menu console phân quyền rõ ràng

### Các chức năng đặc biệt:
- **Phân quyền động**: Menu khác nhau cho Admin và User
- **Self-service**: Sinh viên có thể tự đăng ký và quản lý thông tin cá nhân
- **Tìm kiếm linh hoạt**: Hỗ trợ tìm kiếm theo ID, tên, và lọc theo campus
- **Thống kê thông minh**: Chỉ hiển thị núi có người đăng ký, sắp xếp theo độ phổ biến
- **Data integrity**: Xác nhận trước khi xóa, validation duplicate data
- **Session management**: Quản lý trạng thái đăng nhập và logout an toàn

## 8. Kế hoạch phát triển

Hệ thống được thiết kế với khả năng mở rộng cao, có thể bổ sung:
- Giao diện GUI
- Kết nối cơ sở dữ liệu
- Tính năng báo cáo nâng cao
- Quản lý lịch trình tour
- Tích hợp thanh toán online

---

*Dự án được phát triển như một phần của chương trình LAB211 - Assignment J1.L.P0027, nhằm ứng dụng các nguyên tắc Lập trình Hướng đối tượng trong việc xây dựng hệ thống quản lý thực tế.*
