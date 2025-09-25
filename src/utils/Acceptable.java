package utils;

public interface Acceptable {
    String STUDENT_ID = "^[CcDdHhSsQq][Ee]\\d{6}$";   // start with campus code (SE, HE, DE, QE, CE)
    String NAME_VALID = "^.{2,20}$";                  // 2–20  characters
    String PHONE_VALID = "^[0-9]{10}$";               // 10 digits
    String EMAIL_VALID = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"; //email

    //regex phone nums network Vietnam
    String VIETTEL_VALID = "^(086|096|097|098|03[2-9]|09[6-8])\\d{7}$";
    String VNPT_VALID = "^(081|082|083|084|085|088|091|094)\\d{7}$";

    static boolean isValid(String data, String regex) {
        if (data == null) return false;
        return data.matches(regex);
    }


}
