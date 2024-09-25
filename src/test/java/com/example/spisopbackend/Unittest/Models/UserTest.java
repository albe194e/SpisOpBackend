package com.example.spisopbackend.Unittest.Models;

import com.example.spisopbackend.Exceptions.ValidationException;
import com.example.spisopbackend.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserTest {

    private User user = new User();

    private final String tooLongEmail257 = "a".repeat(245) + "@example.com";
    private final String tooLongEmail258 = "a".repeat(246) + "@example.com";
    private final String tooLongBeforeAtEmail66 = "a".repeat(66) + "@example.com";
    private final String tooLongBeforeAtEmail67 = "a".repeat(67) + "@example.com";

    //Firstname
    @ParameterizedTest
    @CsvSource({
        "Aa",
        "Bb",
        "Åå",
        "Øø",
    })
    void testValidFirstnameChars(String firstname){

        user.setFirstName(firstname);
        assertEquals(firstname, user.getFirstName());
    }

    @ParameterizedTest
    @CsvSource({
            "Al",
            "Alb",
            "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
    })
    void testValidFirstnameLength(String firstname){

        user.setFirstName(firstname);
        assertEquals(firstname, user.getFirstName());
    }

    @Test
    void testInvalidFirstnameLength(){
        assertThrows(ValidationException.class, () -> user.setFirstName(""));
        assertThrows(ValidationException.class, () -> user.setFirstName("A"));
    }

    @ParameterizedTest
    @CsvSource({
            "A1",
            "A2",
            "A8",
            "A9"
    })
    void testFirstnameWithNumber(String firstname){
        assertThrows(ValidationException.class, () -> user.setFirstName(firstname));
    }

    @ParameterizedTest
    @CsvSource({
            "A!",
            "A”",
            "A¤",
            "A("
    })
    void testFirstnameWithSpecialChars(String firstname){
        assertThrows(ValidationException.class, () -> user.setFirstName(firstname));
    }

    @Test
    void testEmptyFirstname(){
        assertThrows(ValidationException.class, () -> user.setFirstName(""));
    }

    @Test
    void testContainsSpace(){
        assertThrows(ValidationException.class, () -> user.setFirstName("Albert test"));
    }

    @ParameterizedTest
    @CsvSource({
            "albert",
            "daniel",
    })
    void testFirstLetterNotCapitalizedFirstname(String firstname){
        assertThrows(ValidationException.class, () -> user.setFirstName(firstname));
    }

    @ParameterizedTest
    @CsvSource({
            "ALbert",
            "DAniel",
            "ALBERT",
            "DANIEL"
    })
    void testOtherThanFirstLetterCapitalized(String firstname){
        assertThrows(ValidationException.class, () -> user.setFirstName(firstname));
    }

    @Test
    void testNullFirstname(){
        assertThrows(ValidationException.class, () -> user.setFirstName(null));
    }

    //Lastname
    @ParameterizedTest
    @CsvSource({
            "Aa",
            "Bb",
            "Åå",
            "Øø",
    })
    void testValidLastnameChars(String lastname){
        user.setLastName(lastname);
        assertEquals(lastname, user.getLastName());
    }

    @ParameterizedTest
    @CsvSource({
            "Al",
            "Alb",
            "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
    })
    void testValidLastnameLength(String lastname){
        user.setLastName(lastname);
        assertEquals(lastname, user.getLastName());
    }

    @Test
    void testInvalidLastnameLength(){
        assertThrows(ValidationException.class, () -> user.setLastName(""));
        assertThrows(ValidationException.class, () -> user.setLastName("A"));
    }

    @ParameterizedTest
    @CsvSource({
            "A1",
            "A2",
            "A8",
            "A9"
    })
    void testLastnameWithNumber(String lastname){
        assertThrows(ValidationException.class, () -> user.setLastName(lastname));
    }

    @ParameterizedTest
    @CsvSource({
            "A!",
            "A”",
            "A¤",
            "A("
    })
    void testLastnameWithSpecialChars(String lastname){
        assertThrows(ValidationException.class, () -> user.setLastName(lastname));
    }

    @Test
    void testEmptyLastname(){
        assertThrows(ValidationException.class, () -> user.setLastName(""));
    }

    @Test
    void testLastnameContainsSpace(){
        assertThrows(ValidationException.class, () -> user.setLastName("Albert test"));
    }

    @ParameterizedTest
    @CsvSource({
            "albert",
            "daniel",
    })
    void testFirstLetterNotCapitalizedLastname(String lastname){
        assertThrows(ValidationException.class, () -> user.setLastName(lastname));
    }

    @ParameterizedTest
    @CsvSource({
            "ALbert",
            "DAniel",
            "ALBERT",
            "DANIEL"
    })
    void testOtherThanFirstLetterCapitalizedLastname(String lastname){
        assertThrows(ValidationException.class, () -> user.setLastName(lastname));
    }

    @Test
    void testNullLastname(){
        assertThrows(ValidationException.class, () -> user.setLastName(null));
    }

    //Email
    @ParameterizedTest
    @CsvSource({
            "a@gmail.com",
            "b@gmail.com",
            "long.email@domain.com",
            "user@example.com"
    })
    void testValidEmailWithAtAndDot(String email) {
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @ParameterizedTest
    @CsvSource({
            "a-b@gmail.com",
            "user.name_123@domain.co.uk",
            "john.doe@company.org"
    })
    void testValidAlphanumericEmail(String email) {
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @ParameterizedTest
    @CsvSource({
            "a@c.com",
            "a@ab.com",
            "user@verylongdomainname.com",
            "info@example.net"
    })
    void testValidDomainLengthEmail(String email) {
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    void testMissingAtInEmail() {
        assertThrows(ValidationException.class, () -> user.setEmail("a.com"));
    }

    @Test
    void testMissingDotInEmail() {
        assertThrows(ValidationException.class, () -> user.setEmail("a@com"));
    }

    @Test
    void testTooLongEmail() {
        assertThrows(ValidationException.class, () -> user.setEmail(tooLongEmail257));
        assertThrows(ValidationException.class, () -> user.setEmail(tooLongEmail258));
    }

    @Test
    void testTooLongBeforeAtEmail() {
        assertThrows(ValidationException.class, () -> user.setEmail(tooLongBeforeAtEmail66));
        assertThrows(ValidationException.class, () -> user.setEmail(tooLongBeforeAtEmail67));
    }

    @ParameterizedTest
    @CsvSource({
            "user!@example.com",
            "user@exam ple.com",
    })
    void testInvalidCharsEmail(String email) {
        assertThrows(ValidationException.class, () -> user.setEmail(email));
    }

    @Test
    void testMultipleAtInEmail() {
        assertThrows(ValidationException.class, () -> user.setEmail("a@@"));
    }

    @Test
    void testEmptyEmail() {
        assertThrows(ValidationException.class, () -> user.setEmail(""));
    }

    @Test
    void testNullEmail() {
        assertThrows(ValidationException.class, () -> user.setEmail(null));
    }

    //ProfilePicture
    @ParameterizedTest
    @CsvSource({
            "https://example.com/pic.jpg",
            "http://longurlexample.com/profile/picture.png",
    })
    void testStartsWithHttpsOrHttp(String profilePicture) {
        user.setProfilePicture(profilePicture);
        assertEquals(profilePicture, user.getProfilePicture());
    }

    @ParameterizedTest
    @CsvSource({
            "https://site.com/img.jpg",
            "https://site.com/img.jpeg",
            "https://site.com/img.png",
            "https://site.com/img.gif",
    })
    void testProfilepictureValidFormat(String profilePicture) {
        user.setProfilePicture(profilePicture);
        assertEquals(profilePicture, user.getProfilePicture());
    }

    @ParameterizedTest
    @CsvSource({
            "www.example.com/pic.jpg",
            "www.example.com/pic.png",
    })
    void testInvalidProtocol(String profilePicture) {
        assertThrows(ValidationException.class, () -> user.setProfilePicture(profilePicture));
    }

    @ParameterizedTest
    @CsvSource({
            "www.example.com/pic.pn",
            "www.example.com/pic.jp",
            "www.example.com/pic.what",
            "www.example.com/pic.holysmokes",
            "www.example.com/pic.tft",
    })
    void testInvalidFileExtension(String profilePicture) {
        assertThrows(ValidationException.class, () -> user.setProfilePicture(profilePicture));
    }

    //Username
    @ParameterizedTest
    @CsvSource({
            "user",
            "johny",
            "johndoe123",
            "verylongusername12345678906364",
            "verylongusername1234567890636",
            "username123",
    })
    void testValidUsernameLength(String username) {
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @ParameterizedTest
    @CsvSource({
            "user1",
            "User2",
            "JohnDoe123",
            "åÅ123abc",
            "User123"
    })
    void testValidUsernameChars(String username) {
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    void testEmptyAndNullUsername() {
        assertThrows(ValidationException.class, () -> user.setUsername(""));
        assertThrows(ValidationException.class, () -> user.setUsername(null));
    }

    @ParameterizedTest
    @CsvSource({
            "a",
            "aa",
            "aaa",
            "thisisaverylongusernameoverthth",
            "thisisaverylongusernameoverththg"
    })
    void testInvalidLengthUsername(String username) {
        assertThrows(ValidationException.class, () -> user.setUsername(username));
    }

    @ParameterizedTest
    @CsvSource({
            "user@123",
            "john_doe",
            "user-name",
            "user.name",
    })
    void testInvalidCharsUsername(String username) {
        assertThrows(ValidationException.class, () -> user.setUsername(username));
    }
}
