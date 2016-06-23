package entity;

import javax.persistence.*;

/**
 * Created by sweet on 15-6-3.
 */
@Entity
@Table(name = "verify_email", schema = "", catalog = "j2ee")
public class VerifyEmailentity {
    private String email;
    private String token;
    private String expire;

    public VerifyEmailentity(){}

    public VerifyEmailentity(String email, String token, String expire) {
        this.email = email;
        this.token = token;
        this.expire = expire;
    }

    @Id
    @Column(name = "email", nullable = false, insertable = true, updatable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "token", nullable = false, insertable = true, updatable = true, length = 32)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "expire", nullable = false, insertable = true, updatable = true, length = 10)
    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerifyEmailentity that = (VerifyEmailentity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (expire != null ? !expire.equals(that.expire) : that.expire != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (expire != null ? expire.hashCode() : 0);
        return result;
    }

}
