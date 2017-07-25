package edu.oregonstate.mist.departments.db

import edu.oregonstate.mist.departments.core.Department

class DepartmentMockDAO {
    private static List<String> businessCenters = ["UABC", "AABC", "FOBC"]
    private static List<String> departments = ["HR Sample", "Finance Dept",
                                               "Mock Dept", "Audits"]
    public int departmentSize = 0

    DepartmentMockDAO(int departmentSize) {
        this.departmentSize = departmentSize
    }

    static List<Department> generate(int size) {
        List<Department> result = new ArrayList<>()
        def random = new Random()

        if (size) {
            size.times {
                result += new Department(
                        name: chooseName(),
                        businessCenter: chooseBusinessCenter(),
                        organizationCode: random.nextInt(999999)
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

    List<Department> getDepartments() {
        generate(departmentSize)
    }
}
