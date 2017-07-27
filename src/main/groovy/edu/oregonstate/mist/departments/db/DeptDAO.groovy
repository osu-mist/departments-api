package edu.oregonstate.mist.departments.db

import edu.oregonstate.mist.departments.core.Department

abstract class DeptDAO {
    List<Department> getDepartments(String businessCenter)  {
        new ArrayList<Department>()
    }
}
