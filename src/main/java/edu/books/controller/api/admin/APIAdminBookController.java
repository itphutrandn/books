package edu.books.controller.api.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.books.base.AbstractController;
import edu.books.constant.CommonConstants;
import edu.books.constant.UrlConstants;
import edu.books.convert.BookConverter;
import edu.books.domain.Book;
import edu.books.domain.User;
import edu.books.form.BookActiveRequest;
import edu.books.form.BookAdminRequest;
import edu.books.form.BookForm;
import edu.books.model.BookAdminEnabledResponse;
import edu.books.model.BookAdminResponse;
import edu.books.model.ResponseAPI;
import edu.books.security.UserInfoHandler;
import edu.books.service.BookService;
import edu.books.util.ObjectUtil;
import edu.books.util.OffsetLimitPageable;
import edu.books.util.PaginationUtils;
import edu.books.validator.BookValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(UrlConstants.URI_API + UrlConstants.URI_ADMIN)
@Api(value = "api.book", description = "Book API")
public class APIAdminBookController extends AbstractController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BookService bookService;

	@Autowired
	@Qualifier("bookValidator")
	private BookValidator bookValidator;

	@Autowired
	@Qualifier("bookConverter")
	private BookConverter bookConverter;

	@Autowired
	@Qualifier("userInfoHandler")
	private UserInfoHandler userInfoHandler;

	@RequestMapping(value = UrlConstants.URL_BOOK, method = RequestMethod.POST)
	@ApiOperation(value = "Book list", response = Object.class)
	public ResponseAPI search(@RequestBody BookAdminRequest bookRequest) {
		ObjectUtil.removeEmptyField(bookRequest);
		Pageable pageable = OffsetLimitPageable.of(
				PaginationUtils.getOffset(bookRequest.getPageIndex(), bookRequest.getPageRecord()),
				bookRequest.getPageRecord(),
				Sort.by(Sort.Direction.valueOf(bookRequest.getOrderBy()), bookRequest.getSortBy()));
		Page<BookAdminResponse> books = bookService.getSearchAllBook(bookRequest.getTitle(), bookRequest.getAuthor(),
				pageable);
		HashMap<String, Object> data = new HashMap<>();
		data.put("books", books.getContent());
		data.put("totalPage", books.getTotalPages());
		return new ResponseAPI(HttpStatus.SC_OK, null, data);
	}

	@RequestMapping(value = UrlConstants.URL_BOOK_LIST, method = RequestMethod.POST)
	@ApiOperation(value = "Book list", response = Object.class)
	public ResponseAPI getAllBook(
			@RequestHeader(value = "Authorization", required = false, defaultValue = "") String authorization,
			HttpSession session) {
		User currentUser = currentUser(authorization);
		HashMap<String, Object> data = new HashMap<>();
		List<BookAdminResponse> books = null;
		if (currentUser != null) {
			if (userInfoHandler.isAdmin(session)) {
				books = bookService.getAllByUserId(null);
			} else {
				books = bookService.getAllByUserId(currentUser.getId());
			}
			data.put("books", books);
		}
		return new ResponseAPI(HttpStatus.SC_OK, null, data);
	}
	
	@RequestMapping(value = UrlConstants.URL_BOOK_LIST_ENABLED, method = RequestMethod.POST)
	@ApiOperation(value = "Book list", response = Object.class)
	public ResponseAPI getAllBookNotLogin(HttpSession session) {
		List<BookAdminEnabledResponse> books = bookService.getListEnabled();
		HashMap<String, Object> data = new HashMap<>();
		data.put("books", books);
		return new ResponseAPI(HttpStatus.SC_OK, null, data);
	}
	
	@RequestMapping(value = UrlConstants.URL_ACTIVE_BOOK, method = RequestMethod.POST)
	@ApiOperation(value = "Active book", response = Object.class)
	public ResponseAPI active(@RequestBody BookActiveRequest bookRequest,
			@RequestHeader(value = "Authorization", required = false, defaultValue = "") String authorization,
			HttpSession session) {
		User currentUser = currentUser(authorization);
		HashMap<String, Object> data = new HashMap<>();
		if (currentUser != null) {
			if (userInfoHandler.isAdmin(session)) {
				bookService.active(bookRequest.getEnabled(), bookRequest.getBookId());
			} 
			data.put("enabled", bookRequest.getEnabled());
		}
		return new ResponseAPI(HttpStatus.SC_OK, null, data);
	}

	@RequestMapping(value = UrlConstants.URL_MY_BOOK, method = RequestMethod.POST, consumes = "multipart/form-data")
	@ApiOperation(value = "My book list", response = Object.class)
	public ResponseAPI myBookList(@RequestBody BookAdminRequest bookRequest,
			@RequestHeader(value = "Authorization", required = false, defaultValue = "") String authorization) {
		User currentUser = currentUser(authorization);
		if (currentUser != null) {
			ObjectUtil.removeEmptyField(bookRequest);
			Pageable pageable = OffsetLimitPageable.of(
					PaginationUtils.getOffset(bookRequest.getPageIndex(), bookRequest.getPageRecord()),
					bookRequest.getPageRecord(),
					Sort.by(Sort.Direction.valueOf(bookRequest.getOrderBy()), bookRequest.getSortBy()));
			Page<BookAdminResponse> books = bookService.getSearchAllBook(bookRequest.getTitle(),
					bookRequest.getAuthor(), currentUser.getId(), pageable);
			HashMap<String, Object> data = new HashMap<>();
			data.put("books", books.getContent());
			data.put("totalPage", books.getTotalPages());
			return new ResponseAPI(HttpStatus.SC_OK, null, data);
		}
		return new ResponseAPI(HttpStatus.SC_OK,
				messageSource.getMessage("api.admin.book.empty", new String[] {}, null), null);
	}

	@RequestMapping(value = UrlConstants.URL_DETAIL_BOOK, method = RequestMethod.GET)
	@ApiOperation(value = "Get detail book", response = Object.class)
	public ResponseAPI detail(@PathVariable Integer id) throws Exception {
		Book book = bookService.findById(id);
		if (book != null) {
			HashMap<String, Object> data = new HashMap<>();
			data.put("book", book);
			return new ResponseAPI(HttpStatus.SC_OK, null, data);
		}
		return new ResponseAPI(HttpStatus.SC_OK,
				messageSource.getMessage("api.admin.book.empty", new String[] {}, null), null);
	}

	@PostMapping(value = UrlConstants.URL_CREATE_BOOK)
	@ApiOperation(value = "Create book", response = Object.class)
	public ResponseAPI create(@ModelAttribute BookForm book,
			@RequestHeader(value = "Authorization", required = false, defaultValue = "") String authorization,
			HttpServletRequest request) throws Exception {
		boolean isCreate = false;
		String image = "";
		User currentUser = currentUser(authorization);
		ResponseAPI responseAPI = null;
		ObjectUtil.removeEmptyField(book);
		if(currentUser != null) {
			book.setAuthor(currentUser.getFirstName() + " "+ currentUser.getLastName());
			bookValidator.validate(book);
			String msg = messageSource.getMessage("api.admin.create.book.success", new String[] {}, null);
			Book bookSave = bookConverter.convert(book);
			try {
				if (book.getAvatar() != null
						&& !StringUtils.isEmpty(FilenameUtils.getExtension(book.getAvatar().getOriginalFilename()))) {
					image = upload(book.getAvatar(), request);
				}
				bookSave.setImage(image);
				Set<User> users = new HashSet<>();
				users.add(currentUser);
				bookSave.setUsers(users);
				bookService.save(bookSave);
				isCreate = true;
			} catch (Exception e) {
				msg = messageSource.getMessage("api.admin.create.book.fail", new String[] {}, null);
				throw new Exception(e.getMessage());
			}
			responseAPI = new ResponseAPI(isCreate == true ? HttpStatus.SC_OK : HttpStatus.SC_NOT_MODIFIED, msg,
					isCreate);
		}
		return responseAPI;
	}

	@PostMapping(value = UrlConstants.URL_UPDATE_BOOK)
	@ApiOperation(value = "Update book", response = Object.class)
	public ResponseAPI update(@ModelAttribute BookForm book,
			@RequestHeader(value = "Authorization", required = false, defaultValue = "") String authorization, 
			HttpServletRequest request)
			throws Exception {
		String image = "";
		boolean isCreate = false;
		ResponseAPI responseAPI = null;
		User currentUser = currentUser(authorization);
		ObjectUtil.removeEmptyField(book);
		if (currentUser != null) {
			book.setAuthor(currentUser.getFirstName() + " "+ currentUser.getLastName());
			bookValidator.validateUpdate(book);
			Book checkBook = bookService.findById(book.getId());
			if (checkBook == null) {
				throw new Exception(messageSource.getMessage("api.admin.book.notexist", new String[] {}, null), null);
			}
			String msg = messageSource.getMessage("api.admin.update.book.success", new String[] {}, null);
			Book bookSave = bookConverter.convert(book);
			try {
				
				if (book.getAvatar() != null
						&& !StringUtils.isEmpty(FilenameUtils.getExtension(book.getAvatar().getOriginalFilename()))) {
					if(!"".equals(checkBook.getImage())) {
						deleteFile(checkBook.getImage(), request);
					}
					image = upload(book.getAvatar(), request);
				} else {
					if("1".equals(book.getDelImg())) {
						if(!"".equals(checkBook.getImage())) {
							deleteFile(checkBook.getImage(), request);
						}
						image = "";
					} else {
						image = checkBook.getImage();
					}
					
				}
				bookSave.setImage(image);
				Set<User> users = new HashSet<>();
				users.add(currentUser);
				bookSave.setUsers(users);
				checkBook.setTitle(bookSave.getTitle());
				checkBook.setDescription(bookSave.getDescription());
				checkBook.setImage(bookSave.getImage());
				bookService.save(checkBook);
				isCreate = true;
			} catch (Exception e) {
				msg = messageSource.getMessage("api.admin.update.book.fail", new String[] {}, null);
				throw new Exception(e.getMessage());
			}

		responseAPI = new ResponseAPI(isCreate == true ? HttpStatus.SC_OK : HttpStatus.SC_NOT_MODIFIED, msg,
				isCreate);
		}
		return responseAPI;
	}

	@PostMapping(value = UrlConstants.URL_DELETE_BOOK)
	@ApiOperation(value = "Delete book", response = Object.class)
	public ResponseAPI delete(@PathVariable Integer id, HttpServletRequest request,
			@RequestHeader(value = "Authorization", required = false, defaultValue = "") String authorization)
			throws Exception {
		String msg = org.springframework.http.HttpStatus.OK.toString();
		try {
			Book book = bookService.findById(id);
			if (book == null) {
				throw new Exception(messageSource.getMessage("api.admin.book.notexist", new String[] {}, null), null);
			}
			User currentUser = currentUser(authorization);
			if(!"".equals(book.getImage())) {
				deleteFile(book.getImage(), request);
			}
			bookService.delete(book.getId(), currentUser.getId());
			bookService.delete(book);
		} catch (Exception e) {
			msg = messageSource.getMessage("api.admin.delete.book.fail", new String[] {}, null);
			throw new Exception(e.getMessage());
		}
		msg = messageSource.getMessage("api.admin.delete.book.success", new String[] {}, null);
		ResponseAPI responseAPI = new ResponseAPI(HttpStatus.SC_OK, msg, null);
		return responseAPI;
	}

	public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
		String fileName = FilenameUtils.getBaseName(file.getOriginalFilename()) + " - " + System.nanoTime() + "."
				+ FilenameUtils.getExtension(file.getOriginalFilename());
		String folderName = request.getServletContext().getRealPath("") + CommonConstants.DIR_UPLOAD;
		if (!new File(folderName).exists()) {
			new File(folderName).mkdirs();
		}
		String filePath = folderName + File.separator + fileName;
		file.transferTo(new File(filePath));
		return fileName;
	}
	
	public boolean deleteFile(String fileName,HttpServletRequest request) {
		String folderName = request.getServletContext().getRealPath("") + CommonConstants.DIR_UPLOAD;
		if (!new File(folderName).exists()) {
			new File(folderName).mkdirs();
		}
		String filePath = folderName + File.separator + fileName;
		return new File(filePath).delete();
	}

}
