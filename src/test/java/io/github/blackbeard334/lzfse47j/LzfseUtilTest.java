package io.github.blackbeard334.lzfse47j;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LzfseUtilTest {

    @Test
    void test_lzfse_encode_decode_buffer() throws IOException {
        final byte[] randomBytes = new byte[100_000_000];
        final byte[] compressBuffer = new byte[randomBytes.length << 2];
        new Random().nextBytes(randomBytes);

        final long l1 = LzfseUtil.encodeBuffer(randomBytes, randomBytes.length, compressBuffer, compressBuffer.length);
        final byte[] compressedRandomBytes = Arrays.copyOf(compressBuffer, (int) l1);
        assertNotEquals(0, l1);

        final byte[] decompressBuffer = new byte[randomBytes.length];
        final long l2 = LzfseUtil.decodeBuffer(compressedRandomBytes, compressedRandomBytes.length, decompressBuffer, decompressBuffer.length);
        assertNotEquals(0, l2);

        assertArrayEquals(randomBytes, decompressBuffer);
    }
}