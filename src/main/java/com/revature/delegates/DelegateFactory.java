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
		case "department": return new DepartmentDelegate();
		case "address": return new AddressDelegate();
//		case "employee": return new EmployeeDelegate();
		case "eventlocation": return new EventLocationDelegate();
		case "eventtype": return new EventTypeDelegate();
		case "gradeformat": return new GradeFormatDelegate();
		case "gradestatus": return new GradeStatusDelegate();
		case "inforeq": return new InfoReqDelegate();
		case "status": return new StatusDelegate();
		case "tr": return new TRDelegate();
		case "trf": return new TRFDelegate();
		case "trt": return new TRTDelegate();
		case "login": return new LoginDelegate();
		case "purchases":
		default: return null;
		}
	}
}
