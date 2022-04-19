package com.bidgelabz;


import com.bridgelabz.Employee;
import com.bridgelabz.EmployeePayRollService;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

/**
 * Purpose: Class EmployeePayRollTest for perform Testcases.
 *
 * @author Aniket Kumbhar
 * @version 1.0
 * @since 18-03-2022
 *
 */

public class EmployeePayRollTest {
    EmployeePayRollService employeePayRollService = new EmployeePayRollService();

    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        String sql = "select * from employee_payroll";
        List<Employee> employeePayrollDataList = employeePayRollService.queryExecute(sql);
        Assert.assertEquals(5, employeePayrollDataList.size());
    }

    @Test
    public void givenUpdatingTerisaBasicPay_whenUpdate_ShouldReturnUpdatedPay() {
        double BasicPay = 3000000;
        String Name = "Terisa";
        double salaryUpdated = employeePayRollService.updateBasicPay(Name, BasicPay);
        Assert.assertEquals(BasicPay, salaryUpdated,3000000);
    }

    @Test
    public void givenUpdatingRahulBasicPay_whenUpdate_ShouldReturnUpdatedPay() {
        double BasicPay = 800000;
        String Name = "rahul";
        double salaryUpdated = employeePayRollService.updateBasicPay(Name, BasicPay);
        Assert.assertEquals(BasicPay, salaryUpdated,800000);
    }

}