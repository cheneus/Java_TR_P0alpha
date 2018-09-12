package com.revature.data;

public class TRAppDAOFactory {
	private static TRAppDAOFactory bf = new TRAppDAOFactory();
	private static final String TYPE = "Oracle";
	private TRAppDAOFactory() {
		super();
	}
	public static synchronized TRAppDAOFactory getInstance() {
		if(bf==null) {
			bf = new TRAppDAOFactory();
		}
		return bf;
	}
	public AddressDAO getAddressDAO() {
		switch(TYPE) {
		case "Oracle": return new AddressOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public DepartmentDAO getDepartmentDAO() {
		switch(TYPE) {
		case "Oracle": return new DepartmentOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public EmployeeDAO getEmployeeDAO() {
		switch(TYPE) {
		case "Oracle": return new EmployeeOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public EventLocationDAO getEventLocationDAO() {
		switch(TYPE) {
		case "Oracle": return new EventLocationOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public EventTypeDAO getEventTypeDAO() {
		switch(TYPE) {
		case "Oracle": return new EventTypeOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public GradeFormatDAO getGradeFormatDAO() {
		switch(TYPE) {
		case "Oracle": return new GradeFormatOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	
	public GradeStatusDAO getGradeStatusDAO() {
		switch(TYPE) {
		case "Oracle": return new GradeStatusOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public InfoRequestDAO getInfoRequestDAO() {
		switch(TYPE) {
		case "Oracle": return new InfoRequestOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public LoginDAO getLoginDAO() {
		switch(TYPE) {
		case "Oracle": return new LoginOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public StatusDAO getStatusDAO() {
		switch(TYPE) {
		case "Oracle": return new StatusOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public SubmissionDAO getSubmissionDAO() {
		switch(TYPE) {
		case "Oracle": return new SubmissionOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public TuitionReimbursementDAO getTuitionReimbursementDAO() {
		switch(TYPE) {
		case "Oracle": return new TuitionReimbursementOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public TuitionReimbursementFormDAO getTuitionReimbursementFormDAO() {
		switch(TYPE) {
		case "Oracle": return new TuitionReimbursementFormOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
	public TuitionReimbursementTypeDAO getTuitionReimbursemenTypeDAO() {
		switch(TYPE) {
		case "Oracle": return new TuitionReimbursementTypeOracle();
		default: throw new RuntimeException("Could not determine DAO type");
		}
	}
}
