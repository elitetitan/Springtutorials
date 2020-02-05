package com.bfm.training.jdbc;


public class Employee {
    private Integer empno;
    private String ename;
    private Integer mgrid;
    private Integer sal;
    private Integer comm;

    public Employee(){
        
    }
    public Employee(Integer empNo, String eName, Integer mgrId, Integer sal, Integer comm){
        this.empno = empNo;
        this.ename = eName;
        this.mgrid = mgrId;
        this.sal = sal;
        this.comm = comm;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getMgrid() {
        return mgrid;
    }

    public void setMgrid(Integer mgrid) {
        this.mgrid = mgrid;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo=" + empno +
                ", eName='" + ename + '\'' +
                ", mgrId=" + mgrid +
                ", sal=" + sal +
                ", comm=" + comm +
                '}';
    }
}