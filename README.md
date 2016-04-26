# imagelib

[![Build Status](https://travis-ci.org/sangupta/imagelib.svg?branch=master)](https://travis-ci.org/sangupta/imagelib)
[![Coverage Status](https://coveralls.io/repos/sangupta/imagelib/badge.png)](https://coveralls.io/r/sangupta/imagelib)
[![Maven Version](https://maven-badges.herokuapp.com/maven-central/com.sangupta/imagelib/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.sangupta/imagelib)

`imagelib` is a simple image library for Java. It wraps around functionality from many different imaging libraries to
bring out the best into easy to use `static utility` methods. This allows developers to focus more on the business
logic than struggling with a lot of boiler-plate coding and/or error handling.

## Why?

Simple answer are the following featues:

* Static utility classes to perform most functions
* Reduces boiler plate code
* Use `Apache Sanselan` if image reading via `ImageIO` fails
* Read `CMYK` color JPEG files - and not just `RGB` color-model files
* Uses `twelvemonkeys` plugins to enhance read/write support for many common formats* 

## Usage examples

Reading an image is as easy as:

```java
BufferedImage image = ImageLibReader.readImage(myBytes); // if you have read the image in memory
```

## Versioning

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, `iamgelib` will be 
maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the follow format:

```
<major>.<minor>.<patch>
```

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major
* New additions without breaking backward compatibility bumps the minor
* Bug fixes and misc changes bump the patch

For more information on SemVer, please visit http://semver.org/.

## License

```
imagelib - Simple image library
Copyright (c) 2015-2016, Sandeep Gupta

http://sangupta.com/projects/imagelib

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
