package org.kvlt.core.models;

public class AuthParams extends ModelParams {

    @Override
    public String selectSQL() {
        return "SELECT * FROM authentication WHERE id = :id";
    }

    @Override
    protected void fillCols() {
        map("registration_ip", "registerIp");
        map("email_confirmed", "isEmailConfirmed");
        map("email_confirmation_code", "emailConfirmationCode");
        map("email_code_timestamp", "emailCodeTimestamp");
        map("last_authenticated", "lastAuth");
    }
}
