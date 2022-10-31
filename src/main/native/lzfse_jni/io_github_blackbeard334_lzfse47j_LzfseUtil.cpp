#include <jni.h>
#include "lzfse/src/lzfse.h"

#ifndef _Included_io_github_blackbeard334_lzfse47j_LzfseUtil
#define _Included_io_github_blackbeard334_lzfse47j_LzfseUtil
#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jlong JNICALL Java_io_github_blackbeard334_lzfse47j_LzfseUtil_decodeBuffer
        (JNIEnv *env, jobject obj, jbyteArray src, jlong src_len, jbyteArray dst, jlong dst_len) {
    jboolean src_isCopy;
    jboolean dst_isCopy;
    jbyte *temp_src = env->GetByteArrayElements(src, &src_isCopy);
    jbyte *temp_dst = env->GetByteArrayElements(dst, &dst_isCopy);

    size_t out_size = lzfse_decode_buffer((uint8_t *) temp_dst, dst_len,
                                          (uint8_t *) temp_src, src_len,
                                          NULL);


    env->ReleaseByteArrayElements(src, temp_src, src_isCopy ? 0 : JNI_ABORT);
    env->ReleaseByteArrayElements(dst, temp_dst, dst_isCopy ? 0 : JNI_ABORT);
    return out_size;
}

JNIEXPORT jlong JNICALL Java_io_github_blackbeard334_lzfse47j_LzfseUtil_encodeBuffer
        (JNIEnv *env, jobject obj, jbyteArray src, jlong src_len, jbyteArray dst, jlong dst_len) {
    jboolean src_isCopy;
    jboolean dst_isCopy;
    jbyte *temp_src = env->GetByteArrayElements(src, &src_isCopy);
    jbyte *temp_dst = env->GetByteArrayElements(dst, &dst_isCopy);

    size_t out_size = lzfse_encode_buffer((uint8_t *) temp_dst, dst_len,
                                          (uint8_t *) temp_src, src_len,
                                          NULL);


    env->ReleaseByteArrayElements(src, temp_src, src_isCopy ? 0 : JNI_ABORT);
    env->ReleaseByteArrayElements(dst, temp_dst, dst_isCopy ? 0 : JNI_ABORT);
    return out_size;
}

#ifdef __cplusplus
}
#endif
#endif
