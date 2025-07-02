package com.example.hello.entity;

/**
 * 职位实体类
 */
public class Job {
    private Integer id;
    private Integer companyId;
    private String title;
    private String employmentType;
    private String workStyle;
    private Integer salaryMin;
    private Integer salaryMax;
    private String salaryCurrency;
    private String salaryPeriod;
    
    // 关联的公司信息
    private Company company;

    // 构造函数
    public Job() {}

    // Getter和Setter方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getWorkStyle() {
        return workStyle;
    }

    public void setWorkStyle(String workStyle) {
        this.workStyle = workStyle;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public String getSalaryPeriod() {
        return salaryPeriod;
    }

    public void setSalaryPeriod(String salaryPeriod) {
        this.salaryPeriod = salaryPeriod;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", title='" + title + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", workStyle='" + workStyle + '\'' +
                ", salaryMin=" + salaryMin +
                ", salaryMax=" + salaryMax +
                ", salaryCurrency='" + salaryCurrency + '\'' +
                ", salaryPeriod='" + salaryPeriod + '\'' +
                ", company=" + company +
                '}';
    }
} 