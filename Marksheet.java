package Java.bean;

public class Marksheet {
	
	private String rollNo;
	private String name;
	private int chemistry;
	private int physics;
	private int maths;
	
	public Marksheet() {
		super();
	}
	
	public Marksheet(String rollNo, String name, int chemistry , int physics, int maths) {
		this.rollNo = rollNo;
		this.name = name;
		this.chemistry = chemistry;
		this.physics = physics;
		this.maths = maths;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChemistry() {
		return chemistry;
	}

	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	public int getPhysics() {
		return physics;
	}

	public void setPhysics(int physics) {
		this.physics = physics;
	}

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	@Override
	public String toString() {
		return "Marksheet [rollNo=" + rollNo + ", name=" + name + ", chemistry=" + chemistry + ", physics=" + physics
				+ ", maths=" + maths + "]";
	}
	
	
	

}
