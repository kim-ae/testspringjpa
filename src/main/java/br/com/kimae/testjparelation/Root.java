package br.com.kimae.testjparelation;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Root {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "rootId")
	private List<Leaf> leafs;

	public Root() {
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}

	public List<Leaf> getLeafs() {
		return leafs;
	}

	public void setLeafs(List<Leaf> leafs) {
		this.leafs = leafs;
	}

	@Override
	public String toString() {
		return "Root{" +
				"id=" + id +
				", leafs=" + leafs +
				'}';
	}
}
