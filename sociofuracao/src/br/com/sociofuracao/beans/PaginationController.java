package br.com.sociofuracao.beans;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class PaginationController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient HtmlDataTable paginationDmdDataTable;
	// getter and setter methods for paginationDmdDataTable;
	private int totalRows;
	private int firstRow;
	private int rowsPerPage = 2;
	private int totalPages;
	private int pageRange = 10;
	private Integer[] pages;
	private int currentPage;

	// Providing all getter methods for the above attributes.
	public void loadData() {
		totalRows = paginationDmdDataTable.getRowCount();
		currentPage = (totalRows / rowsPerPage)
				- ((totalRows - firstRow) / rowsPerPage) + 1;
		totalPages = (totalRows / rowsPerPage)
				+ ((totalRows % rowsPerPage != 0) ? 1 : 0);
		int pagesLength = Math.min(pageRange, totalPages);
		pages = new Integer[pagesLength];
		int firstPage = Math.min(Math.max(0, currentPage - (pageRange / 2)),
				totalPages - pagesLength);
		// Create pages (page numbers for page links).
		for (int i = 0; i < pagesLength; i++) {
			pages[i] = ++firstPage;
		}
	}

	public void page(ActionEvent event) {
		System.out.println((Integer) ((UICommand) event.getComponent())
				.getValue());
		page(((Integer) ((UICommand) event.getComponent()).getValue() - 1)
				* rowsPerPage);
	}

	private void page(int firstRow) {
		this.firstRow = firstRow;
		loadData();
	}

	public Integer[] getPages() {
		loadData();
		return pages;
	}
}