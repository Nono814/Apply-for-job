package com.example.hello.dto;

/**
 * 职位详情响应DTO
 */
public class JobDetailResponse {
    private String jobTitle;
    private String companyName;
    private String employmentType;
    private String workStyle;
    private String salaryRange;
    private String companyLogo;

    // 构造函数
    public JobDetailResponse() {}

    public JobDetailResponse(String jobTitle, String companyName, String employmentType, 
                           String workStyle, String salaryRange, String companyLogo) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.employmentType = employmentType;
        this.workStyle = workStyle;
        this.salaryRange = salaryRange;
        this.companyLogo = companyLogo;
    }

    // Getter和Setter方法
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    @Override
    public String toString() {
        return "JobDetailResponse{" +
                "jobTitle='" + jobTitle + '\'' +
                ", companyName='" + companyName + '\'' +
                ", employmentType='" + employmentType + '\'' +
                ", workStyle='" + workStyle + '\'' +
                ", salaryRange='" + salaryRange + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                '}';
    }
} 