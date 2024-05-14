package es.gob.info.ant.dto;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@Data
public class PaginadorDto implements Serializable {

	// Número de elementos que se muestran por página
	private Integer pageSize;

	// Página mostrada de entre todas las que tiene la búsqueda
	private Integer currentPage;

	// Número de paginas que tiene la búsqueda
	private Integer numPages;

	// Número total de elementos que tiene la búsqueda
	private Integer inboxSize;

	// Verdadero si se está posicionado en la primera página
	private boolean isFirst;

	// Verdadero si se está posicionado en la última página
	private boolean isLast;

	private ArrayList<Integer> pages;

	public PaginadorDto() {
	   this.currentPage = 1;
	   this.pageSize = 10;
	 }

	public PaginadorDto(Integer currentPage) {
	   this.pageSize = 10;
	   this.currentPage = currentPage;
	 }

	public PaginadorDto(Integer inboxSize, Integer pageSize) {
	   this.inboxSize = inboxSize;
	   this.pageSize = pageSize;
	   this.currentPage = 1;
	   this.numPages = this.getNumPages();
	 }

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getInboxSize() {
		return inboxSize;
	}

	public void setInboxSize(Integer inboxSize) {
		this.inboxSize = inboxSize;
	}

	public boolean isIsFirst() {
		this.isFirst = this.currentPage == 1;
		return this.isFirst;
	}

	public void setIsFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isIsLast() {
		this.isLast = this.currentPage.equals(this.numPages);
		return isLast;
	}

	public void setIsLast(boolean isLast) {
		this.isLast = isLast;
	}

	public Integer getNumPages() {
		if (inboxSize != null) {
			this.numPages = (int) Math.ceil((double) inboxSize / (double) pageSize);
		} else {
			this.numPages = 0;
		}
		setPages();
		return numPages;
	}

	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}

	public ArrayList<Integer> getPages() {
		return pages;
	}

	public void setPages() {
		pages = new ArrayList<Integer>();
		for (int i = 1; i <= numPages; i++) {
			pages.add(i);
		}
	}

	private static final long serialVersionUID = 2204195332241839710L;

}
