package Task.Java;

public class Details {

	private int Sno;
	private String Name;
	private String City;
	private String Mobile;
	private String Address;
	public int getSno() {
		return Sno;
	}
	public void setSno(int sno) {
		Sno = sno;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Details(int sno, String name, String city, String mobile, String address) {
		super();
		Sno = sno;
		Name = name;
		City = city;
		Mobile = mobile;
		Address = address;
	}
	
	
}
