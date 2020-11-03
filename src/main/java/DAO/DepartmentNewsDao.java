package DAO;

import models.Department;
import models.DepartmentNews;
import models.User;

import java.util.List;

public interface DepartmentNewsDao {
    void add(DepartmentNews departmentNews);

    List<DepartmentNews> getAllDepartmentNews();

    DepartmentNews getDepartmentNewsById(int id);

}
