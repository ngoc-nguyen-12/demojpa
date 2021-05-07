Tìm hiểu về các annotations:

1.  `@Entity`

- Một entity tương ứng với một table dưới database vì thế nó phải chứa đầy đủ các thông tin như tên bảng, khoá chính, khoá ngoại, các cột trong bảng.
- Mọi entity class đều phải chứa một @Entity annotation, Hibernate sẽ dựa vào thông tin này để ánh xạ xuống một bảng dưới database. Một entity class phải có tối thiểu no-agrs constructor và một khoá chính.
- Mặc định tên của entity sẽ trùng với tên của entity class, chúng ta có thể thay đổi thông qua thuộc tính name trong @Entity annotation.

2.  `@Table`

- Một table trong database có thể có nhiều ràng buộc unique (duy nhất). Chúng ta có thể sử dụng @Table để mô tả các ràng buộc này.
- @Table cho phép chú thích tên bảng thông qua thuộc tính name (thuộc tính này không bắt buộc). Nếu không chỉ rõ tên bảng trong phần tử name, Hibernate sẽ dựa vào phần tử name của @Entity sau đó mới tới tên của class.

3. `@Data`

- @Data sẽ có tác dụng generate ra Constructor rỗng hoặc có tham số theo yêu cầu, toàn bộ Get/Set, hàm equals, hashCode, toString()
- Khi bạn đã đánh dấu 1 class là @Data, thì tại bất cứ đâu trong project. Khi sử dụng tới class này, nó sẽ tự có các function đã generate mà không cần code thêm bất kì dòng nào.

4. `@NoArgsConstructor`

- Hàm khởi tạo rỗng

5. `@AllArgsConstructor`

- Hàm khởi tạo chứa tất cả thuộc tính

6. `@Id`

- @Id được sử dụng để mô tả đây là Id (Identity) của Entity, nó tương đương với cột đó là khóa chính (Primary Key) của table trong database.
- Khóa chính có thể là một trường duy nhất hoặc kết hợp nhiều trường tùy thuộc vào cấu trúc bảng trong database.

7. `@GeneratedValue`

- @GeneratedValue được sử dụng để Hibernate tự động tạo ra giá trị và gán vào cho một cột trong trường hợp insert mới một Entity vào database. Nó có thể gắn trên cột Id hoặc một cột nào đó.
- Annotation này cũng được sử dụng cùng với @Generator.
- Theo mặc định, chú thích @Id sẽ tự động xác định chiến lược tạo primary key, nhưng có thể ghi đè bằng cách áp dụng chú thích @GeneratedValue có hai tham số strategy và generator.
- GenerationType.IDENTITY: cột có kiểu IDENTITY chỉ được hỗ trợ bởi một vài loại cơ sở dữ liệu, không phải là tất cả, ví dụ MySQL, DB2, SQL Server, Sybase và PostgreSQL. Oracle không hỗ trợ cột kiểu này.

8. `@NaturalId`

- NaturalId: truong tự nhiên cho cac thuộc tính tự nhiên.
- NaturalId xác định bản ghi nhưng phức tạp hơn id khóa chính. Thuộc tính id là một khóa thay thế và được tạo bởi Hibernate.
- Các NaturalId là bất biến theo mặc định và bạn không nên cung cấp các phương thức setter cho chúng.

9. `@ManyToOne`

- Mô tả quan hệ N-1 (nhiều - một) trong database.
- Hibernate có các công cụ cho phép tạo ra các lớp Entity từ các bảng trong Database và Hibernate cũng cho phép tạo ra bảng từ các lớp Entity, bao gồm cả giàng buộc giữa các bảng (Foreign Key). Annotation @ForeignKey cho phép chỉ định rõ tên Foreign Key sẽ được tạo ra.

10. `@OneToMany`

- Mô tả quan hệ 1-N (một - nhiều) trong database.
- Đảo ngược của OneToMany là ManyToOne.

11. `cascade = CascadeType.ALL`

- Cascade là một tính năng giúp quản lý trạng thái của các đối tượng trong một mối quan hệ một cách tự động.
- Ví dụ: Mối quan hệ giữa company và employee là 1-N (một company chứa nhiều employee). Khi xóa hoặc update id của một row trong table company sẽ có các trường hợp sau:
  - Trường hợp 1: chưa có employee nào tham chiếu tới company đó (company đó chưa có employee nào) -> xóa / update bình thường.
  - Trường hợp 2: đã có employee tham chiếu tới company đó (company đó đã có employee):
    a) Mặc định sẽ không có sửa, update vì nó sẽ ảnh hưởng tới các employee đang tham chiếu tới. (ON UPDATE/DELETE = NO ACTION, RESTRICT)
    b) set company_id của các employee đang tham chiếu tới bằng null (ON UPDATE/ON DELETE = SET NULL) (Tạm hiểu là công ty bị xóa, thay đổi thì các nhân viên sẽ không thuộc công ty nào cả)
    c) set company_id của các employee bằng id của company sau khi update (cho trường hợp update) . Và xóa tất cả các employee có company_id tham chiếu tới company bị xóa (ON UPDATE/ON DELETE = CASCADE)
    Cascade chính là tính năng trong trường hợp c) Khi một bản ghi thay đổi thì nó sẽ tự động update các bản ghi đang tham chiếu tới nó.
- Ta sẽ sử dụng cascade trong các trường hợp dữ liệu tham chiếu ít, các dữ liệu tham chiếu chỉ có ý nghĩa khi gắn liền với đối tượng tham chiếu.

12. `orphanRemoval = true`

- orphanRemoval là một thuộc tính bên trong annotation @OneToMany và @OneToOne.
- orphanRemoval là một đặc tả trong ORM. Nó đánh dấu rằng các phần tử con sẽ bị xóa khi bạn xóa nó khỏi collection của phần tử cha.

13. `fetch = FetchType.LAZY`

- LAZY nói với Hibernate rằng, hãy tải dữ liệu một cách lười biếng, nghĩa là chỉ tải khi được gọi (khi cần thiết).

14. `fetch = FetchType.EAGER`

- EAGER nói với Hibernate rằng, hãy truy vấn toàn bộ các cột của bảng liên quan.

15. `@JoinColumn(name = "user_id")`

- Dặc tả cột Foreign Key ở Entity phía Many. Thuộc tính name đặt tên cột.
- Trong các trường hợp khi chúng ta muốn tạo nhiều cột nối, chúng ta có thể sử dụng @JoinColumns (kết nối các cột trong databases).

16. `@PrePersist`

- JPA callback method là các method lắng nghe các sự kiện như save, update, remove các đối tượng trên database.
- Các annotation đánh dấu callback method: JPA cung cấp các annotation được sử dụng trong các class entity (được đánh dấu @Entity) để lắng nghe các sự kiện save, update, delete của chính các entity đó.
- @PrePersist được sử dụng để định cấu hình lệnh gọi lại cho các sự kiện tồn tại trước (chèn trước) của thực thể. Nói cách khác, nó được sử dụng để chú thích các phương thức của mô hình để chỉ ra rằng phương thức nên được gọi trước khi thực thể được chèn (tồn tại) vào cơ sở dữ liệu.

1.  `@PreUpdate`

- @PreUpdate được sử dụng để định cấu hình lệnh gọi lại trước khi update cho entity, tức là nó được sử dụng để chú thích các phương thức trong mô hình để chỉ ra một hoạt động cần được kích hoạt trước khi một entity được cập nhật trong cơ sở dữ liệu.

18. `@JoinTable`

- Kết nối các bảng trong databases

