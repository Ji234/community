package cdu.gtj.community.dto;

import org.springframework.stereotype.Component;

/**
 * 功能描述
 *
 * @author wshg
 * @date 2022/07/01  10:32 AM
 */
@Component
public class AccessTokenDTO {
    private String client_id;
    private String redirect_uri;
    private String code;
    private String state;
    private String client_secret;
    //getter setter 下面是

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}
