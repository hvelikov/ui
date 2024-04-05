package bg.comsoft.servlet;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;


// https://quarkus.io/guides/security-authentication-mechanisms#oauth2-authentication
@Named
@RequestScoped
public class LoginBean {

    @NotNull
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters")
    private String username;

    @NotNull
    @Size(min = 5, max = 50, message = "Password must be between 5 and 50 characters")
    private String password;

    public void login() {

        FacesContext context = FacesContext.getCurrentInstance();

        context.responseComplete();

       /* Credential credential = new UsernamePasswordCredential(username, new Password(password));

        AuthenticationStatus status = securityContext.authenticate(
                getRequest(context),
                getResponse(context),
                withParams()
                        .credential(credential));
        Log.info("authentication result:" + status);

        if (status.equals(SEND_CONTINUE)) {
            // Authentication mechanism has send a redirect, should not
            // send anything to response from JSF now.
            context.responseComplete();
        } else if (status.equals(SEND_FAILURE)) {
            addError(context, "Authentication failed");
        }
*/
    }

    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context
                .getExternalContext()
                .getResponse();
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context
                .getExternalContext()
                .getRequest();
    }

    private static void addError(FacesContext context, String message) {
        context
                .addMessage(
                        null,
                        new FacesMessage(SEVERITY_ERROR, message, null));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}