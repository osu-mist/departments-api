package edu.oregonstate.mist.departments.resources

import com.codahale.metrics.annotation.Timed
import edu.oregonstate.mist.api.Resource
import edu.oregonstate.mist.api.jsonapi.ResourceObject
import edu.oregonstate.mist.api.jsonapi.ResultObject
import edu.oregonstate.mist.departments.db.DeptDAO
import groovy.transform.TypeChecked
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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

    Logger logger = LoggerFactory.getLogger(DepartmentsResource.class)
    private DeptDAO deptDAO

    DepartmentsResource(DeptDAO deptDAO) {
        this.deptDAO = deptDAO
    }

    @Timed
    @GET
    Response getDepartments(@QueryParam('businessCenter') String businessCenter) {
        ok(new ResultObject(
                data: deptDAO.getDepartments(businessCenter)
        )).build()
    }

}
