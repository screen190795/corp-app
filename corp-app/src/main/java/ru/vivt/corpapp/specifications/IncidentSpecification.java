package ru.vivt.corpapp.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.vivt.corpapp.entity.Incident;
import ru.vivt.corpapp.entity.Priority;
import ru.vivt.corpapp.entity.Status;

public class IncidentSpecification {

    public static Specification<Incident> titleContains(String title) {
        try {
            return (r, cq, cb) -> cb.like(r.get("title"), "%" + title + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Specification<Incident> priorityEqualTo(String priority) {
        if (priority == null) {
            return null;
        }
        try {
            return (r, cq, cb) -> cb.equal(r.get("priority"), Priority.valueOf(priority));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Specification<Incident> statusEqualTo(String status) {
        if (status == null) {
            return null;
        }
        try {
            return (r, cq, cb) -> cb.equal(r.get("status"), Status.valueOf(status));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Specification<Incident> reporterIdEqualTo(String reporterId) {
        try {
            return (r, cq, cb) -> cb.equal(r.get("reporter"), Long.parseLong(reporterId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Specification<Incident> assigneeIdEqualTo(String assigneeId) {
        try {
            return (r, cq, cb) -> cb.equal(r.get("assignee"), Long.parseLong(assigneeId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Specification<Incident> commentContains(String comment) {
        try {
            return (r, cq, cb) -> cb.like(r.get("comment"), "%" + comment + "%");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
