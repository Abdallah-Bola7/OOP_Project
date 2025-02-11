package service;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validateUserName(String userName) {

        // TODO return true if userName start with upper case and length must be greater than or equal 3
        if (userName.length() <  3 ) {
            System.out.println("Username must be greater than or equal 3.");
            return false;
        }
        if (!userName.matches("[a-zA-Z0-9]+")) {
            System.out.println("Username can only contain letters and numbers.");
            return false;
        }
        if (userName.contains(" ")) {
            System.out.println("Username cannot contain spaces.");
            return false;
        }
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        // TODO return true if password contain number , uppercaseChar, lowerCase char, size greater than or equal 6 and special char
        if (password.length() < 6 ) {
            System.out.println("Password must be at least6 characters long.");
            return false;
        }
        if(password.contains("[a-z]+") && password.contains("[A-Z]+") && password.contains("[0-9]+") ){
            System.out.println("Password must contain number , uppercaseChar, lowerCase char, size greater than or equal 6");
            return false;
        }
        if (password.matches("!^.*[@#$%,.?\":{}&*()|<>].*")) {
            System.out.println("Password must contain at least one special character.");
            return false;
        }
        return true;
    }
}
