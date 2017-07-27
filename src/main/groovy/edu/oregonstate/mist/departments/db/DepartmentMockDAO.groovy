package edu.oregonstate.mist.departments.db

import edu.oregonstate.mist.departments.core.Department

class DepartmentMockDAO extends DeptDAO implements DepartmentDAO {
    private static List<String> businessCenters = ["UABC", "AABC", "FOBC"]
    private static List<String> departments = ["HR Sample", "Finance Dept",
                                               "Mock Dept", "Audits"]
    public int departmentSize = 0

    DepartmentMockDAO(int departmentSize) {
        this.departmentSize = departmentSize
    }

    static List<Department> generate(int size, String businessCenter) {
        List<Department> result = new ArrayList<>()

        if (size) {
            size.times {
                result += new Department(
                        name: chooseName(),
                        businessCenter: businessCenter ?: chooseBusinessCenter(),
                        organizationCode: 1111 + it
                )
            }
        }
        result
    }

    private static String chooseBusinessCenter() {
        def random = new Random()
        businessCenters[random.nextInt(businessCenters.size())]
    }

    private static String chooseName() {
        def random = new Random()
        departments[random.nextInt(departments.size())] + " " +
                random.nextInt(111)
    }

    List<Department> getDepartments(String businessCenter) {
        if (businessCenter == "empty") {
            return generate(0, null)
        }

        generate(departmentSize, businessCenter)
    }

    @Override
    void close() { }

    @Override
    Integer checkHealth() {
        return null
    }
}
