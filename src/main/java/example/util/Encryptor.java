package example.util;

import example.config.EncryptorConfig;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Encryptor {

    public static void main(String[] args) {
        if (args ==null || args.length == 0) {
            throw new IllegalArgumentException("No encryptionKey & textToEncrypt have been provided");
        } else if (args.length > 2) {
            throw new IllegalArgumentException("Unsupported amount of arguents, expected: encryptionKey(optional) textToEncrypt");
        }
        if (args.length == 1) {
            log.warn("Encryption key is not set, the default onw will be used");
            StringEncryptor encryptor = getStringEncryptor();
            log.info(encryptor.encrypt(args[0]));
        } else {
            System.setProperty(EncryptorConfig.KEY, args[0]);
            StringEncryptor encryptor = getStringEncryptor();
            log.info(encryptor.encrypt(args[1]));
        }
    }

    private static StringEncryptor getStringEncryptor() {
        ApplicationContext context = new AnnotationConfigApplicationContext(EncryptorConfig.class);
        return context.getBean("jasyptStringEncryptor", StringEncryptor.class);

    }

}
