package languageApp;

public class Word {
	public String engDef;
	public String turDef;
	public String secDef;
	
	public Word(String engDef, String turDef, String secDef) {
		this.engDef = engDef;
		this.turDef = turDef;
		this.secDef = secDef;
	}

	
	public String getEngDef() {
		return engDef;
	}


	public String getTurDef() {
		return turDef;
	}


	public String getSecDef() {
		return secDef;
	}


	public void setEngDef(String engDef) {
		this.engDef = engDef;
	}

	public void setTurDef(String turDef) {
		this.turDef = turDef;
	}

	public void setSecDef(String secDef) {
		this.secDef = secDef;
	}

//"<html><div style='text-align:right; float: right;'>%s</div></html>"
	public String toString() {
		return engDef + " " + turDef;
	}
}


