package se.leanbit.ticketsystem.model.abstra;

import com.google.gson.annotations.Expose;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ModelEntity {
	@Id
	@GeneratedValue
	@Expose
	private long id;

	protected ModelEntity() {
	}

	public ModelEntity(long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

}
