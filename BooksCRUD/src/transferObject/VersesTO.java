package transferObject;

public class VersesTO {
	private String poemTitle;
	private String verse1;
	private String verse2;
	public String getPoemTitle() {
		return poemTitle;
	}

	public void setPoemTitle(String poemTitle) {
		this.poemTitle = poemTitle;
	}

	public String getVerse1() {
		return verse1;
	}

	public void setVerse1(String verse1) {
		this.verse1 = verse1;
	}

	public String getVerse2() {
		return verse2;
	}

	public void setVerse2(String verse2) {
		this.verse2 = verse2;
	}

	public VersesTO()
	{
		
	}
	public VersesTO(String title,String v1,String v2) {
		// TODO Auto-generated constructor stub
		this.poemTitle = title;
		this.verse1 = v1;
		this.verse2 = v2;
	}

}
