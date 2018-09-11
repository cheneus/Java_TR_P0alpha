package com.revature.delegates;

public class DelegateFactory {
	private static DelegateFactory df;
	private DelegateFactory() {
		super();
	}
	public static synchronized DelegateFactory getInstance() {
		if(df==null) {
			df = new DelegateFactory();
		}
		return df;
	}
	public FrontControllerDelegate getDelegate(String name) {
		switch(name) {
		case "books": return new BookDelegate();
		case "authors": return new AuthorDelegate();
		case "genres": return new GenreDelegate();
		case "login": return new LoginDelegate();
		case "purchases":
		default: return null;
		}
	}
}
