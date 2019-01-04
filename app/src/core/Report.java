package core;

import java.util.ArrayList;

/*
 * Classe respons�vel por armazenar as informa��es que ser�o exibidas no relat�rio
 * Esta classe estrutura os dados de acordo com cada tipo de Anomalia Arquitetural detectada
 */

public class Report {

	/*
	 * Relat�rios de:
	 * - Ambiguous Interface
	 * - Ambiguous Interface Prediction
	 * - Connector Envy
	 * - Extraneous Connector
	 * - Scattered Funcionality Parasitic
	 * - Concern Component Overload
	 */
	private ArrayList<String> ReportAI;
	private ArrayList<String> ReportAIP;
	private ArrayList<String> ReportCEn;
	private ArrayList<String> ReportEC;
	private ArrayList<String> ReportSFP;
	private ArrayList<String> ReportCCO;
	
	public Report()
	{
		setReportAI(new ArrayList<String>());
		setReportAIP(new ArrayList<String>());
		setReportCEn(new ArrayList<String>());
		setReportEC(new ArrayList<String>());
		setReportSFP(new ArrayList<String>());
		setReportCCO(new ArrayList<String>());
	}


	//== M�todos Get e Set para armazenar e retornar relat�rios ====
	public ArrayList<String> getReportAI() {
		return ReportAI;
	}


	public void setReportAI(ArrayList<String> reportAI) {
		ReportAI = reportAI;
	}

	public void setReportElementAI(String elementAI) {
		ReportAI.add(elementAI);
	}

	public ArrayList<String> getReportCEn() {
		return ReportCEn;
	}

	
	public void setReportCEn(ArrayList<String> reportCEn) {
		ReportCEn = reportCEn;
	}

	public void setReportElementCEn(String elementCEn) {
		ReportCEn.add(elementCEn);
	}

	public ArrayList<String> getReportEC() {
		return ReportEC;
	}


	public void setReportEC(ArrayList<String> reportEC) {
		ReportEC = reportEC;
	}


	public void setReportElementEC(String elementEC) {
		ReportEC.add(elementEC);
	}
	
	public ArrayList<String> getReportSFP() {
		return ReportSFP;
	}


	public void setReportSFP(ArrayList<String> reportSFP) {
		ReportSFP = reportSFP;
	}

	
	public void setReportElementSFP(String elementSFP) {
		ReportSFP.add(elementSFP);
	}

	public ArrayList<String> getReportCCO() {
		return ReportCCO;
	}


	public void setReportCCO(ArrayList<String> reportCCO) {
		ReportCCO = reportCCO;
	}
	
	public void setReportElementCCO(String elementCCO) {
		ReportCCO.add(elementCCO);
	}


	public ArrayList<String> getReportAIP() {
		return ReportAIP;
	}


	public void setReportAIP(ArrayList<String> reportAIP) {
		ReportAIP = reportAIP;
	}
	
	public void setReportElementAIP(String elementAIP) {
		ReportAIP.add(elementAIP);
	}
	//== M�todos Get e Set para armazenar e retornar relat�rios ====
	
	//M�todo utilizado para limpar os relat�rios;
	public void clear(){
		ReportAI.clear();
		ReportAIP.clear();
		ReportCEn.clear();
		ReportEC.clear();
		ReportSFP.clear();
		ReportCCO.clear();
	}
	
}
