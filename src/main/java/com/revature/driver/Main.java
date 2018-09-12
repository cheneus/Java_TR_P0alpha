package com.revature.driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Author;
import com.revature.beans.Book;
import com.revature.beans.Customer;
import com.revature.beans.Department;
import com.revature.beans.Purchase;
import com.revature.beans.User;
import com.revature.data.AuthorDAO;
import com.revature.data.BookAppDAOFactory;
import com.revature.data.BookDAO;
import com.revature.data.GenreDAO;
import com.revature.data.TRAppDAOFactory;
import com.revature.services.BookService;
import com.revature.services.BookServiceOracle;
import com.revature.services.CustomerService;
import com.revature.services.CustomerServiceOracle;
import com.revature.services.PurchaseService;
import com.revature.services.PurchaseServiceOracle;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.LogUtil;

public class Main {
	private static ConnectionUtil cu = ConnectionUtil.getInstance();
	private static TRAppDAOFactory tf = TRAppDAOFactory.getInstance();
	private static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		//firstAttempt();
		//System.out.println(getGenre(5));
		//login();
		//dao();
		service();
	}

	private static void service() {
		BookService bs = new BookServiceOracle();
		PurchaseService ps = new PurchaseServiceOracle();
		CustomerService cs = new CustomerServiceOracle();
		String username = "rorr";
		String password = "pass1";
		//System.out.println(bs.getBooks());
		
		// Richard wants to make a purchase
		
		// lets log in:
		Customer c = cs.getCustomer(username, password);
		
		// Try to retrieve open purchases
		Purchase p = ps.getOpenPurchase(c);
		if(p==null) {
			p = new Purchase();
			p.setStatus("OPEN");
			p.setCust(c);
			ps.addPurchase(p);
			log.trace("new purchase created: "+p);
		}
		
		Set<Book> books = bs.getBooks();
		for(Book b : books) {
			if(b.getId()%3==0)
				ps.addBookToCart(p, b);
		}
	}

	private static void dao() {
		GenreDAO gd = bf.getGenreDAO();
		AuthorDAO ad = bf.getAuthorDAO();
		BookDAO bd = bf.getBookDAO();
		//System.out.println(gd.getGenre(3));
		//System.out.println(gd.getGenres());
		Book b = new Book();
		b.setId(1);
		//System.out.println(gd.getGenresByBook(b));
		Department g = gd.getGenre(1);
//		g.setGenre("Fantasy");
//		gd.updateGenre(g);
		
		/*Genre g = new Genre();
		g.setGenre("Steam Punk");
		System.out.println(gd.addGenre(g));
		System.out.println(g);*/
		//System.out.println(ad.getAuthorByName("J.K.", "Rowling"));
		b.setIsbn10("1111111111");
		b.setIsbn13("1234567890-123");
		b.setTitle("Harry Potter and the Prisoner of Azkaban");
		Author a = ad.getAuthor(1);
		HashSet<Author> authors = new HashSet<Author>();
		authors.add(a);
		b.setAuthors(authors);
		HashSet<Department> genres = new HashSet<Department>();
		genres.add(g);
		b.setGenres(genres);
		b.setPrice(56.0);
		b.setStock(2);
		bd.addBook(b);
	}

	private static void login() {
		String user = null;
		String pass = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Username : ");
		user = scan.nextLine();
		System.out.println("Password : ");
		pass = scan.nextLine();
		
		System.out.println(getUser2(user,pass));
	}

	private static User getUser2(String user, String pass) {
		// are no longer allowed to use Statement
		User u = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from login where username =? and pswd=?";
			log.trace(sql);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				log.trace("User found!");
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("pswd"));
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, Main.class);
		}
		
		return u;
	}

	private static User getUser(String user, String pass) {
		User u = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from login where username ='"+user +"' and pswd='"+pass+"'";
			log.trace(sql);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				log.trace("User found!");
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("pswd"));
				u.setFirst(rs.getString("first_name"));
				u.setLast(rs.getString("last_name"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, Main.class);
		}
		
		return u;
	}

	private static Department getGenre(int id) {
		Department g = null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, genre from genre where id ="+id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				log.trace(rs.getInt(1)+" | "+rs.getString(2));
				g = new Department();
				g.setId(rs.getInt("id"));
				g.setGenre(rs.getString("genre"));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, Main.class);
		}
		return g;
	}

	private static void firstAttempt() {
		String sql = "select * from book";
		//String sql = "select id, title from book";
		try (Connection conn = cu.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//log.trace(rs.getInt("id")+" | "+rs.getString("title"));
				log.trace(rs.getInt(1)+" | "+rs.getString(2));
			}
		} catch (SQLException e) {
			LogUtil.logException(e, Main.class);
		}
	}

}
