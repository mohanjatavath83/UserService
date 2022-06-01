package com.userservice.model.user;


public enum Role {
    ROLE_ADMINGROUP("ROLE_ADMINGROUP"),
    ROLE_EMPLOYEEGROUP("ROLE_EMPLOYEEGROUP"),
    ROLE_CUSTOMERGROUP("ROLE_CUSTOMERGROUP");

    private String roleGroup;

    private Role(String group) {
        this.roleGroup = group;
    }

    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }
}
