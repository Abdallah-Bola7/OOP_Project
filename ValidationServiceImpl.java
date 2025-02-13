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
        // Check length
        if (password.length() < 6) {
            System.out.println("Password must be at least 6 characters long.");
            return false;
        }

        // Check for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }

        // Check for at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }

        // Check for at least one digit
        if (!password.matches(".*[0-9].*")) {
            System.out.println("Password must contain at least one number.");
            return false;
        }

        // Check for at least one special character
        if (!password.matches(".*[_!@#$%^&*(),.?\":{}|<>].*")) {
            System.out.println("Password must contain at least one special character.");
            return false;
        }

        return true; // If all conditions pass, the password is valid.
    }

}
