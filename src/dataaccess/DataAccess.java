package dataaccess;

import java.time.LocalDate;
import java.util.HashMap;

import business.Book;
import business.BookCopy;
import business.LibraryMember;
import dataaccess.DataAccessFacade.StorageType;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);
	public void saveNewBook(Book book);
	public void updateBook(Book book);
	public void updateMember(LibraryMember member);
	public LibraryMember getLibraryMemberById(String memberId);
	public Book getBookByIsbn(String isbn);
	public void deleteBookByIsbn(String isbn);
}
