package Entity;

import javax.persistence.*;

@Entity
@Table(name="users")

public class User {
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;

	public User() {
		super();
		this.username="default";
		this.password="default";
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {return username;}
	public String getPassword() {return password;}
	@Override
	public String toString() {return "User [username=" + username + ", password=" + password + "]";}
}
