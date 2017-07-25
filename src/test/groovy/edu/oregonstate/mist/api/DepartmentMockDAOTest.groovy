package edu.oregonstate.mist.api

import edu.oregonstate.mist.departments.db.DepartmentMockDAO
import org.junit.Test

class DepartmentMockDAOTest {
    @Test
    void shouldReturnEmptyList() {
        assert !DepartmentMockDAO.generate(0)
    }

    @Test
    void shouldGenerateManyDepartments() {
        (1..10).each {
            assert DepartmentMockDAO.generate(it).size() == it
        }
    }

    @Test
    void shouldNotGenerateNegativeDepartments() {
        (-10..-1).each {
            assert DepartmentMockDAO.generate(it).size() == 0
        }
    }

    @Test
    void shouldReturnDepartmentsSpecifiedInConstructor() {
        DepartmentMockDAO departmentMockDAOTest
        (1..10).each {
            departmentMockDAOTest = new DepartmentMockDAO(it)
            assert departmentMockDAOTest.getDepartments().size() == it
        }
    }
}
