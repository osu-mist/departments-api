package edu.oregonstate.mist.departments.department

import edu.oregonstate.mist.departments.core.Department
import edu.oregonstate.mist.departments.db.AbstractDepartmentDAO
import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper

import java.sql.ResultSet
import java.sql.SQLException

class DepartmentMapper implements ResultSetMapper<Department> {
    public Department map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        new Department(
                name: rs.getString(AbstractDepartmentDAO.mapperColumnName),
                businessCenter: rs.getString(AbstractDepartmentDAO.mapperColumnBusinessCenter),
                organizationCode: rs.getString(AbstractDepartmentDAO.mapperColumnOrganizationCode)
        )
    }

}
