package com.soundz;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration test for App.
 */
public class AppITest {

    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(sysOut);
    }

    @Test
    public void should_succeed_for_given_example_input() {
        // Given
        String[] args = {getResourcesPath("first_example_input.txt")};
        // When
        App.main(args);
        // Then
        assertThat(outContent.toString(), is("1 3 N\n5 1 E\n"));
    }

    @Test
    public void should_succeed_for_input_with_blank_lines() {
        // Given
        String[] args = {getResourcesPath("blank_lines.txt")};
        // When
        App.main(args);
        // Then
        assertThat(outContent.toString(), is("1 3 N\n5 1 E\n"));
    }

    @Test
    public void should_succeed_for_input_with_white_spaces() {
        // Given
        String[] args = {getResourcesPath("white_spaces.txt")};
        // When
        App.main(args);
        // Then
        assertThat(outContent.toString(), is("1 3 N\n5 1 E\n"));
    }

    @Test
    public void second_mower_should_be_blocked_by_first_mower() {
        // Given
        String[] args = {getResourcesPath("second_mower_blocked.txt")};
        // When
        App.main(args);
        // Then
        assertThat(outContent.toString(), is("1 3 N\n1 2 N\n"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_for_lawn_bad_format() {
        // Given
        String[] args = {getResourcesPath("lawn_bad_format.txt")};
        // When
        App.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_for_mower_bad_format() {
        // Given
        String[] args = {getResourcesPath("mower_bad_format.txt")};
        // When
        App.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_for_instructions_bad_format() {
        // Given
        String[] args = {getResourcesPath("instructions_bad_format.txt")};
        // When
        App.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_mower_move_on_full_lawn() {
        // Given
        String[] args = {getResourcesPath("full_lawn.txt")};
        // When
        App.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_input_file_has_not_enough_data() {
        // Given
        String[] args = {getResourcesPath("not_enough_data.txt")};
        // When
        App.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_input_file_path_does_not_exist() {
        // Given
        String[] args = {"does_not_exist.txt"};
        // When
        App.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_input_file_is_empty() {
        // Given
        String[] args = {getResourcesPath("empty_file.txt")};
        // When
        App.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_fail_when_no_args() {
        // Given
        String[] args = {};
        // When
        App.main(args);
    }


    private String getResourcesPath(String testFile) {
        return AppITest.class.getClassLoader().getResource(testFile).getPath();
    }
}
