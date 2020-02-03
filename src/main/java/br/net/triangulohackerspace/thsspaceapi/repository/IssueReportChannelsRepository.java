package br.net.triangulohackerspace.thsspaceapi.repository;

import br.net.triangulohackerspace.thsspaceapi.domain.IssueReportChannels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssueReportChannelsRepository extends JpaRepository<IssueReportChannels, Long> {
	
	@Query(value = "SELECT issueReportChannels FROM br.net.triangulohackerspace.thsspaceapi.domain.IssueReportChannels issueReportChannels WHERE issueReportChannels.space.id = :spaceId")
	IssueReportChannels findBySpaces(@Param("spaceId") Long spaceId);
}
