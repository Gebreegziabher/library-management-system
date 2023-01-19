package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {

    public static Auth currentAuth = null;

    @Override
    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();
    }

    @Override
    public List<String> allMemberIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readMemberMap().keySet());
        return retval;
    }

    @Override
    public List<String> allBookIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().keySet());
        return retval;
    }

    @Override
    public List<Book> allBooks() {
        DataAccess da = new DataAccessFacade();
        List<Book> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().values());
        return retval;
    }

    @Override
    public void addBook(Book book) {
        DataAccess da = new DataAccessFacade();
        da.saveNewBook(book);
    }

    @Override
    public void updateBook(Book book) {
        DataAccess da = new DataAccessFacade();
        da.updateBook(book);
    }

    @Override
    public void deleteBookById(String isbn) {
        DataAccess da = new DataAccessFacade();
        da.deleteBookByIsbn(isbn);
    }

    @Override
    public void addLibraryMember(LibraryMember libraryMember) {
        DataAccess da = new DataAccessFacade();
        da.saveNewMember(libraryMember);
    }

    @Override
    public Book addBookCopyByIsbn(String isbn) {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> booksMap = da.readBooksMap();
        Book book = booksMap.get(isbn);
        if (book == null) {
            return null;
        }

        book.addCopy();
        da.updateBook(book);
        return book;
    }

    @Override
    public void createCheckoutRecordEntry(String memberId, String isbn) {
        // Call this method, only after validation
        DataAccess da = new DataAccessFacade();
        LibraryMember libraryMember = da.getLibraryMemberById(memberId);
        Book book = da.getBookByIsbn(isbn);

        BookCopy bookCopy = book.getNextAvailableCopy();
        LocalDate checkoutDate = LocalDate.now();
        LocalDate dueDate = LocalDate.now().plusDays(book.getMaxCheckoutLength());

        libraryMember.getCheckoutRecord().addCheckoutEntry(bookCopy, checkoutDate, dueDate);
        bookCopy.changeAvailability();

        da.updateMember(libraryMember);
        da.updateBook(book);
    }

    @Override
    public List<CheckoutEntry> allCheckoutEntries() {
        DataAccess da = new DataAccessFacade();
        Collection<LibraryMember> allMembers = da.readMemberMap().values();
        ArrayList<CheckoutEntry> allEntries = new ArrayList();
        for (LibraryMember member : allMembers) {
            List<CheckoutEntry> memberCheckoutEntries = member.getCheckoutRecord().getCheckoutEntries();
            allEntries.addAll(memberCheckoutEntries);
        }
        return allEntries;
    }

    @Override
    public List<CheckoutEntry> getCheckoutEntriesByID(String memberID) {
        DataAccess da = new DataAccessFacade();
        Collection<LibraryMember> allMembers = da.readMemberMap().values();
        ArrayList<CheckoutEntry> allEntries = new ArrayList();
        for (LibraryMember member : allMembers) {
            if (member.getMemberId().equals(memberID)) {
                List<CheckoutEntry> memberCheckoutEntries = member.getCheckoutRecord().getCheckoutEntries();
                allEntries.addAll(memberCheckoutEntries);
            }
        }
        return allEntries;
    }

    @Override
    public String validateMemberIDAndISBN(String memberId, String isbn) {
        DataAccess da = new DataAccessFacade();
        String message = "";

        LibraryMember libraryMember = da.getLibraryMemberById(memberId);
        if (libraryMember == null) {
            return "Library member doesn't exist";
        }

        Book book = da.getBookByIsbn(isbn);
        if (book == null) {
            return "Book doesn't exist";
        }

        BookCopy bookCopy = book.getNextAvailableCopy();
        if (bookCopy == null) {
            return "Book is not available";
        }

        return message;
    }
    
    @Override
    public boolean bookAlreadyExists(String isbn) {
        DataAccess da = new DataAccessFacade();
        Book book = da.getBookByIsbn(isbn);
        return book != null;
    }
    
    @Override
    public List<CheckoutEntry> getCheckedoutEntries(String isbn) {
        DataAccess da = new DataAccessFacade();
        Collection<LibraryMember> allMembers = da.readMemberMap().values();
        List<CheckoutEntry> retEntries = new ArrayList();
        for (LibraryMember m : allMembers) {
            for (CheckoutEntry entry : m.getCheckoutRecord().getCheckoutEntries()) {
                if (entry.getBookCopy().getBook().getIsbn().equals(isbn)) {
                    retEntries.add(entry);
                }
            }
        }
        return retEntries;
    }
    
}
