package tpServlet;

public class Disques {
	private String code;
	public String prix;
	
	public Disques (String cd, String pr) {
		code = cd;
		prix = pr;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPrix() {
		return prix;
	}
	public void setPrix(String prix) {
		this.prix = prix;
	}
	public Disques (String cd) {
		code = cd;
	}


}
