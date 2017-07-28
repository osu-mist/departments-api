package edu.oregonstate.mist.departments.db

import edu.oregonstate.mist.departments.core.Department

interface DeptDAO {
    List<Department> getDepartments(String businessCenter)
}
