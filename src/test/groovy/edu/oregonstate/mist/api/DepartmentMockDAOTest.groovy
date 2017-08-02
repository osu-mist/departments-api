package edu.oregonstate.mist.api

import edu.oregonstate.mist.departments.core.Department
import edu.oregonstate.mist.departments.db.DepartmentMockDAO
import org.junit.Test
import static org.junit.Assert.*

class DepartmentMockDAOTest {
    @Test
    void shouldReturnEmptyList() {
        assert !DepartmentMockDAO.generate(0, null)
    }

    @Test
    void shouldGenerateManyDepartments() {
        (1..10).each {
            assertEquals(DepartmentMockDAO.generate(it, null).size(), it)
        }
    }

    @Test
    void shouldNotGenerateNegativeDepartments() {
        (-10..-1).each {
            assertEquals(DepartmentMockDAO.generate(it, null).size(), 0)
        }
    }

    @Test
    void shouldReturnDepartmentsSpecifiedInConstructor() {
        DepartmentMockDAO departmentMockDAOTest
        (1..10).each {
            departmentMockDAOTest = new DepartmentMockDAO(it)
            def departments = departmentMockDAOTest.getDepartments("abc")
            assertEquals(departments.size(), it)
            departments.each { assertEquals(it.businessCenter, "abc")}
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

    @Test
    void shouldGenerateOrganizationCodesInLimitedRange() {
        new DepartmentMockDAO(100).getDepartments("abc").each {
            def difference = Math.abs(Integer.valueOf(it.organizationCode) - 1111)
            assert(difference <= 100)
        }
    }

    @Test
    void organizationCodeShouldBeUnique() {
        def orgCodes = new DepartmentMockDAO(100).getDepartments("abc").organizationCode
        def uniqueOrgCodes = orgCodes.unique()

        assertEquals(orgCodes, uniqueOrgCodes)
    }

    @Test
    void invalidBcIsInvalid() {
        def mockDAO = new DepartmentMockDAO(1)
        assertFalse mockDAO.isValidBC("invalid-bc")
    }
}
