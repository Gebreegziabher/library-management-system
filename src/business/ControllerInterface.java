package business;

import java.time.LocalDate;
import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public List<Book> allBooks();
	public Book addBookCopyByIsbn(String isbn);
	public void addBook(Book book);
	public void updateBook(Book book);
	public void deleteBookById(String isbn);
	public void addLibraryMember(LibraryMember libraryMember);
	public void createCheckoutRecordEntry(String memberId, String isbn);
	public List<CheckoutEntry> allCheckoutEntries();
        public List<CheckoutEntry> getCheckoutEntriesByID(String memberID);
	public String validateMemberIDAndISBN(String memberId, String isbn);
        public boolean bookAlreadyExists(String isbn);
        public List<CheckoutEntry> getCheckedoutEntries(String isbn);
}
