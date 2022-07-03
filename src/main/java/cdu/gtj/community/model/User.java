package cdu.gtj.community.model;

import org.springframework.stereotype.Component;

/**
 * 功能描述user
 *
 * @author wshg
 * @date 2022/07/02  1:47 PM
 */
@Component
public class User {
    private String name;
    private String AccountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", AccountId='" + AccountId + '\'' +
                ", token='" + token + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmtCreat() {
        return gmtCreate;
    }

    public void setGmtCreat(Long gmtCreat) {
        this.gmtCreate= gmtCreat;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
