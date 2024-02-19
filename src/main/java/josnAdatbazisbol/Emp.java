package josnAdatbazisbol;

public class Emp {

	private String ename;
	private String job;
	private int sal;

	public Emp(String ename, String job, int sal) {
		this.ename = ename;
		this.job = job;
		this.sal = sal;
	}

	public String getEname() {
		return ename;
	}

	public String getJob() {
		return job;
	}

	public int getSal() {
		return sal;
	}

	@Override
	public String toString() {
		return ename + " " + job + " " + sal + "$";
	}

}
