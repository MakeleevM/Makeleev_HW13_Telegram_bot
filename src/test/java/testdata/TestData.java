package testdata;

import com.github.javafaker.Faker;

public class TestData {
    private final Faker faker = new Faker();

    public static String messageAfterSubmitting = "Thanks for submitting the form";
    public static String messagePracticeForm = "Student Registration Form";
    public final String userName = faker.name().username();
    public final String firstName = faker.name().firstName();
    public final String lastName = faker.name().lastName();
    public final String userEmail = faker.internet().emailAddress();
    public final String genderWrapper = faker.options().option("Male", "Female", "Other");
    public final String userNumber = faker.phoneNumber().subscriberNumber(10);
    public final String monthNumber = String.format("%02d", faker.number().numberBetween(1, 12));
    public final String month = getMonthName(monthNumber);
    public final String year = String.valueOf(faker.number().numberBetween(1980, 2010));
    public final String day = String.format("%02d", faker.number().numberBetween(1, 28));
    public final String subjects = faker.options().option("Maths", "Arts", "Biology", "Physics", "Chemistry",
            "Commerce", "History", "English", "Computer Science", "Economics", "Civics");
    public final String hobbies = faker.options().option("Sports", "Reading", "Music");
    public final String uploadfile = faker.options().option("1.png", "2.png", "3.png");
    public final String currentAddress = faker.address().fullAddress();
    public final String permanentAddress = faker.address().streetAddress();
    public final String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public final String city = selectCity(state);
    public final String wrongEmail = faker.options().option("invalid-email", "user@", "@nodomain.com",
            "user name@test.com", "user@test", "user@@test.com", "user@test.c");
    public final String wrongNumber = faker.options().option("123", "abc-def-ghij", "12-34-56",
            "999999999999999", "-%%%%", "");

    private String getMonthName(String monthNumber) {
        return switch (monthNumber) {
            case "01" -> "January";
            case "02" -> "February";
            case "03" -> "March";
            case "04" -> "April";
            case "05" -> "May";
            case "06" -> "June";
            case "07" -> "July";
            case "08" -> "August";
            case "09" -> "September";
            case "10" -> "October";
            case "11" -> "November";
            case "12" -> "December";
            default -> "January";
        };
    }

    private String selectCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> "Delhi";
        };
    }
}