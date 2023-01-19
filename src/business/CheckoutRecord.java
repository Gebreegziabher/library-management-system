package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 */
final public class CheckoutRecord implements Serializable {
	
	private static final long serialVersionUID = 6220690276383962829L;
	private List<CheckoutEntry> checkoutEntries;
	private LibraryMember libraryMember;

	public CheckoutRecord(LibraryMember member) {
		libraryMember = member;
		checkoutEntries = new ArrayList();
	}
	
	public void addCheckoutEntry(BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
		CheckoutEntry entry = new CheckoutEntry(this, bookCopy, checkoutDate, dueDate);
		this.checkoutEntries.add(entry);
	}
	
	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}
	
	public LibraryMember getLibraryMember() {
		return libraryMember;
	}
	
}
