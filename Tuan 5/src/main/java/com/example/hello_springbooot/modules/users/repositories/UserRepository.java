package com.example.hello_springbooot.modules.users.repositories;
import com.example.hello_springbooot.modules.users.entity.User;
import com.example.hello_springbooot.repositories.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*JpaRepository là một interface trong Spring Data JPA giúp thao tác với cơ sở dữ liệu dễ dàng hơn.
Nó cung cấp sẵn các phương thức CRUD (Create, Read, Update, Delete) mà không cần tự viết SQL.
Không cần viết SQL, bạn đã có sẵn các hàm CRUD:
        save(T entity)	Lưu hoặc cập nhật dữ liệu
        findById(ID id)	Tìm bản ghi theo ID
        findAll()	Lấy tất cả bản ghi
        deleteById(ID id)	Xóa bản ghi theo ID
        delete(T entity)	Xóa một entity cụ thể
        existsById(ID id)	Kiểm tra bản ghi có tồn tại không */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String username);
}
