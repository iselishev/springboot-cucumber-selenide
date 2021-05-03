package example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncryptorTest {

    @Test
    public void shouldThrowExceptionOnEmptyArgument() throws IllegalArgumentException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Encryptor.main(new String[]{}));
    }

    @Test
    public void shouldThrowExceptionOnMoreThanWroArguments() throws IllegalArgumentException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Encryptor.main(new String[]{"a", "b", "c"}));
    }

    @Test
    public void shouldEncryptWithDefaultEncryptionKey() {
        Encryptor.main(new String[]{"b"});
    }

    @Test
    public void shouldEncryptWithEncryptionKeyAndPasswordSet() {
        Encryptor.main(new String[]{"a", "b"});
    }


}
