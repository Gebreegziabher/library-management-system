package business;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 */
final public class CheckoutEntry implements Serializable {
	
	private static final long serialVersionUID = 6220690006685002829L;
	private BookCopy bookCopy;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private CheckoutRecord checkoutRecord;

	public CheckoutEntry(CheckoutRecord checkoutRecord, BookCopy bookCopy, LocalDate checkoutDate, LocalDate dueDate) {
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.checkoutRecord = checkoutRecord;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

}
