import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonArray = "[{\"name\":\"John\", \"age\":30}, {\"name\":\"Jane\", \"age\":25}]";

        try {
            // Sử dụng TypeReference để chỉ rõ kiểu List<User>
            List<User> users = objectMapper.readValue(jsonArray, new TypeReference<List<User>>() {});
            users.forEach(user -> System.out.println(user.getName() + " - " + user.getAge()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class User {
    private String name;
    private int age;

    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}


@OneToOne
@JoinColumn(name = "author_id")
private Author author;

@Entity
public class Author {
    ...
    // tạo thuộc tính book trong author
    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL)
    private Book book;


    // Constructor chỉ cần truyền vào tên tác giả, có thể sử dụng phương thức setBook() để thiết lập mối quan hệ với Book sau khi cả hai đối tượng đã được khởi tạo.
    public Author(String name) {
        this.name = name;
    }

    // thêm getter, setter cho book vào
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    ...
}


@Entity
public class Book {
    ...
    // tạo thuộc tính kiểu author trong book
    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    //constructer nhét thêm thằng author vào
    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    // thêm getter, setter cho author zô
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    ...
} 


public interface BookRepository extends JpaRepository<Book, Long> {}
public interface AuthorRepository extends JpaRepository<Author, Long> {}


@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public void createBookWithAuthor() {
        Author author = new Author("J.K. Rowling");
        Book book = new Book("Harry Potter", author);

        // Thiết lập liên kết 2 chiều
        author.setBook(book);

        // Lưu tác giả và sách
        authorRepository.save(author);
        bookRepository.save(book);
    }
}


@Entity
public class Author {
    ...
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    ...
}


@Entity
public class Book {
   ...
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    ...
}


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh tại đây nếu cần
}

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh tại đây nếu cần
}

@PostMapping("/{authorId}/books")
public Book addBookToAuthor(@PathVariable Long authorId, @RequestBody Book book) {
    Author author = authorRepository.findById(authorId).orElse(null);
    if (author != null) {
        book.setAuthor(author); // Thiết lập tác giả cho sách
        author.getBooks().add(book); // Thêm sách vào danh sách sách của tác giả
        authorRepository.save(author); // Lưu tác giả (cùng với sách)
        return bookRepository.save(book); // Lưu sách
    }
    return null;
}



@Entity
public class Student {
    ...
    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> courses = new HashSet<>();

    public Student(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    ...
}




@Entity
public class Course {
    ...
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "student_course", // Tên bảng liên kết
        joinColumns = @JoinColumn(name = "course_id"), // Khóa ngoại cho Course
        inverseJoinColumns = @JoinColumn(name = "student_id") // Khóa ngoại cho Student
    )
    private Set<Student> students = new HashSet<>();

    public Course(String title) {
        this.title = title;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    ...
}


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh tại đây nếu cần
}

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh tại đây nếu cần
}


@PostMapping("/{studentId}/courses")
public Course addCourseToStudent(@PathVariable Long studentId, @RequestBody Course course) {
    Student student = studentRepository.findById(studentId).orElse(null);
    if (student != null) {
        course.getStudents().add(student); // Thêm sinh viên vào danh sách sinh viên của khóa học
        student.getCourses().add(course); // Thêm khóa học vào danh sách khóa học của sinh viên
        studentRepository.save(student); // Lưu sinh viên
        return courseRepository.save(course); // Lưu khóa học
    }
    return null;
}



@Query("SELECT b FROM Book b WHERE b.author = :author")
List<Book> findBooksByAuthor(@Param("author") String author);

@Query(value = "SELECT * FROM books WHERE price > :price", nativeQuery = true)
List<Book> findBooksByPriceGreaterThan(@Param("price") Double price);

@Transactional
public void saveBook(Book book) {
    bookRepository.save(book);
}
