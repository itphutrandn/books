package edu.books.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class OffsetLimitPageable extends PageRequest {
	
	private static final long serialVersionUID = 1L;

	protected OffsetLimitPageable(int page, int size, Sort sort) {
		super(page, size, sort);
		this.offset = offset;
	}

	private int offset;

	@Override
	public long getOffset() {
		return this.offset;
	}
}