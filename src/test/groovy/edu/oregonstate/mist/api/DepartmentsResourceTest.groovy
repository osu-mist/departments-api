package edu.oregonstate.mist.api

import edu.oregonstate.mist.api.jsonapi.ResourceObject
import edu.oregonstate.mist.api.jsonapi.ResultObject
import edu.oregonstate.mist.departments.db.DepartmentMockDAO
import edu.oregonstate.mist.departments.db.DeptDAO
import edu.oregonstate.mist.departments.resources.DepartmentsResource
import static org.junit.Assert.*
import org.junit.Before
import org.junit.Test

import javax.ws.rs.core.Response

class DepartmentsResourceTest {
    public static final int DATA_SIZE = 10
    DepartmentsResource departmentsResource

    @Test
    void shouldListAllDepartments() {
        Response response = departmentsResource.getDepartments("bcName")
        assertNotNull(response)
        assertEquals(response.getEntity().class, ResultObject.class)
        assertEquals(response.status, 200)

        ResultObject resultObject = response.getEntity()
        assertNotNull(resultObject.data)
        assertEquals(resultObject.data.class, ArrayList.class)
        assertEquals(resultObject.data.size(), DATA_SIZE)

        resultObject.data.each {
            assertEquals(it.class, ResourceObject.class)
        }
    }

    @Test
    void shouldReturn400ForEmptyList() {
        Response response = departmentsResource.getDepartments("empty")
        assertNotNull(response)
        assertEquals(response.getEntity().class, Error.class)
        assertEquals(response.status, 400)
    }

    @Test
    void shouldRequireBusinessCenter() {
        Response response = departmentsResource.getDepartments("")
        assertNotNull(response)
        assertEquals(response.status, 400)
        assertEquals(response.getEntity().class, Error.class)
    }

    @Test
    void shouldValidateBusinessCenter() {
        Response response = departmentsResource.getDepartments("invalid-bc")
        assertNotNull(response)
        assertEquals(response.status, 400)
        assertEquals(response.getEntity().class, Error.class)
    }

    @Before
    void setup() {
        DeptDAO deptDAO = new DepartmentMockDAO(DATA_SIZE)
        departmentsResource = new DepartmentsResource(deptDAO)
    }
}
