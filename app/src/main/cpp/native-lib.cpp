#include <jni.h>
#include <string>

extern "C"
jstring
Java_imageutil_zhp_com_imageutils_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
