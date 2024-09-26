package com.example.spisopbackend.Unittest.Models;

import com.example.spisopbackend.Exceptions.ValidationException;
import com.example.spisopbackend.model.FoodPost;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FoodPostTest {

    private FoodPost foodPost = new FoodPost();

    //Title
    @ParameterizedTest
    @CsvSource({
            "Aaa",
            "Bbb",
            "Ååå",
            "Øøø",
            "Mmm",
            "Nnn",
            "Spicy!",
            "Spicy#",
            "Yum-Yum",
            "Sushi(Fresh)",
            "Aaa1",
            "Aaa2",
            "Aaa8",
            "Aaa9",
    })
    void testValidChars(String title){

        foodPost.setTitle(title);
        assertEquals(title, foodPost.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "P",
            "pi",
            "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "Delicious Pasta with Tomato Sauce",
    })
    void testValidLength(String title){

        foodPost.setTitle(title);
        assertEquals(title, foodPost.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "Delicious Pasta with Tomato Sauce",
            "Spicy Tacos",
            "Vegetarian Pizza",
            "Grilled Salmon",
            "Grilled        Salmon",
    })
    void testValidSpaces(String title){

        foodPost.setTitle(title);
        assertEquals(title, foodPost.getTitle());
    }

    @ParameterizedTest
    @CsvSource({
            "Pizza \uD83C\uDF55",
            "Burger \uD83C\uDF54",
            "Sushi \uD83C\uDF63",
            "Spicy \uD83C\uDF36\uFE0F Tacos",
            "\uD83C\uDF53\uD83C\uDF4C\uD83E\uDD5D",
            "\uD83C\uDF55\uD83C\uDF54\uD83C\uDF2D\uD83C\uDF5F",
    })
    void testEmojis(String title){

        foodPost.setTitle(title);
        assertEquals(title, foodPost.getTitle());
    }

    @Test
    void testValidEmojiLength() {

        String title = "\uD83C\uDF55".repeat(50);
        foodPost.setTitle(title);
        assertEquals(title, foodPost.getTitle());

        title = "\uD83C\uDF55".repeat(49);
        foodPost.setTitle(title);
        assertEquals(title, foodPost.getTitle());

        // 3 emojis
        title = "\uD83C\uDF55".repeat(1);
        foodPost.setTitle(title);
        assertEquals(title, foodPost.getTitle());

        //4 emojis
        title = "\uD83C\uDF55".repeat(2);
        foodPost.setTitle(title);
        assertEquals(title, foodPost.getTitle());
    }

    @Test
    void testInvalidEmojiLength() {

        assertThrows(ValidationException.class, () -> foodPost.setTitle("\uD83C\uDF55".repeat(51)));
        assertThrows(ValidationException.class, () -> foodPost.setTitle("\uD83C\uDF55".repeat(52)));
    }

    @Test
    void testInvalidEmptyTitleWhitespaces(){

        assertThrows(ValidationException.class, () -> foodPost.setTitle("   "));
        assertThrows(ValidationException.class, () -> foodPost.setTitle("    "));
        assertThrows(ValidationException.class, () -> foodPost.setTitle("     "));
    }


    @Test
    void testInvalidNullTitle() {
        assertThrows(ValidationException.class, () -> foodPost.setTitle(null));
    }

    @ParameterizedTest
    @CsvSource({
            "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    })
    void testInvalidTitleLength(String title){
        assertThrows(ValidationException.class, () -> foodPost.setTitle(title));
    }

    //Description
    @ParameterizedTest
    @CsvSource({
            "a",
            "ab",
            "\uD83C\uDF55",
            "\uD83C\uDF55\uD83C\uDF55"
    })
    void testValidDescriptionLengthLower(String description){
        foodPost.setDescription(description);
        assertEquals(description, foodPost.getDescription());
    }

    @Test
    void testValidDescriptionLengthUpper(){
        String description = "a".repeat(255);
        foodPost.setDescription(description);
        assertEquals(description, foodPost.getDescription());

        description = "a".repeat(254);
        foodPost.setDescription(description);
        assertEquals(description, foodPost.getDescription());

        description = "a".repeat(200) + "\uD83C\uDF55".repeat(55);
        foodPost.setDescription(description);
        assertEquals(description, foodPost.getDescription());

        description = "a".repeat(200) + "\uD83C\uDF55".repeat(54);
        foodPost.setDescription(description);
        assertEquals(description, foodPost.getDescription());

        description = "\uD83C\uDF55".repeat(255);
        foodPost.setDescription(description);
        assertEquals(description, foodPost.getDescription());
    }
}
