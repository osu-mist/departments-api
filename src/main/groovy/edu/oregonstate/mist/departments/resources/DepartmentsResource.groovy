package edu.oregonstate.mist.departments.resources

import com.codahale.metrics.annotation.Timed
import edu.oregonstate.mist.api.Resource
import edu.oregonstate.mist.api.jsonapi.ResultObject
import edu.oregonstate.mist.departments.db.DepartmentDAO
import groovy.transform.TypeChecked

import javax.annotation.security.PermitAll
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("departments")
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
@TypeChecked
class DepartmentsResource extends Resource {

    private DepartmentDAO departmentDAO

    DepartmentsResource(DepartmentDAO deptDAO) {
        this.departmentDAO = deptDAO
    }

    @Timed
    @GET
    Response getDepartments(@QueryParam('businessCenter') String businessCenter) {
        if (!businessCenter?.trim() || !departmentDAO.isValidBC(businessCenter)) {
            return badRequest("A valid businessCenter is required.").build()
        }

        ok(new ResultObject(
                data: departmentDAO.getDepartments(businessCenter).collect { it.toResourceObject() }
        )).build()
    }

}
