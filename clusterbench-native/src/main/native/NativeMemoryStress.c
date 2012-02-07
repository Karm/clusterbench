/*
 ============================================================================
 Name        :
 Author      : Michal Karm Babacek
 Version     :
 Copyright   : Devil cares
 Description : in C, C99 fashion
 ============================================================================
 */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <jni.h>
#include <nativeStuff.h> // generated by javah via maven-native-plugin
#define MEGABYTE 1024*1024
JNIEXPORT jint JNICALL Java_org_jboss_test_clusterbench_common_load_NativeMemoryStress_allocateMegabytes (JNIEnv *env, jobject obj, jint megabytes, jint seconds) {
  char *data;
  printf("Gonna allocate %d megabytes and hold on for %d.\n",megabytes, seconds);
  data = (char *)malloc(megabytes * MEGABYTE * sizeof(char));

  if (data == NULL) {
    free(data);
    fprintf(stderr, "Fatal error: Malloc() has failed.\n");
    return 1;
  }

  //Fill it up so as to force the kernel to actually take that memory.
  memset(data,1,megabytes * MEGABYTE * sizeof(char));

  sleep(seconds);
  printf("Gonna free it.\n");
  free(data);
  return 0;
}

