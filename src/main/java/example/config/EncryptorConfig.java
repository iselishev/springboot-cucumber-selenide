package example.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Slf4j
@Configuration
@EnableEncryptableProperties
public class EncryptorConfig {

    public static final String KEY = "enc_key";

    @Resource
    private Environment env;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(encConfig());
        return encryptor;
    }

    @Bean("encConfig")
    public SimpleStringPBEConfig encConfig() {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptionKey());
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations(1000);
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("hexadecimal");
        return config;
    }

    private String encryptionKey() {
        if (!isBlank(env.getProperty(KEY))) {
            log.info("Encryption key: ENVIRONMENT");
            return env.getProperty(KEY);
        }
        String encKeyLocation = env.getProperty("encryption.key.location");
        if (!isBlank(encKeyLocation)) {
            File file = new File(encKeyLocation);
            Properties properties = new Properties();
            try (FileInputStream inStream = new FileInputStream(file)) {
                properties.load(inStream);
                log.info("Encryption key: FILE");
                String encKey = properties.getProperty(KEY);
                if (isBlank(encKey)) {
                    throw new IllegalArgumentException("Encryption key not found in file ;" + encKeyLocation + ";");
                }
                return encKey;
            } catch (IOException e) {
                throw new IllegalArgumentException("Could not read file: '" + file.getPath() + ";", e);
            }
        }
        log.info("Encryption key: DEFAULT");
        return "windrunner";
    }
}
