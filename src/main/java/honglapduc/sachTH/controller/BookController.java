package honglapduc.sachTH.controller;

import honglapduc.sachTH.entity.Book;
import honglapduc.sachTH.services.BookService;
import honglapduc.sachTH.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addbook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult,Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("categories",categoryService.getAllCategories());
            return "book/add";
        }

        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        // Tìm sách dựa trên ID truyền vào
        Book book = bookService.getBookById(id);

        if (book != null) {

            bookService.deleteBook(book.getId());
        }

        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        // Tìm sách dựa trên ID truyền vào
        Book book = bookService.getBookById(id);

        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        } else {
            // Xử lý khi sách không tồn tại
            return "redirect:/books";
        }
    }
    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book updatedBook, BindingResult bindingResult) {
        // Tìm sách dựa trên ID truyền vào
        Book book = bookService.getBookById(id);

        if (book != null) {
            if (bindingResult.hasErrors()) {
                return "book/edit";
            }
            // Cập nhật thông tin của đầu sách
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setCategory(updatedBook.getCategory());

            // Gọi phương thức cập nhật sách trong bookService
            bookService.updateBook(book);
        }

        return "redirect:/books";
    }


}
