package se.leanbit.ticketsystem.repository;

import org.springframework.data.repository.CrudRepository;
import se.leanbit.ticketsystem.model.Issue;
import se.leanbit.ticketsystem.model.Team;

public interface IssueRepository extends CrudRepository<Issue, Long> {

}
