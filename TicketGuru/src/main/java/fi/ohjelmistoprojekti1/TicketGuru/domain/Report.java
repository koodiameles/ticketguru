package fi.ohjelmistoprojekti1.TicketGuru.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Report {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long reportid; 
private String reportname;



public Report(String reportname) {
	super();
	this.reportname = reportname;
}


public Long getReportid() {
	return reportid;
}


public void setReportid(Long reportid) {
	this.reportid = reportid;
}


public String getReportname() {
	return reportname;
}


public void setReportname(String reportname) {
	this.reportname = reportname;
}


@Override
public String toString() {
	return "Report [reportid=" + reportid + ", reportname=" + reportname + "]";
} 


}
