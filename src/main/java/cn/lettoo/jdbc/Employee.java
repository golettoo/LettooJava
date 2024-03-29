package cn.lettoo.jdbc;

public class Employee {

	private int id;
	
	private String name;
	
	private Department department;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public String toString() {
	    StringBuffer sb = new StringBuffer();
	    sb.append("id=" + this.id);
	    sb.append("name=" + this.name);
	    sb.append("department=" + this.department);
	    sb.append("description=" + this.description);
	    
	    return sb.toString();
    }
}
