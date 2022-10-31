# What is this?

Java bindings for the LZFSE C reference implementation.

# How to use..

## Add to your project

Include the following dependency in your `pom.xml`, and you're good to go.

```xml       
<dependencies>
    <dependency>
        <groupId>io.github.blackbeard334</groupId>
        <artifactId>lzfse47j</artifactId>
        <version>0.1</version>
        <classifier>natives-linux</classifier> 
 <!--or <classifier>natives-windows</classifier> --> 
    </dependency> 
</dependencies>
```

## Code example

```java
class Bla {
    long decompressLzfse(final byte[] srcArray, final byte[] dstArray) {
        return LzfseUtil.decodeBuffer(srcArray, srcArray.length, dstArray, dstArray.length);
    }
}
```

## Caveat

LZFSE headers do **not** contain the decompressed length of the compressed file(s), so you need to provide them.

### What if I don't know the length of the decompressed file?

The `LzfseUtil.decodeBuffer()` method will decompress bytes until:

- EOF reached
- DST buffer is full

Then we suggest something like:

```java
class Bla {
    byte[] decompressLzfseWithUnknownLength(final byte[] srcArray) {
        final byte[] dstArray = new byte[srcArray.length * 2];

        while (true) {
            final long l = LzfseUtil.decodeBuffer(srcArray, srcArray.length, dstArray, dstArray.length);

            // is the dst buffer full?
            if (l == dstArray.length) {
                // then double dst buffer size
                dstArray = new byte[dstArray.length * 2];
            } else {
                // else EOF reached
                break;
            }
        }

        // return the decompressed bytes range
        return Arrays.copyOfRange(dstArray, 0, l);
    }
}
```

# Development

### Requirements

- `apt install cmake`
- `apt install build-essential` ||
  MSBuild (https://learn.microsoft.com/en-us/visualstudio/msbuild/walkthrough-using-msbuild?view=vs-2022#install-msbuild)
- `apt install openjdk-11-jdk`

### Build

run `mvn -clean verify pom.xml -Pnatives-linux` || `-Pnatives-windows`