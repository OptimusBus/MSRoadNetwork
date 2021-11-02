package model;

public class Street {
	
	public Street(String id, int speedlimit, String name, double weight, float ffs) {
		super();
		this.linkid = id;
		this.speedlimit = speedlimit;
		this.name = name;
		this.weight = weight;
		this.ffs = ffs;
	}
	

	public String getId() {
		return linkid;
	}
	public void setId(String id) {
		this.linkid = id;
	}
	public int getSpeedlimit() {
		return speedlimit;
	}
	public void setSpeedlimit(int speedlimit) {
		this.speedlimit = speedlimit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public float getFfs() {
		return ffs;
	}
	public void setFfs(float ffs) {
		this.ffs = ffs;
	}

	private String linkid;
	private int speedlimit;
	private String name;
	private double weight;
	private float ffs;

}
