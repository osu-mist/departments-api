package edu.oregonstate.mist.departments.db

import edu.oregonstate.mist.departments.core.Department
import edu.oregonstate.mist.departments.department.DepartmentMapper
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.SqlQuery
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(DepartmentMapper)
public interface DepartmentDAO extends Closeable  {
    @SqlQuery("SELECT 1 FROM dual")
    Integer checkHealth()

    @SqlQuery(AbstractDepartmentDAO.getDepartments)
    List<Department> getDepartments(@Bind("businessCenter") String businessCenter)

    @Override
    void close()
}
