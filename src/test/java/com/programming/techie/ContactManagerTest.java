package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.*;
class ContactManagerTest {
    ContactManager contactManager;
    @BeforeEach
    public void setup(){
         this.contactManager = new ContactManager();
    }

    @Test
    public void shouldCreateContact(){
        Contact contact = new Contact("tom","easle","0922012121");
        contactManager.addContact(contact);
        Assertions.assertTrue(contactManager.getAllContacts().contains(contact));
    }

    @Test
    public void shouldThrowExceptionForExistingContact(){
        Contact contact = new Contact("tom","easle","0922012121");
        contactManager.addContact(contact);
        Assertions.assertThrows(Exception.class,()->{
            contactManager.addContact(contact);
        });
    }
    @DisplayName("display shouldTestContactCreationUsingValueSource")
    @ParameterizedTest
    @ValueSource(strings = {"0922012121","0922012122"})
    public void shouldTestContactCreationUsingValueSource(String phoneNum){
        Contact contact = new Contact("tom","easle",phoneNum);
        Assertions.assertTrue(contactManager.contactList.isEmpty());
        contactManager.addContact(contact);
        Assertions.assertFalse(contactManager.contactList.isEmpty());
    }

}
@Nested
class RepeatedTests {
    @DisplayName("Repeat Contact Creation Test 5 Times")
    @RepeatedTest(value = 5,
            name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
    public void shouldTestContactCreationRepeatedly() {
        contactManager.addContact("John", "Doe", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
}

@Nested
class ParameterizedTests {
    @DisplayName("Phone Number should match the required Format")
    @ParameterizedTest
    @ValueSource(strings = {"0123456789", "0123456798", "0123456897"})
    public void shouldTestPhoneNumberFormatUsingValueSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("CSV Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @CsvSource({"0123456789", "0123456798", "0123456897"})
    public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @DisplayName("CSV File Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void shouldTestPhoneNumberFormatUsingCSVFileSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }
}

    @DisplayName("Method Source Case - Phone Number should match the required Format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
        contactManager.addContact("John", "Doe", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    private static List<String> phoneNumberList() {
        return Arrays.asList("0123456789", "0123456798", "0123456897");
    }

    @Test
    @DisplayName("Test Should Be Disabled")
    @Disabled
    public void shouldBeDisabled() {
        throw new RuntimeException("Test Should Not be executed");
    }


    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("Should be executed at the end of the Test");
    }
}
