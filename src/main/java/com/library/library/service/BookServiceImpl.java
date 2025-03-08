package com.library.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.library.library.dto.BookDto;
import com.library.library.dto.BookRequest;
import com.library.library.entity.Author;
import com.library.library.entity.Book;
import com.library.library.entity.Category;
import com.library.library.exception.AuthorNotFoundException;
import com.library.library.exception.BookNotFoundException;
import com.library.library.exception.CategoryNotFoundException;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.repository.CategoryRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
            CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BookDto getBook(Long id) {
        Book book = findBookById(id);
        return BookDto.from(book, true, true);
    }

    @Override
    public List<BookDto> getBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();

        return books.stream()
                .map(book -> BookDto.from(book, true, true))
                .collect(Collectors.toList());
    }

    @Override
    public BookDto saveBook(BookRequest bookRequest) {
        Author author = findAuthorById(bookRequest.authorId());
        Category category = findCategoryById(bookRequest.categoryId());

        Book book = new Book(bookRequest.title(), bookRequest.releaseYear());
        book.setAuthor(author);
        book.setCategory(category);

        // we first save the original object of Book to the database
        Book savedBook = bookRepository.save(book);

        // then, we create the dto object to return to the client in the response body
        return BookDto.from(savedBook, true, true);
    }

    @Override
    public BookDto updateBook(Long id, BookRequest bookRequest) {
        Book existingBook = findBookById(id);

        existingBook.setTitle(bookRequest.title());
        existingBook.setReleaseYear(bookRequest.releaseYear());

        // Update author if provided
        if (bookRequest.authorId() != null) {
            existingBook.setAuthor(findAuthorById(bookRequest.authorId()));
        }

        // Update category if provided
        if (bookRequest.categoryId() != null) {
            existingBook.setCategory(findCategoryById(bookRequest.categoryId()));
        }

        // Save and convert to DTO
        Book savedBook = bookRepository.save(existingBook);

        return BookDto.from(savedBook, true, true);
    }

    @Override
    public void deleteBook(Long id) {
        findBookById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAuthorBooks(Long authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);

        return books.stream()
                .map(book -> BookDto.from(book, false, true))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getCategoryBooks(Long categoryId) {
        findCategoryById(categoryId);
        List<Book> books = bookRepository.findByCategoryId(categoryId);

        return books.stream()
                .map(book -> BookDto.from(book, true, false))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> getAuthorCategoryBooks(Long authorId, Long categoryId) {
        findAuthorById(authorId);
        findCategoryById(categoryId);

        return bookRepository.findByAuthorIdAndCategoryId(authorId, categoryId);
    }

    // helper functions
    private Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    private Book findBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
}
