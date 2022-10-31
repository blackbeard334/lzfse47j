package io.github.blackbeard334.lzfse47j;

/**
 * Utility class for compressing &amp; decompressing <a href="https://en.wikipedia.org/wiki/LZFSE">LZFSE (Lempel–Ziv Finite State Entropy)</a>.
 */
public final class LzfseUtil {
    static {
        NativeLoaderUtil.load();
    }

    private LzfseUtil() {
    }

    /**
     * Decompress a buffer using LZFSE.
     *
     * @param srcBuffer A byte array of the source buffer.
     * @param srcSize   Size of the source buffer in bytes.
     * @param dstBuffer A byte array to write the decompressed bytes to.
     * @param dstSize   Size of the destination buffer in bytes.
     * @return The number of bytes written to the destination buffer if the input is
     * successfully decompressed. If there is not enough space in the destination
     * buffer to hold the entire expanded output, only the first {@code dstSize} bytes
     * will be written to the buffer and {@code dstSize} is returned.
     */
    public static native long decodeBuffer(byte[] srcBuffer, long srcSize, byte[] dstBuffer, long dstSize);

    /**
     * Compress a buffer using LZFSE.
     *
     * @param srcBuffer A byte array of the source buffer.
     * @param srcSize   Size of the source buffer in bytes.
     * @param dstBuffer A byte array to write the decompressed bytes to.
     * @param dstSize   Size of the destination buffer in bytes.
     * @return The number of bytes written to the destination buffer if the input is
     * successfully compressed. If the input cannot be compressed to fit into
     * the provided buffer, or an error occurs, <b>zero is returned</b>, and the
     * contents of {@code dstBuffer} are <u>unspecified</u>.
     */
    public static native long encodeBuffer(byte[] srcBuffer, long srcSize, byte[] dstBuffer, long dstSize);
}