package se.leanbit.ticketsystem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import se.leanbit.ticketsystem.model.Team;
import org.springframework.data.repository.CrudRepository;
import se.leanbit.ticketsystem.model.User;
import se.leanbit.ticketsystem.model.WorkItem;

import java.util.List;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {
	@Query("select w from WorkItem w where w.name = ?1")
	WorkItem getWorkItem(final String name);

	@Transactional
	@Modifying
	@Query("delete from WorkItem w where w.name = ?1")
	void removeWorkItem(final String name);

	@Query("select w from WorkItem w where w.description like %?1%")
	List<WorkItem> getWorkItemWithDescriptionLike(final String description);
}