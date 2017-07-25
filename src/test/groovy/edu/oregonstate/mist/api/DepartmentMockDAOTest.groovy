package edu.oregonstate.mist.api

import edu.oregonstate.mist.departments.db.DepartmentMockDAO
import org.junit.Test
import static org.junit.Assert.*

class DepartmentMockDAOTest {
    @Test
    void shouldReturnEmptyList() {
        assert !DepartmentMockDAO.generate(0)
    }

    @Test
    void shouldGenerateManyDepartments() {
        (1..10).each {
            assertEquals(DepartmentMockDAO.generate(it).size(), it)
        }
    }

    @Test
    void shouldNotGenerateNegativeDepartments() {
        (-10..-1).each {
            assertEquals(DepartmentMockDAO.generate(it).size(), 0)
        }
    }

    @Test
    void shouldReturnDepartmentsSpecifiedInConstructor() {
        DepartmentMockDAO departmentMockDAOTest
        (1..10).each {
            departmentMockDAOTest = new DepartmentMockDAO(it)
            assertEquals(departmentMockDAOTest.getDepartments("abc").size(), it)
        }
    }

    @Test
    void shouldReturnEmptyListForEmpty() {
        DepartmentMockDAO departmentMockDAOTest
        (1..10).each {
            departmentMockDAOTest = new DepartmentMockDAO(it)
            assertTrue(departmentMockDAOTest.getDepartments("empty").isEmpty())
        }
    }
}
