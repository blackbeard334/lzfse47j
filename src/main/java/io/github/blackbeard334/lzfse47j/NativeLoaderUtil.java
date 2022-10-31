package io.github.blackbeard334.lzfse47j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class NativeLoaderUtil {
    private static final File        TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
    private static final Set<String> LOADED_LIBS    = new HashSet<>();
    private static final String      OS_NAME        = System.getProperty("os.name");

    static {
        if (OS_NAME.startsWith("Windows")) {
            loadFromResource("lzfse.dll");
            loadFromResource("lzfse_jni.dll");
        } else if (OS_NAME.equals("Linux")) {
            loadFromResource("liblzfse.so");
            loadFromResource("liblzfse_jni.so");
        } else {
            throw new UnsupportedOperationException(String.format("%s not supported.", OS_NAME));
        }
    }

    private NativeLoaderUtil() {
    }

    /**
     * TODO
     */
    static void load() {
        // magic!
    }

    private static void loadFromResource(final String libraryName) {
        if (!LOADED_LIBS.add(libraryName)) {
            return;
        }

        try (final InputStream inputStream = NativeLoaderUtil.class.getResourceAsStream("/" + libraryName)) {
            final File tempFile = new File(TEMP_DIRECTORY, libraryName);

            Files.copy(Objects.requireNonNull(inputStream), tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.load(tempFile.getAbsolutePath());

            tempFile.deleteOnExit();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
